package com.weatherforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weatherforecast.service.WeatherForecastService;

/**
 * @author NAVDEEPS
 *
 */
@RestController
public class WeatherForecastController {
	
	@Autowired
	private WeatherForecastService weatherForecastService;
	
	/**
	 * @param cityName
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/api/weather-forecast")  
	public String getWeatherByCityName(String cityName)   
	{  
		if(cityName.equals("")) {
			return "failure";
		}
		return weatherForecastService.fetchWeatherDetails(cityName);
	}
	
	/**
	 * @return
	 */
	@RequestMapping("/")  
	public String testAPI()   
	{  
		return "Spring Boot service is up/running!!";
	} 
	
}
