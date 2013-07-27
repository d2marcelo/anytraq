package com.brtracker.shared.payload.controller;

import java.util.Map;

import com.brtracker.shared.payload.controller.lookup.DeviceFeatureType;

public class DeviceFeature {

	private Map<DeviceFeatureType,String> features;

	public Map<DeviceFeatureType, String> getFeatures() {
		return features;
	}

	public void setFeatures(Map<DeviceFeatureType, String> features) {
		this.features = features;
	}
	
	
	
}
