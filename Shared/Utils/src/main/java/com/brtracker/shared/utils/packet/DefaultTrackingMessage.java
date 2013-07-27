package com.brtracker.shared.utils.packet;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;

public class DefaultTrackingMessage implements TrackingMessage {

	private Map<String, Object> messagePropertyMap = new HashMap<String, Object>();
	private String messageName = "";
	
	public DefaultTrackingMessage() {
	}

	public DefaultTrackingMessage(Map<String, Object> messagePropertyMap, String messageName) {
		this.messagePropertyMap = messagePropertyMap;
		this.messageName = messageName;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getProperty(Class<T> type, String property) {
		
		if (messagePropertyMap.size() == 0) {
			return null;
		}
		
		Map<String, Object> rootDataMap = 
			(Map<String, Object>) messagePropertyMap.get(messageName);
		
		Map<String, Object> currentDataMap = rootDataMap;
		
		String[] properties = property.split("\\.");
		if (properties.length == 0) {
			properties = new String[] {property};
		}
		
		Object value = null;
		
		for (int i = 0; i < properties.length; i++) {
			if (i == properties.length -1) {
				value = doGet(type, currentDataMap, properties[i]);
			} else {
				Object nextDataElement = currentDataMap.get(properties[i]);
				if (nextDataElement instanceof Map<?,?>) {
					currentDataMap = (Map<String, Object>) nextDataElement;
				}
			}
		}
		return value != null ? type.cast(value) : null;
	}

	private <T> T doGet(Class<T> type, Map<String, Object> dataMap, String key) {

		Object v = dataMap.get(key);
		try {
			return type.cast(v);
		} catch (Exception e) {
			return coerce(type, v);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private <T> T coerce(Class<T> type, Object v) {
		
		if (String.class.isAssignableFrom(type)) {
			try {
				return (T) String.valueOf(v);
			} catch (Exception ee) { 
			}
		} else if (Integer.class.isAssignableFrom(type)){
			try {
				return (T) Integer.valueOf(v.toString());
			} catch (Exception ee) { 
			}
		} else if (Float.class.isAssignableFrom(type)){
			try {
				return (T) Float.valueOf(v.toString());
			} catch (Exception ee) { 
			}
		} else if (Double.class.isAssignableFrom(type)){
			try {
				return (T) Double.valueOf(v.toString());
			} catch (Exception ee) { 
			}
		} 
		// TODO other primitive types here
		return null;
	}
	@Override
	public void serialize(String fileName) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(fileName), messagePropertyMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String serialize() {
		try {
			return JSONMapper.toString(this.messagePropertyMap);
		} catch (JSONMapperException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	@SuppressWarnings("unchecked")
	public void deserialize(String json) {
		try {
			messagePropertyMap = (Map<String, Object>) JSONMapper.toObject(json, Map.class);
			Set<String> keySet = messagePropertyMap.keySet();
			if (keySet != null && keySet.size() > 0) {
				messageName = keySet.iterator().next();
			}
			
		} catch (JSONMapperException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getMessageType() {
		return messageName;
	}

	@Override
	public String toString() {
		return serialize();
	}

	
}
