
package com.solace.covid19.splitter.regional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solace.covid19.data.models.RawJHUCSSUCOVID19;
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

import static com.solace.covid19.data.models.COVID19Utilities.cloneFeature;

@SpringBootApplication
public class COVID19RegionalSplitter {

    private static final Logger logger = LoggerFactory.getLogger(COVID19RegionalSplitter.class);
    @Autowired
    private JmsTemplate jmsTemplate = null;
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, RawJHUCSSUCOVID19.Features> updateMap = new HashMap<String, RawJHUCSSUCOVID19.Features>();
    private String topicPrefix = "jhu/csse/covid19/cases/region/update/";

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(COVID19RegionalSplitter.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8083"));
        app.run(args);
    }

    @Bean
    public Consumer<RawJHUCSSUCOVID19> onCovidRawDataRegion() {
		// Add business logic here.
		return rawJHUCSSUCOVID19 -> {
			for (RawJHUCSSUCOVID19.Features feature : rawJHUCSSUCOVID19.getFeatures()) {
				String key = topicPrefix + feature.getAttributes().getCountryRegion() + "/" + feature.getAttributes().getProvinceState();
                if(feature.getAttributes().getLastUpdate() != null) {
                    if (!updateMap.containsKey(key) || updateMap.get(key).getAttributes().getLastUpdate().compareTo(feature.getAttributes().getLastUpdate()) != 0) {

                        try {
                            jmsTemplate.convertAndSend(key, mapper.writeValueAsString(cloneFeature(feature)));
                        } catch (JsonProcessingException e) {
                            logger.error(e.getMessage());
                        }
                        updateMap.put(key, feature);
                        logger.info("Publishing: " + key);
                    }
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
        jmsTemplate.setTimeToLive(24*60*60*1000);
    }
}

