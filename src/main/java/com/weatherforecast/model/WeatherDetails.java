package com.weatherforecast.model;

import org.springframework.stereotype.Component;
import com.weatherforecast.util.TempConvUtil;

/**
 * @author NAVDEEPS
 *
 */
@Component
public class WeatherDetails { 
	float temp_min, temp_max;
	String rain;

	public WeatherDetails() {
	}
	
	public float getTemp_min() {
		return temp_min;
	}
	public float getTemp_max() {
		return temp_max;
	}
	public String getRain() {
		return rain;
	}
	
	public boolean isHot() {
		return temp_max > 40f;
	}

	public void setTemp_min(String temp_min) {
		this.temp_min = Float.parseFloat(temp_min);
		this.temp_min = TempConvUtil.getKelvinToCelcius(this.temp_min);
	}
	public void setTemp_max(String temp_max) {
		this.temp_max = Float.parseFloat(temp_max);
		this.temp_max = TempConvUtil.getKelvinToCelcius(this.temp_max);
	}
	public void setRain(String rain) {
		this.rain = rain;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n--- – Weather Information----- \n");
		sb.append("Temp_Min: " + getTemp_min() + "\n");
		sb.append("Temp_Max: " + getTemp_max() + "\n");
		sb.append("Rain:" + getRain() + "\n");
		sb.append("*****************************");
		return sb.toString();
	}
}
