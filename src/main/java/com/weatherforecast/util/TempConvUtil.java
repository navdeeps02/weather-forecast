package com.weatherforecast.util;

/**
 * @author NAVDEEPS
 *
 */
public class TempConvUtil {
	/**
	 * @param temp
	 * @return
	 */
	public static float getFarenheitToCelcius(float temp) {
		return (float) (temp-32f) * 5f / 9f;
	}
	
	/**
	 * @param temp
	 * @return
	 */
	public static float getKelvinToCelcius(float temp) {
		
		return (float) (temp - 273.15);
	}
}
