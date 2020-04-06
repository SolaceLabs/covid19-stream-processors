// vim: set ts=2 sw=2 sts=2 expandtab :
module.exports = ({ Nunjucks }) => {

  var yaml = require('js-yaml');
  var _ = require('lodash');
  const ScsLib = require('../lib/ScsLib');
  const scsLib = new ScsLib();

  // This maps json schema types to Java format strings.
  const formatMap = new Map();
  formatMap.set('boolean', '%s');
  formatMap.set('enum', '%s');
  formatMap.set('integer', '%d');
  formatMap.set('number', '%f');
  formatMap.set('null', '%s');
  formatMap.set('string', '%s');

  // This maps json schema types to examples of values.
  const sampleMap = new Map();
  sampleMap.set('boolean', 'true');
  sampleMap.set('integer', '1');
  sampleMap.set('null', 'string');
  sampleMap.set('number', '1.1');
  sampleMap.set('string', '"string"');

  // This maps json schema types to Java types.
  const typeMap = new Map();
  typeMap.set('boolean', 'Boolean');
  typeMap.set('integer', 'Integer');
  typeMap.set('null', 'String');
  typeMap.set('number', 'Double');
  typeMap.set('string', 'String');

  class SCSFunction {
    name;
		type;
		group;
    publishChannel;
    subscribeChannel;
    publishPayload;
    subscribePayload;
    reactive;

    get publishBindingName() {
      return this.name + "-out-0";
    }

    get subscribeBindingName() {
      return this.name + "-in-0";
    }

    get functionSignature() {
      var ret = '';
      switch(this.type) {
        case 'function':
          if (this.reactive) {
            ret = `public Function<Flux<${this.subscribePayload}>, Flux<${this.publishPayload}>> ${this.name}()`;
          } else {
            ret = `public Function<${this.subscribePayload}, ${this.publishPayload}> ${this.name}()`;
          }
          break;
        case 'supplier':
          if (this.reactive) {
            ret = `public Supplier<Flux<${this.publishPayload}>> ${this.name}()`;
          } else {
            ret = `public Supplier<${this.publishPayload}> ${this.name}()`;
          }
          break;
        case 'consumer':
          if (this.reactive) {
            ret = `public Consumer<Flux<${this.subscribePayload}>> ${this.name}()`;
          } else {
            ret = `public Consumer<${this.subscribePayload}> ${this.name}()`;
          }
          break;
        default:
          throw new Error(`Can't determine the function signature for ${this.name} because the type is ${this.type}`);
      }
      return ret;
    }

    get isPublisher() {
      return this.type === 'function' || this.type === 'supplier';
    }

    get isSubscriber() {
      return this.type === 'function' || this.type === 'consumer';
    }

  }

  // This generates the object that gets rendered in the application.yaml file.
  Nunjucks.addFilter('appProperties', ([asyncapi, params]) => {
    params.binder = params.binder || 'kafka';
    if (params.binder != 'kafka' && params.binder != 'rabbit' && params.binder != 'solace') {
      throw new Error("Please provide a parameter named 'binder' with the value kafka, rabbit or solace.");
    }

    let doc = {};
    doc.spring = {};
    doc.spring.cloud = {};
    doc.spring.cloud.stream = {};
    let scs = doc.spring.cloud.stream;
    scs.function = {};
    scs.function.definition = getFunctionDefinitions(asyncapi, params);
    scs.bindings = getBindings(asyncapi, params);

    if (params.binder === 'solace') {
      let additionalSubs = getAdditionalSubs(asyncapi);

      if (additionalSubs) {
        scs.solace = additionalSubs;
      }
    }

		if (isApplication(params)) {
      if (params.binder === 'solace') {
        doc.solace = getSolace(params);
      }

      doc.logging = {};
      doc.logging.level = {};
      doc.logging.level.root = 'info';
      doc.logging.level.org = {};
      doc.logging.level.org.springframework = 'info';

      if (params.actuator === 'true') {
        doc.server = {};
        doc.server.port = 8080;
        doc.management = {};
        doc.management.endpoints = {};
        doc.management.endpoints.web = {};
        doc.management.endpoints.web.exposure = {};
        doc.management.endpoints.web.exposure.include = '*';
      }
    }
    let ym = yaml.safeDump(doc, { lineWidth: 200 } );
    //console.log(ym);
    return ym;
  });

  Nunjucks.addFilter('artifactId', ([info, params]) => {
    return scsLib.getParamOrExtension(info, params, 'artifactId', 'x-artifact-id', 'Maven artifact ID', 'my-application', true);
  })

  Nunjucks.addFilter('camelCase', (str) => {
    return _.camelCase(str);
  })

  // This determines the base function name that we will use for the SCSt mapping between functions and bindings.
  Nunjucks.addFilter('functionName', ([channelName, channel]) => {
    return getFunctionNameByChannel(channelName, channel);
  })

  Nunjucks.addFilter('indent1', (numTabs) => {
    return indent(numTabs);
  })

  Nunjucks.addFilter('indent2', (numTabs) => {
    return indent(numTabs + 1);
  })

  Nunjucks.addFilter('indent3', (numTabs) => {
    return indent(numTabs + 2);
  })

  // This returns the proper Java type for a schema property.
  Nunjucks.addFilter('fixType', ([name, property]) => {

    //console.log('fixType: ' + name);
    
    let isArrayOfObjects = false;

    // For message headers, type is a property.
    // For schema properties, type is a function.
    let type = property.type;

    if (typeof type == "function") {
      type = property.type();
    }

    //console.log('fixType: ' + name + ' ' + type + ' ' + JSON.stringify(property._json) + ' ' );
    //console.log("");

    // If a schema has a property that is a ref to another schema,
    // the type is undefined, and the title gives the title of the referenced schema.
    let ret;
    if (type === undefined) {
      if (property._json.enum) {
        ret = _.upperFirst(name);
      } else {
        ret = property.title();
      }
    } else if (type === 'array') {
      if (!property._json.items) {
        throw new Error("Array named " + name + " must have an 'items' property to indicate what type the array elements are.");
      }
      //console.log('fixtype: ' + JSON.stringify(propery._json.items));
      let itemsType = property._json.items.type;
      if (itemsType) {
        itemsType = typeMap.get(itemsType);
      }
      if (!itemsType) {
        itemsType = _.upperFirst(name);
        isArrayOfObjects = true;
      }
      ret = _.upperFirst(itemsType) + "[]";
    } else if (type === 'object') {
      ret = _.upperFirst(name);
    } else {
      ret = typeMap.get(type);
      if (!ret) {
        ret = type;
      }
    }
    return [ret, isArrayOfObjects];
  })

  Nunjucks.addFilter('functions', ([asyncapi, params]) => {
    return getFunctionSpecs(asyncapi, params);
  });

  Nunjucks.addFilter('groupId', ([info, params]) => {
    return scsLib.getParamOrExtension(info, params, 'groupId', 'x-group-id', 'Maven group ID.', 'com.company', true);
  })

  Nunjucks.addFilter('log', (str) => {
    console.log(str);
    return str;
  })

  Nunjucks.addFilter('lowerFirst', (str) => {
    return _.lowerFirst(str);
  })

  Nunjucks.addFilter('mainClassName', ([info, params]) => {
    let cls = scsLib.getParamOrExtension(info, params, 'javaClass', 'x-java-class', 'Main application class.', 'Application', false);
    let ret = cls || "Application";
    return ret;
  });

  // This returns the Java class name of the payload.
  Nunjucks.addFilter('payloadClass', ([channelName, channel]) => {
    let ret = getPayloadClass(channel.publish());
    if (!ret) {
      ret = getPayloadClass(channel.subscribe());
    }
    if (!ret) {
      throw new Error("Channel " + channelName + ": no payload class has been defined.");
    }
    return ret;
  })

  Nunjucks.addFilter('solaceSpringCloudVersion', ([info, params]) => {
    var required = isApplication(params) && params.binder === 'solace';
    return scsLib.getParamOrExtension(info, params, 'solaceSpringCloudVersion', 'x-solace-spring-cloud-version', 'Solace Spring Cloud version', '1.0.0-RELEASE', required);
  })

  Nunjucks.addFilter('springCloudStreamVersion', ([info, params]) => {
    var required = !isApplication(params);
    return scsLib.getParamOrExtension(info, params, 'springCloudStreamVersion', 'x-spring-cloud-stream-version', 'Spring Cloud Stream version', '3.0.1.RELEASE', required);
  })

  Nunjucks.addFilter('springCloudVersion', ([info, params]) => {
    var required = isApplication(params);
    return scsLib.getParamOrExtension(info, params, 'springCloudVersion', 'x-spring-cloud-version', 'Spring Cloud version', 'Hoxton.SR1', required);
  })

	Nunjucks.addFilter('stringify', (obj) => {
    var str = JSON.stringify(obj, null, 2);
    return str;
  })


  // This returns an object containing information the template needs to render topic strings.
  Nunjucks.addFilter('topicInfo', ([channelName, channel]) => {
    let p = channel.parameters();
    return getTopicInfo(channelName, channel);
  })

  Nunjucks.addFilter('upperFirst', (str) => {
    return _.upperFirst(str);
  })

  function dump(obj) {
    let s = typeof obj;
    for (let p in obj) {
      s += " ";
      s += p;
    }
    return s;
  }

  // For the Solace binder. This determines the topic that must be subscribed to on a queue, when the x-scs-destination is given (which is the queue name.)
  // TODO: Make this work with the new FunctionSpec stuff.
  function getAdditionalSubs(asyncapi) {
    let ret;

    for (let channelName in asyncapi.channels()) {
      let channel = asyncapi.channels()[channelName];
      let channelJson = channel._json;
      
      if (channelJson.subscribe) {
        let functionName = getFunctionName(channelName, channelJson.subscribe, true);
        let topicInfo = getTopicInfo(channelName, channel);
        let queue = channelJson.subscribe['x-scs-destination'];
        if (topicInfo.hasParams || queue) {
          if (!ret) {
            ret = {};
            ret.bindings = {};
          }
          let bindingName = functionName + "-in-0";
          ret.bindings[bindingName] = {};
          ret.bindings[bindingName].consumer = {};
          ret.bindings[bindingName].consumer.queueAdditionalSubscriptions = topicInfo.subscribeTopic;
        }
      }
    } 

    return ret;
  }

  // This returns the SCSt bindings config that will appear in application.yaml.
  function getBindings(asyncapi, params) {
    let ret = {};
    let funcs = getFunctionSpecs(asyncapi, params);

    funcs.forEach((spec, name, map) => {
      if (spec.isPublisher) {
        ret[spec.publishBindingName] = {};
        ret[spec.publishBindingName].destination = spec.publishChannel;
      }
      if (spec.isSubscriber) {
        ret[spec.subscribeBindingName] = {};
				ret[spec.subscribeBindingName].destination = spec.subscribeChannel;
				if (spec.group) {
					ret[spec.subscribeBindingName].group = spec.group;
				}
      }
    });
    return ret;
  }

  // This returns the base function name that SCSt will use to map functions with bindings.
  function getFunctionName(channelName, operation, isSubscribe) {
    let ret;
    //console.log('functionName operation: ' + JSON.stringify(operation));
    let functionName = operation['x-scs-function-name'];

    if (!functionName) {
      functionName = operation.operationId;
    }

    if (functionName) {
      ret = functionName;
    } else {
      ret = _.camelCase(channelName) + (isSubscribe ? "Consumer" : "Provider");
    }
    return ret;
  }

  // This returns the base function name that SCSt will use to map functions with bindings.
  function getFunctionNameByChannel(channelName, channel) {
    let ret = _.camelCase(channelName);
    let channelJson = channel._json;
    //console.log('functionName channel: ' + JSON.stringify(channelJson));
    let functionName = channelJson['x-scs-function-name'];
    //console.log('function name for channel ' + channelName + ': ' + functionName);
    if (functionName) {
      ret = functionName;
    }
    return ret;
  }

  // This returns the string that gets rendered in the function.definition part of application.yaml.
  function getFunctionDefinitions(asyncapi, params) {
    let ret = "";
    let funcs = getFunctionSpecs(asyncapi, params);
    let names = funcs.keys();
    ret = Array.from(names).join(";");
    return ret;
  }

  function getFunctionSpecs(asyncapi, params) {
    // This maps function names to SCS function definitions.
    const functionMap = new Map();
    const reactive = params.reactive === 'true';

    for (let channelName in asyncapi.channels()) {
      let channel = asyncapi.channels()[channelName];
      let channelJson = channel._json;
      //console.log("=====================================");
      //console.log("channelJson: " + JSON.stringify(channelJson));
      //console.log("=====================================");
      let functionSpec;
      if (channelJson.publish) {
        let name = getFunctionName(channelName, channelJson.publish, false);
        functionSpec = functionMap.get(name);
        if (functionSpec) {
          if (functionSpec.type === 'supplier' || functionSpec === 'function') {
            throw new Error(`Function ${name} can't publish to both channels {a.channel} and ${channelName}.`);
          }
          functionSpec.type = 'function';
        } else {
          functionSpec = new SCSFunction();
          functionSpec.name = name;
          functionSpec.type = 'supplier';
          functionSpec.reactive = reactive;
          functionMap.set(name, functionSpec);
        }
        let payload = getPayloadClass(channel.publish());
        if (!payload) {
          throw new Error("Channel " + channelName + ": no payload class has been defined.");
        }
        functionSpec.publishPayload = payload;
        functionSpec.publishChannel = channelName;
      }
      if (channelJson.subscribe) {
        let name = getFunctionName(channelName, channelJson.subscribe, true);
        functionSpec = functionMap.get(name);
        if (functionSpec) {
          if (functionSpec.type === 'consumer' || functionSpec === 'function') {
            throw new Error(`Function ${name} can't subscribe to both channels {functionSpec.channel} and ${channelName}.`);
          }
          functionSpec.type = 'function'
        } else {
          functionSpec = new SCSFunction();
          functionSpec.name = name;
          functionSpec.type = 'consumer';
          functionSpec.reactive = reactive;
          functionMap.set(name, functionSpec);
        }
        let payload = getPayloadClass(channel.subscribe());
        if (!payload) {
          throw new Error("Channel " + channelName + ": no payload class has been defined.");
        }
        functionSpec.subscribePayload = payload;
				var group = channelJson.subscribe['x-scs-group'];
				if (group) {
					functionSpec.group = group;
				}
				var dest = channelJson.subscribe['x-scs-destination'];
				if (dest) {
					functionSpec.subscribeChannel = dest;
				} else {
					functionSpec.subscribeChannel = channelName;
				}
      }
    }

    return functionMap;
  }

  function getPayloadClass(pubOrSub) {
    let ret;

    if (pubOrSub && pubOrSub._json && pubOrSub._json.message && pubOrSub._json.message.payload) {
      //console.log("getPayloadClass: "  + JSON.stringify(pubOrSub._json.message));
      ret = _.upperFirst(pubOrSub._json.message.payload['x-parser-schema-id']);
    }

    return ret;
  }

  // This returns the connection properties for a solace binder, for application.yaml.
  function getSolace(params) {
    let ret = {};
    ret.java = {};
    ret.java.host = params.host || 'tcp://localhost:5555';
    ret.java.msgVpn = params.msgVpn || 'default';
    ret.java.clientUsername = params.username || 'default';
    ret.java.clientPassword = params.password || 'default';
    return ret;
  }

  // This returns an object containing information the template needs to render topic strings.
  function getTopicInfo(channelName, channel) {
    const ret = {};
    let publishTopic = String(channelName);
    let subscribeTopic = String(channelName);
    const params = [];
    let functionParamList = "";
    let functionArgList = "";
    let sampleArgList = "";
    let first = true;

    //console.log("params: " + JSON.stringify(channel.parameters()));
    for (let name in channel.parameters()) {
      const nameWithBrackets = "{" + name + "}";
      const schema = channel.parameter(name)['_json']['schema'];
      //console.log("schema: " + dump(schema));
      const type = schema.type;
      const param = { "name": _.lowerFirst(name) };
      let sampleArg = 1;

      if (first) {
        first = false;
      } else {
        functionParamList += ", ";
        functionArgList += ", ";
      }

      sampleArgList += ", ";

      if (type) {
        //console.log("It's a type: " + type);
        const javaType = typeMap.get(type);
        if (!javaType) throw new Error("topicInfo filter: type not found in typeMap: " + type);
        param.type = javaType;
        const printfArg = formatMap.get(type);
        //console.log("printf: " + printfArg);
        if (!printfArg) throw new Error("topicInfo filter: type not found in formatMap: " + type);
        //console.log("Replacing " + nameWithBrackets);
        publishTopic = publishTopic.replace(nameWithBrackets, printfArg);
        sampleArg = sampleMap.get(type);
      } else {
        const en = schema.enum;
        if (en) {
          //console.log("It's an enum: " + en);
          param.type = _.upperFirst(name);
          param.enum = en;
          sampleArg = "Messaging." + param.type + "." + en[0];
          //console.log("Replacing " + nameWithBrackets);
          publishTopic = publishTopic.replace(nameWithBrackets, "%s");
        } else {
          throw new Error("topicInfo filter: Unknown parameter type: " + JSON.stringify(schema));
        }
      }

      subscribeTopic = subscribeTopic.replace(nameWithBrackets, "*");
      functionParamList += param.type + " " + param.name;
      functionArgList += param.name;
      sampleArgList += sampleArg;
      params.push(param);
    }
    ret.functionArgList = functionArgList;
    ret.functionParamList = functionParamList;
    ret.sampleArgList = sampleArgList;
    ret.channelName = channelName;
    ret.params = params;
    ret.publishTopic = publishTopic;
    ret.subscribeTopic = subscribeTopic;
    ret.hasParams = params.length > 0;
    return ret;
  }

  function indent(numTabs) {
    return "\t".repeat(numTabs);
  }

  function isApplication(params) {
    var artifactType = params.artifactType;
    return (!artifactType || artifactType === 'application')
  }
}
