
package com.solace.covidtracking.states;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;


@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
	private ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public Supplier<CovidTracking_StatesCurrentData> retrieveAndPublish() {
        // Add business logic here.
        return () -> {
            RestTemplate restTemplate = new RestTemplateBuilder().build();
            CovidTracking_StatesCurrentData parsedData = null;
            boolean error = true;
            String data = null;
            String result = null;
            logger.info("Making REST request to CovidData");
            data = restTemplate.getForObject("https://covidtracking.com/api/states", String.class);
			try{
				parsedData = objectMapper.readValue(data, CovidTracking_StatesCurrentData.class);
                logger.debug(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parsedData));
                logger.info("Received & Parsed REST response");
                result = objectMapper.writeValueAsString(parsedData);
			} catch (JsonProcessingException e) {
				logger.error("There was a problem parsing the response data: " + data);
				logger.error(e.getMessage());
			}

			return parsedData;

        };
    }

}
