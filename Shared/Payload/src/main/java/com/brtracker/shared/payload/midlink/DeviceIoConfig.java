package com.brtracker.shared.payload.midlink;

public class DeviceIoConfig {
	
	private String deviceAddress;
	private int commandIoConfigId;
	
	public String getDeviceAddress() {
		return deviceAddress;
	}
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}
	public int getCommandIoConfigId() {
		return commandIoConfigId;
	}
	public void setCommandIoConfigId(int commandIoConfigId) {
		this.commandIoConfigId = commandIoConfigId;
	}
}
