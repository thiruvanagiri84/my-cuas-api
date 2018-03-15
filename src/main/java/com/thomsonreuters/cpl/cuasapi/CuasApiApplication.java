package com.thomsonreuters.cpl.cuasapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CuasApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(CuasApiApplication.class);
	
	public static void main(String[] args) {
		logger.debug("Logger log ........ test debug");
		
		SpringApplication.run(CuasApiApplication.class, args);
		
		logger.debug("Logger log ........ class loaded");
	}
}
