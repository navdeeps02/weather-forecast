package com.weatherforecast.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.fasterxml.jackson.databind.JsonNode;
import com.weatherforecast.model.WeatherDetails;
import com.weatherforecast.util.Constants;
import com.weatherforecast.util.JSONParser;

/**
 * @author NAVDEEPS
 *
 */

@Component
public class WeatherForecastService {

	public static final Logger logger = LoggerFactory.getLogger(WeatherForecastService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	private WeatherDetails weatherDetails;
	
	TreeMap<Date, WeatherDetails> weatherDetailsMap;
	
	/**
	 * @param cityName
	 * @return
	 */
	public String fetchWeatherDetails(String cityName){
		//HTTPClient to call Restful service
		String URL = Constants.fixedURL + "q="+ cityName +"&appid=" + Constants.appID;
		
		String weather;
		try {
			logger.debug("retreiving weather status from openWeather API @URL: " + URL);
			weather = restTemplate.getForObject(URL, String.class);
		} catch (UnknownHttpStatusCodeException e) {
			logger.error("Error: Unknown host");
			return "failure";
		}
		catch (Exception e) {
			logger.error("Error:" + e);
			return "failure";
		}
		
		//parse response
		logger.debug("parsing response from JSON: " + weather);
		JsonNode weatherNode = JSONParser.getJSONNodefromData(weather);
		if(weatherNode == null) {
			logger.error(Constants.cityNotFound);
			return "failure";
		}
		
		//populate model
		populateWeatherResponseList (weatherNode);
		
		
		String response = "<h2 class=\"city-name\" data-name=\"l,d\">\r\n" + 
				"        <span>"+cityName+"</span>\r\n" +  
				"        </h2>\r\n" + 
				"        <span> Min:"+ String.format(java.util.Locale.US,"%.2f", weatherDetails.getTemp_min()) + "°C" +"</span>\r\n" +
				"        <span> Max:"+ String.format(java.util.Locale.US,"%.2f", weatherDetails.getTemp_max()) + "°C" +"</span>\r\n";
		if(weatherDetails.isHot()) {
			response += "<span>Caution:"+ Constants.tooHotOutside + "</span>\r\n";
		}
		if(weatherDetails.getRain().toLowerCase().equals("rain")) {
			response += "<span>Caution:"+ Constants.isRaining + "</span>\r\n";
		}
		logger.debug(response);		
		return response;
	}
	
	/**
	 * @param arrayNode
	 */
	private void populateWeatherResponseList(JsonNode arrayNode) {
		logger.debug("populating Weather ResponseList ");
		weatherDetailsMap = new TreeMap<Date, WeatherDetails>();
		for(JsonNode jsonNode : arrayNode) {
			JsonNode mainNode = jsonNode.get("main");
			weatherDetails = new WeatherDetails();
			//Get min max temp
			weatherDetails.setTemp_max(mainNode.get("temp_max").toString());
			weatherDetails.setTemp_min(mainNode.get("temp_min").toString());
			// Get weather type (rain or not)
			JsonNode weatherNodeArray = jsonNode.get("weather");
			for(JsonNode weatherNode : weatherNodeArray) {
				String rainString = weatherNode.get("main").toString();
				weatherDetails.setRain(rainString);
			}
			// Get date time
			Date dateTime = null;
			try {
				String dateString = jsonNode.get("dt_txt").toString();
				dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateString.substring(1, dateString.length()-1));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			weatherDetailsMap.put(dateTime, weatherDetails);
		}
	}
}
