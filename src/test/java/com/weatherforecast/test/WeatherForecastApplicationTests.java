package com.weatherforecast.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weatherforecast.controller.WeatherForecastController;
import com.weatherforecast.service.WeatherForecastService;

@SpringBootTest
class WeatherForecastApplicationTests {
	
	@Autowired
	WeatherForecastController controller;
	@Autowired
	WeatherForecastService service;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void validCityName(){
		System.out.println("Test validCityName");
		assertNotEquals("failure", service.fetchWeatherDetails("london"));
	}
	
	@Test
	public void invalidCityName(){
		System.out.println("Test invalidCityName");
		assertEquals("failure", service.fetchWeatherDetails("Invalid cityName"));
	} 
	
	@Test
	public void isEmptyCityName(){
		System.out.println("Test isEmptyCityName");
		assertEquals("failure", service.fetchWeatherDetails(""));
	}

}
