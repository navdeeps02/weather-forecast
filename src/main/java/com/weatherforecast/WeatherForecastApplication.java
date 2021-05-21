package com.weatherforecast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author NAVDEEPS
 *
 */
@SpringBootApplication
//@ComponentScan (basePackages= {"com.weatherforecast.controller","com.weatherforecast.service","com.weatherforecast.model"})
public class WeatherForecastApplication {

	public static final Logger logger = LoggerFactory.getLogger(WeatherForecastApplication.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Starting Spring Boot application");
		SpringApplication.run(WeatherForecastApplication.class, args);
	}
	
	/**
	 * @param builder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
