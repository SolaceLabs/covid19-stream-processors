
package com.solace.covid19.splitter.cases;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.solace.covid19.splitter.cases.COVID19Utilities.cloneOnlyAttribute;


@SpringBootApplication
public class COVID19CasesSplitter {

	private static final Logger logger = LoggerFactory.getLogger(COVID19CasesSplitter.class);
	@Autowired
	private JmsTemplate jmsTemplate = null;
	private ObjectMapper mapper = new ObjectMapper();
	private Map<String, RawJHUCSSUCOVID19.Features> updateMap = new HashMap<String, RawJHUCSSUCOVID19.Features>();
	private String topicPrefix="jhu/csse/covid19/cases/";

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(COVID19CasesSplitter.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8084"));
		app.run(args);
	}


    @Bean
	public Consumer<RawJHUCSSUCOVID19> onCovid19RawData() {
		// Add business logic here.
		return rawJHUCSSUCOVID19 -> {
			for(RawJHUCSSUCOVID19.Features feature : rawJHUCSSUCOVID19.getFeatures()) {
				String key = topicPrefix + feature.getAttributes().getCountryRegion()+"/"+ feature.getAttributes().getProvinceState();
				if (!updateMap.containsKey(key) || updateMap.get(key).getAttributes().getLastUpdate().compareTo(feature.getAttributes().getLastUpdate())!=0)
				{
					if(!updateMap.containsKey(key) || updateMap.get(key).getAttributes().getActive().compareTo(feature.getAttributes().getActive())!=0)
					{
						String topicName = topicPrefix + "active/update/" + feature.getAttributes().getCountryRegion() + "/" + feature.getAttributes().getProvinceState();
						try {
							jmsTemplate.convertAndSend(topicName, mapper.writeValueAsString(cloneOnlyAttribute(feature, COVID19Utilities.DataAttribute.ACTIVE)));
						} catch (JsonProcessingException e) {
							logger.error(e.getMessage());
						}
						logger.info("Publishing: " + topicName);
					}
					if(!updateMap.containsKey(key) || updateMap.get(key).getAttributes().getConfirmed().compareTo(feature.getAttributes().getConfirmed())!=0)
					{
						String topicName = topicPrefix + "confirmed/update/" + feature.getAttributes().getCountryRegion() + "/" + feature.getAttributes().getProvinceState();
						try {
							jmsTemplate.convertAndSend(topicName, mapper.writeValueAsString(cloneOnlyAttribute(feature, COVID19Utilities.DataAttribute.CONFIRMED)));
						} catch (JsonProcessingException e) {
							logger.error(e.getMessage());
						}
						logger.info("Publishing: " + topicName);
					}
					if(!updateMap.containsKey(key) || updateMap.get(key).getAttributes().getDeaths().compareTo(feature.getAttributes().getDeaths())!=0)
					{
						String topicName = topicPrefix + "deaths/update/" + feature.getAttributes().getCountryRegion() + "/" + feature.getAttributes().getProvinceState();
						try {
							jmsTemplate.convertAndSend(topicName, mapper.writeValueAsString(cloneOnlyAttribute(feature, COVID19Utilities.DataAttribute.DEATHS)));
						} catch (JsonProcessingException e) {
							logger.error(e.getMessage());
						}
						logger.info("Publishing: " + topicName );
					}
					if(!updateMap.containsKey(key) || updateMap.get(key).getAttributes().getRecovered().compareTo(feature.getAttributes().getRecovered())!=0)
					{
						String topicName = topicPrefix + "recovered/update/" + feature.getAttributes().getCountryRegion() + "/" + feature.getAttributes().getProvinceState();
						try {
							jmsTemplate.convertAndSend(topicName, mapper.writeValueAsString(cloneOnlyAttribute(feature, COVID19Utilities.DataAttribute.RECOVERED)));
						} catch (JsonProcessingException e) {
							logger.error(e.getMessage());
						}
						logger.info("Publishing: " + topicName);
					}
					updateMap.put(key, feature);
				}
			}
		};
	}

	@PostConstruct
	private void fixJMSTemplate() {
		// Code that makes the JMS Template Cache Connections for Performance.
		CachingConnectionFactory ccf = new CachingConnectionFactory();
		ccf.setTargetConnectionFactory(jmsTemplate.getConnectionFactory());
		jmsTemplate.setConnectionFactory(ccf);
		jmsTemplate.setPubSubDomain(true);
	}
}
