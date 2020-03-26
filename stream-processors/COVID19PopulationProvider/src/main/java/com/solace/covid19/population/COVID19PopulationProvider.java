package com.solace.covid19.population;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solace.covid19.data.models.COVID19UpdatePopulationStats;
import com.solace.covid19.data.models.COVID19UpdateSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;

import static com.solace.covid19.data.models.COVID19Utilities.cloneFeature;

@SpringBootApplication
public class COVID19PopulationProvider {
    private static final Logger logger = LoggerFactory.getLogger(COVID19PopulationProvider.class);
    private static final String COMMA_DELIMITER = ",";
    private Map<String, Long> popData;
    @Autowired
    private JmsTemplate jmsTemplate = null;
    private ObjectMapper mapper = new ObjectMapper();
    private String topicPrefix = "jhu/csse/covid19/cases/active/population/update/US/";

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(COVID19PopulationProvider.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8085"));
        app.run(args);
    }

    @Bean
    public Consumer<COVID19UpdateSchema> onCovid19USConfirmedUpdate() {
        return covid19UpdateSchema -> {
            COVID19UpdatePopulationStats populationStats = new COVID19UpdatePopulationStats(covid19UpdateSchema);
            if(popData.containsKey(covid19UpdateSchema.getAttributes().getProvinceState()))
            {
                Long population = popData.get(covid19UpdateSchema.getAttributes().getProvinceState());
                Integer confirmed = covid19UpdateSchema.getAttributes().getConfirmed();
                Double percent = (confirmed.doubleValue()/population)*100;
                logger.info("State: " + covid19UpdateSchema.getAttributes().getProvinceState() + "   " + percent);
                populationStats.setPopulation(new COVID19UpdatePopulationStats.Population(percent));
                String topic = topicPrefix + populationStats.getAttributes().getProvinceState();
                try {
                    jmsTemplate.convertAndSend(topic, mapper.writeValueAsString(populationStats));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                logger.info("Missing in map: " + covid19UpdateSchema.getAttributes().getProvinceState());
            }
        };
    }

    @PostConstruct
    private void parseLoadUSPopulationData() throws Exception
    {
        popData = new HashMap<String, Long>();
        InputStream inputStream = getClass().getResourceAsStream("/pop.csv");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            for (int i=0; (line = br.readLine()) != null; i++) {
                if(i!=0) {
                    String[] values = line.split(COMMA_DELIMITER);
                    popData.put(values[4], Long.parseLong(values[5]));
                }
            }
        }
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
