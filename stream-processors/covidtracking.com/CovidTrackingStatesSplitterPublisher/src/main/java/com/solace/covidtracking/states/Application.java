
package com.solace.covidtracking.states;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Application {

	@Autowired
	private JmsTemplate jmsTemplate = null;
	private ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	private Map<String, CovidTracking_StateCurrentData> cache = new HashMap<String, CovidTracking_StateCurrentData>();

	@Value("${topicPrefix:com/covidtracking/states/current/update/}")
	private String topicPrefix;

	@Value("${useCache:true}")
	private boolean useCache;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8084"));
		app.run(args);
	}

	@Bean
	public Consumer<List<CovidTracking_StateCurrentData>> splitAndPublish() {
		// Add business logic here.
		return covidTracking_StatesCurrentDataList -> {

			for (CovidTracking_StateCurrentData item : covidTracking_StatesCurrentDataList) {
				if (item.getState() != null) {
					String topicName = topicPrefix + item.getState();
					if (!useCache || !cache.containsKey(topicName)
							|| 0 != cache.get(topicName).getHash().compareTo(item.getHash())) {
						try {
							jmsTemplate.convertAndSend(topicName, objectMapper.writeValueAsString(item));
							logger.info("Sending: " + topicName);
							cache.put(topicName, item);
						} catch (JsonProcessingException e) {
							logger.error(e.getMessage());
						}
					}
				} else {
					logger.warn("Skipping state with value of null");
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
		jmsTemplate.setTimeToLive(24 * 60 * 60 * 1000);
	}
}
