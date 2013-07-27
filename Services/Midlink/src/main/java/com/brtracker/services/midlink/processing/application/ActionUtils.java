package com.brtracker.services.midlink.processing.application;

import java.util.List;
import java.util.Map;

import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.json.JSONPropertyAccessor;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class ActionUtils {

	@SuppressWarnings("unchecked")
	public static void addpendArrayOfIntegerProperties(TrackingMessage trackingMessage, 
			Map<String, Object> messageProperties, DeviceMessageAttribute serviceRequest, String property) {

		JSONPropertyAccessor jsonAccessor = new JSONPropertyAccessor();
		List<Object> targetProps = (List<Object>) jsonAccessor.getDirectProperty(List.class, property, messageProperties);
		
		if (targetProps != null) {
			
			Integer[] values = trackingMessage.getProperty(Integer[].class, property);
			if (values != null) {
				for (int i = 0; i<values.length; i++) {
					int value = values[i]-1;
					if (value>=0 && value < targetProps.size()) {
						String targetProperty = (String) targetProps.get(value);
						if (targetProperty != null && targetProperty.length() > 0) {
							serviceRequest.put(targetProperty, "true");
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void addpendArrayOfFloatProperties(TrackingMessage trackingMessage, 
			Map<String, Object> messageProperties, DeviceMessageAttribute serviceRequest, String property) {

		JSONPropertyAccessor jsonAccessor = new JSONPropertyAccessor();
		List<Object> targetProps = (List<Object>) jsonAccessor.getDirectProperty(List.class, property, messageProperties);
		
		if (targetProps != null) {
			
			Float[] values = trackingMessage.getProperty(Float[].class, property);
			if (values != null) {
				for (int i = 0; i<values.length; i++) {
					if (i < targetProps.size()) {
						String targetProperty = (String) targetProps.get(i);
						if (targetProperty != null && targetProperty.length() > 0) {
							serviceRequest.put(targetProperty, values[i].toString());
						}
					}
				}
			}
		}
	}

}
