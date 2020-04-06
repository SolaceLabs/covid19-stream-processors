// This contains functions taht are common to both the all.js filter and the post-process.js hook.

class ScsLib {

  // This returns the value of a param, or specification extention if the param isn't set. 
  // If neither is set and the required flag is true, it throws an error.
  getParamOrExtension(info, params, paramName, extensionName, description, example, required) {
    let ret = '';
    if (params[paramName]) {
      ret = params[paramName];
    } else if (info.extensions()[extensionName]) {
      ret = info.extensions()[extensionName];
    } else if (required) {
      throw new Error(`Can't determine the ${description}. Please set the param ${paramName} or info.${extensionName}. Example: ${example}`);
    }
    return ret;
  }
}

module.exports = ScsLib;
