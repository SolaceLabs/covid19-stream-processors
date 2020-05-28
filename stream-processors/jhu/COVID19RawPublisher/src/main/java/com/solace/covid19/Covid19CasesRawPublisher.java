
package com.solace.covid19;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;


@SpringBootApplication
public class Covid19CasesRawPublisher {

	private static final Logger logger = LoggerFactory.getLogger(Covid19CasesRawPublisher.class);
	ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(Covid19CasesRawPublisher.class);
	}

	@Bean
	public Supplier<String> publishRawData() {
		// Add business logic here.

		return () -> {
			RestTemplate restTemplate = new RestTemplateBuilder().build();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("f","json");
			map.add("where","1=1");
			map.add("outSr", "4326");
			map.add("outFields", "Province_State,Country_Region,Last_Update,Lat,Long_,Confirmed,Recovered,Deaths,Active");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
			RawJHUCSSUCOVID19 parsedData= null;
			boolean error = true;
			String data = null;
			while(error) {
				logger.info("Making REST request to arcgis");
				data = restTemplate.postForObject("https://services1.arcgis.com/0MSEUqKaxRlEPj5g/ArcGIS/rest/services/ncov_cases/FeatureServer/1/query", request, String.class);
				logger.debug(data);
				if(!data.contains("error"))
				{
					error=false;
					logger.info("Response received without error");
				}
			}
			try {
				parsedData = objectMapper.readValue(data, RawJHUCSSUCOVID19.class);
			} catch (JsonProcessingException e) {
				logger.error("There was a problem parsing the response data: " + data);
				logger.error(e.getMessage());
			}

			try {
				logger.info("Successfully Parsed Response into JSON");
				logger.debug(objectMapper.writeValueAsString(parsedData));
				return objectMapper.writeValueAsString(parsedData);
			} catch (JsonProcessingException e) {
				logger.error("There was a problem turning the parsed data back into JSON: " + e.getMessage());
			}
			return null;
		};
	}

}
