package com.brtracker.shared.payload.async;

import java.util.Map;
import com.brtracker.shared.payload.async.lookup.AsyncMessageType;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.Driver;

public class AsyncGraphMessage {

	private Driver driver;
	private Device device;
	private AsyncMessageType messageType;
	private Map<String,String> attributes;
	
	public AsyncGraphMessage(){};
	
	public AsyncGraphMessage(Driver driver,AsyncMessageType messageType,Map<String,String> attributes){
		this.driver= driver;
		this.messageType = messageType;
		this.attributes = attributes;
	}
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public AsyncMessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(AsyncMessageType messageType) {
		this.messageType = messageType;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
	
	
}
