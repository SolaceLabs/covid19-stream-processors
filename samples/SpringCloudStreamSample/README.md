# Spring Cloud Stream Sample

## Overview
This Spring Cloud Stream microservice shows how to consume COVID-19 data streaming from Solace's public broker. 

This project can be imported into your IDE as a maven project and it relies on the COVID19DataModels for Spring Cloud Stream to automatically convert the events into POJOs for you to process. 
The project is already configured with public credentials. 

You can choose which data you want by modifying the topic subscriptions in the application.yml file. 

## How to Run

### Clone Repo

### Install Data Model
`cd jhu-covid19-stream-processors/stream-processors/COVID19DataModels/`

`./mvnw clean install`

### Run the Application
`cd jhu-covid19-stream-processors/samples/SpringCloudStreamSample`

`./mvnw spring-boot:run` 

## More Information
* [COVID-19 Topic Information](https://github.com/SolaceLabs/jhu-covid19-stream-processors#2-choose-your-topics):  
* [Solace Spring Cloud Stream Binder](https://github.com/SolaceProducts/solace-spring-cloud/tree/master/solace-spring-cloud-starters/solace-spring-cloud-stream-starter)
* [Spring Cloud Stream Reference Guide:](https://cloud.spring.io/spring-cloud-static/spring-cloud-stream/3.0.3.RELEASE/reference/html/spring-cloud-stream.html#spring-cloud-stream-reference)
