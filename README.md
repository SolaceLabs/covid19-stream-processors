| NOTICE | The streams below are no longer continuously running on a publicly available broker. If you would like to use them you can spin up a Solace Broker for free in [Solace Cloud](https://bit.ly/try-solace-free) and run the `Publisher` apps in the [Stream Processors section]( https://github.com/SolaceLabs/covid19-stream-processors#covid19-stream-processors) to publish the event streams. |
| --- | --- |

Table of Contents
=================
   * [Overview & Data Sources](#overview--data-sources)
      * [The Covid Tracking Project](#the-covid-tracking-project)
      * [John Hopkins University](#john-hopkins-university)
   * [How to consume](#how-to-consume)
      * [1. Connection information](#1-connection-information)
         * [Connections credentials](#connections-credentials)
            * [Credentials](#credentials)
            * [Connection Points](#connection-points)
      * [2. Choose your Topics](#2-choose-your-topics)
         * [Streams are available on these topics](#streams-are-available-on-these-topics)
         * [Example topic subscriptions](#example-topic-subscriptions)
      * [3. Create your application](#3-create-your-application)
         * [Sample Applications to Get Started Quickly](#sample-applications-to-get-started-quickly)
         * [COVID19 Stream Processors](#covid19-stream-processors)
   * [Test Topics](#test-topics)
   * [Contribution](#contribution)
   * [Credit](#credit)
   * [Disclaimer](#disclaimer)

# Overview & Data Sources
This repo provides streams information and example applications on how to consume COVID19 related topics aggregated from different data sources. Below is a list of the different data sources used to feed the published streams.

## The Covid Tracking Project
A team from the [The Atlantic](https://www.theatlantic.com/) launched [The Covid Tracking Project](https://covidtracking.com/) to collect and publish testing data available for US states and territories.

## John Hopkins University
A team at [Johns Hopkins University Center for Systems Science and Engineering (JHU CSSE)](https://systems.jhu.edu/) has developed this [interactive web-based dashboard](https://www.arcgis.com/apps/opsdashboard/index.html#/bda7594740fd40299423467b48e9ecf6) to provide researchers, public health authorities, and the general public with a user-friendly tool to track the COVID-19 pandemic as it unfolds. Their team has also been nice enough to periodically (usually once or twice a day) upload the data to this [Github Repo](https://github.com/CSSEGISandData/COVID-19) which has been very popular in the developer community; but has led to developers wanting to receive updates in a more efficient manner. To further their teams’ efforts, help the community, and to “do our part”, we at Solace have created an application that polls the feature service, looks for differences in the data, and publishes updates into an Event Broker we are making available for public use. This allows anyone to consume data updates in near real-time. The data is being published into the event brokers using dynamic topics which allows subscribers to pick, choose and filter on the specific data that they want to consume; e.g.,  a developer could choose to only get updated when an update it available for a specific Country & Province/State that they are interested in. 

# How to consume
Any client application could consume those streams by subscribing to the topic of choice as per the diagram below. Note that the streams are provided over Solace PubSub+ Event Broker that has built-in support for a variety of open standard protocols and APIs.

![PubSub+ E2E](./img/PubSub_COVID.jpg) 
## 1. Connection information

Connect to the PubSub+ broker using one of the connection end points below. For more details, check out the [Solace Developer Portal](https://www.solace.dev) and the [API docs](https://docs.solace.com/APIs.htm) samples for the different messaging APIs and protocols  
![Supported APIs and Protocols](https://www.solace.dev/static/solace-apis-protocols-support-9cdf3a1620efa0a64c238bf062835735.png)

### Connections credentials
#### Credentials
`MessageVPN` : `covid-19-streams`  
`Client Username` : `covid-public-client`  
`Client Password`: `covid19` 

#### Connection Points
| API/Protocol | Host |
|:---------- |:--------
|`SMF Host`|`tcp://covid.messaging.solace.cloud:55555`|
|`Web Messaging Host`|`ws://covid.messaging.solace.cloud:80`|
|`Secured Web Messaging Host`|`wss://covid.messaging.solace.cloud:443`|
|`MQTT Host`|`tcp://covid.messaging.solace.cloud:1883`|
|`Secured MQTT Host`|`ssl://covid.messaging.solace.cloud:8883`|
|`WebSocket MQTT Host`|`ws://covid.messaging.solace.cloud:8000`|
|`AMQP 1.0`|`amqp://covid.messaging.solace.cloud:5672`|

Note: if you want to receive notifications via a REST end point that supports `POST`, contact us at covid19-project@solace.com


## 2. Choose your Topics

### Streams are available on these topics
As of right now, the following is the list of streams available for consumption 

#### John Hopkins University
|  Description| Schema| Topic| Notes
| ---- |----|-------| --- |
| Raw data for all countries published every ~45 seconds |[Raw Data Schema](./schemas/JHURaw.json) | `jhu/csse/covid19/raw`| |
| Updated Active/Deaths/Confirmed/Recovered for a region/state in one event | [Regional Update Schema](./schemas/COVID19UpdateSchema.json) | `jhu/csse/covid19/cases/region/update/{attributes.countryRegion}/{attributes.provinceState}`| |
| Updated Deaths for a region/state | [Update Type Schema](./schemas/JHUUpdateSchema.json) | `jhu/csse/covid19/cases/deaths/update/{attributes.countryRegion}/{attributes.provinceState}`| |
| Updated Active cases for a region/state | [Update Type Schema](./schemas/JHUUpdateTypeSchema.json) | `jhu/csse/covid19/cases/active/update/{attributes.countryRegion}/{attributes.provinceState}`| |
| Updated Confirmed cases for a region/state | [Update Type Schema](./schemas/JHUUpdateTypeSchema.json) | `jhu/csse/covid19/cases/confirmed/update/{attributes.countryRegion}/{attributes.provinceState}`| |
| Updated Recovered cases for a region/state |[Update Type Schema](./schemas/JHUUpdateTypeSchema.json) |  `jhu/csse/covid19/cases/recovered/update/{attributes.countryRegion}/{attributes.provinceState}`| |
| Shows the updated percentage of the population affected | [Update Population Schema](./schemas/JHUUpdatePopulationSchema.json) | `jhu/csse/covid19/cases/active/population/update/US/{attributes.provinceState}`|Only US for now |

#### The Covid Tracking Project
|  Description| Schema| Topic| Notes
| ---- |----|-------| --- |
| Raw data for all the states in the US published every ~45 seconds | [Raw Data Schema](./schemas/CovidTrackingRaw.json) | `com/covidtracking/states/current/get/raw` |
| Updated US state information | [Raw Data Schema](./schemas/CovidTrackingState.json) |  `com/covidtracking/states/current/update/{state}` |

Subscribe to one or more of the available topics above to receive the required data. 
**Note that the streams defined as *update* above only send events when updates actually occur which can be infrequently. For development purposes we are providing [test topics](#test-topics) that get published every few minutes**

For more more information on using topic wildcards check out [SMF Topic Subscriptions](https://docs.solace.com/PubSub-Basics/Wildcard-Charaters-Topic-Subs.htm) and [MQTT Topic Subscriptions](https://docs.solace.com/Open-APIs-Protocols/MQTT/MQTT-Topics.htm#Wildcard)

Note: If you have your own COVID-19 event stream that you'd like to share please contact us at covid19-project@solace.com

### Example topic subscriptions
Subscribing to the following streams will give you the corresponding results:

| Subscription        | Result   
| ------------- |:-------------|
|`jhu/csse/covid19/cases/region/update/>`|All region updates|
|`jhu/csse/covid19/cases/region/update/US/>`|All updates for country US|
|`jhu/csse/covid19/cases/region/update/US/Florida`|All updates where state is Florida|
|`jhu/csse/covid19/cases/recovered/update/Canada/Ontario`|All recovered in Ontario, Canada|
|`jhu/csse/covid19/cases/active/update/Canada/*`|All active in Canada|
|`jhu/csse/covid19/cases/deaths/update/Brazil/*`|All deaths in Brazil (Note: country with no province/state)|
|`jhu/csse/covid19/cases/*/update/Diamond Princess/*`|All case types from the Diamond Princess cruise ship|



## 3. Create your application
After connecting and choosing the topics to listen on, it's time to build your application. Check out the examples below for applications that consume the streams.

### Sample Applications to Get Started Quickly
| Application | Language | Description
| ---- | ---| --- |
| [Spring Cloud Stream Sample](./samples/SpringCloudStreamSample) | Java + Spring | This microservice shows how to consume events using the Spring Cloud Stream project, which abstracts the messaging APIs from the developer and allows them to focus on implementing their business logic.
| [JavaScript Sample](./samples/JavaScriptSample)| JavaScript | This html page allows you to test out multiple topics and view the stream body response. You can check it out on the [CovidStreamViewer](https://www.marcd.dev/COVIDStreamViewer/index.html)
|[MQTT JS Sample](./samples/mqtt)|JavaScript| This html page demonstrates connecting to the Solace PubSub+ COVID19 Broker using MQTT JS Library. You can check it out on the [MQTT CovidStreamViewer](https://www.marcd.dev/COVIDStreamViewer/mqtt/mqttListener.html)
|[NodeJS Sample](./samples/nodejs)|NodeJS| This NodeJs application runs in the terminal using mqtt.js npm library
|[Python Sample](./samples/python)|Python| This NodeJs executable runs in the terminal using the paho-mqtt python library

### COVID19 Stream Processors
Below are Spring Boot microservices used to create the event current streams available for consumption. They are also good examples if you would like to consume the streams and republish them into an event broker. Note that they are not intended to be run with the provided credentials as we are running them as a services.

| Application        | Version           | Integration  | Description |
| ------------- |:-------------:| :-----| :-----|
| [COVID19RawPublisher](./stream-processors/jhu/COVID19RawPublisher)      | 0.1 | Spring | This application publihses RAW JHU data |
| [COVID19CaseSplitter](./stream-processors/jhu/COVID19CasesSplitter)      | 0.1 | Spring | This application consumes RAW JHU COVID19 data, and publishes smaller updates |
| [COVID19RegionalSplitter](./stream-processors/jhu/COVID19RegionalSplitter) | 0.1      |    Spring | This application consumes RAW JHU COVID19 data, splits it into regional updates and publishes smaller regional updates
| [COVID19PopulationProvider](./stream-processors/jhu/COVID19PopulationProvider)     | 0.1      |   Spring | This application publishes the percentage of population affected in the areas
| [CovidTrackingStatesPublisher](./stream-processors/covidtracking.com/CovidTrackingStatesPublisher) | 0.1 | Spring | This application consumes RAW CovidTracking.com data, and publishes smaller updates
| [CovidTrackingStatesSplitterPublisher](./stream-processors/covidtracking.com/CovidTrackingStatesSplitterPublisher) | 0.1 | Spring | This application consumes RAW CovidTracking.com data, splits it into states and publishes smaller updates

![EventPortal](./img/EventPortal.png)

Note: Contact us at covid19-project@solace.com to add more event stream into the current broker.

# Test Topics
This section includes information about test streams that are available. Since actual updates can occur infrequently, we are providing these test streams strictly for development purposes. Instead of only receiving events when updates occur, these topics, which include `test` as a level, will regularly receive events whether or not an update has actually occurred. Once you have completed development you should remove the `test` level from your topic and you’ll only receive events when actual updates occur.

|  Description| Topic| Notes
| ---- |----| --- |
| Updated Active/Deaths/Confirmed/Recovered for a region/state in one event | `jhu/csse/covid19/test/cases/region/update/{attributes.countryRegion}/{attributes.provinceState}`| |
| Updated Deaths for a region/state | `jhu/csse/covid19/test/cases/deaths/update/{attributes.countryRegion}/{attributes.provinceState}`| |
| Updated Active cases for a region/state | `jhu/csse/covid19/test/cases/active/update/{attributes.countryRegion}/{attributes.provinceState}`| |
| Updated Confirmed cases for a region/state | `jhu/csse/covid19/test/cases/confirmed/update/{attributes.countryRegion}/{attributes.provinceState}`| |
| Updated Recovered cases for a region/state| `jhu/csse/covid19/test/cases/recovered/update/{attributes.countryRegion}/{attributes.provinceState}`| |
| Shows the updated percentage of the population affected | `jhu/csse/covid19/test/cases/active/population/update/US/{attributes.provinceState}`|Only US for now |
| Updated US state information | `com/covidtracking/test/states/current/update/{state}`| |

**Note that the `raw` streams is regularly updated every ~45 seconds**

# Contribution
See [Contribution guidelines](./CONTRIBUTING.md) form more details

# Credit
* Social Preview Image by [Gerd Altmann](https://pixabay.com/users/geralt-9301/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=3513216) from [Pixabay](https://pixabay.com/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=3513216)

# Disclaimer
1. As per Johns Hopkins University [Terms of Use](https://github.com/CSSEGISandData/COVID-19) this data is public strictly for educational and academic research purposes.
1. Data streams are provided and maintained by [Solace](https://www.solace.dev/) under the same terms of use.
