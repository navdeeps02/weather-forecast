package com.weatherforecast.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author NAVDEEPS
 *
 */
public class JSONParser {

	/**
	 * @param weather
	 * @return
	 */
	public static JsonNode getJSONNodefromData(String weather) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ObjectNode weatherMap = objectMapper.readValue(weather,ObjectNode.class);
			JsonNode arrayNode = weatherMap.get("list");
			if(arrayNode.isArray()) {
				return arrayNode;
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
