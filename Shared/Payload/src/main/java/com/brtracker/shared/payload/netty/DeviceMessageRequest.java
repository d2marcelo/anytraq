package com.brtracker.shared.payload.netty;

import com.brtracker.shared.payload.controller.lookup.ManufactureType;

public class DeviceMessageRequest {

	private ManufactureType manufactureType;
	private String messageObject;
	
	public ManufactureType getManufactureType() {
		return manufactureType;
	}
	public void setManufactureType(ManufactureType manufactureType) {
		this.manufactureType = manufactureType;
	}
	public String getMessageObject() {
		return messageObject;
	}
	public void setMessageObject(String messageObject) {
		this.messageObject = messageObject;
	}
	
	
	
	
}
