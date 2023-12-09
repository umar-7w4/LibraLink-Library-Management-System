package com.libralink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class LibraLinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraLinkApplication.class, args);
	}
	
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return builder;
    }

}



