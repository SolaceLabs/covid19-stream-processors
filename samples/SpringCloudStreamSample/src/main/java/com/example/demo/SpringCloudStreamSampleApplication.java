package com.example.demo;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.solace.covid19.data.models.COVID19UpdateSchema;

@SpringBootApplication
public class SpringCloudStreamSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamSampleApplication.class, args);
	}

	/*
	 *  This Consumer processes events received via the consumeRegionUpdates-in-0 channel. 
	 *  You can update the topic to subscribe to in the application.yml file. 
	 *  Edit queueAdditionalSubscriptions under the consumeRegionUpdates-in-0 binding
	 *  
	 *  Default topic is listening for updates for Canada
	 *  jhu/csse/covid19/cases/region/update/Canada/>
	 *  
	 */
	@Bean
    public Consumer<COVID19UpdateSchema> consumeRegionUpdates() {
        return covid19UpdateSchema -> {
        	System.out.println("Received Region Update: " + covid19UpdateSchema);
        	//TODO Add your business logic here!
        };
	}
	
	/*
	 *  This Consumer processes events received via the consumeCaseTypeUpdates-in-0 channel. 
	 *  You can update the topic to subscribe to in the application.yml file. 
	 *  Edit queueAdditionalSubscriptions under the consumeCaseTypeUpdates-in-0 binding
	 *  
	 *  Default topic is listening for 'active' updates for Switzerland
	 *  jhu/csse/covid19/cases/active/update/Switzerland/>
	 *  
	 */
	@Bean
    public Consumer<COVID19UpdateSchema> consumeCaseTypeUpdates() {
        return covid19UpdateSchema -> {
        	System.out.println("Received Case Type Update: " + covid19UpdateSchema);
        	//TODO Add your business logic here!
        };
	}

}
