package com.brtracker.shared.payload.tracking;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.brtracker.shared.payload.controller.ServiceAuth;

public class DeviceMessageAttribute extends ServiceAuth implements Serializable {

	private static final long serialVersionUID = 1L;
	private String unitAddress;
	private String messageDate;
	private Map<String, String> attributes = new HashMap<String, String>();
	
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public void put (String key , String value) {
		attributes.put(key, value);
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	
	
	
}
