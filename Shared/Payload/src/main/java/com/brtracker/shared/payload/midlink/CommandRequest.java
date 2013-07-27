package com.brtracker.shared.payload.midlink;

import com.brtracker.shared.payload.controller.lookup.DeviceModel;

public class CommandRequest {

	private Long commandTemplateId;
	private String securityCode;
	private DeviceModel deviceModel;
	private String deviceAddress;
	private Long accountUserId;
	private Long affiliateUserId;
	private String inputParams;
	private String state;
	
	
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
	public Long getCommandTemplateId() {
		return commandTemplateId;
	}
	public void setCommandTemplateId(Long commandTemplateId) {
		this.commandTemplateId = commandTemplateId;
	}
	public DeviceModel getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(DeviceModel deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceAddress() {
		return deviceAddress;
	}
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}
	public Long getAccountUserId() {
		return accountUserId;
	}
	public void setAccountUserId(Long accountUserId) {
		this.accountUserId = accountUserId;
	}
	public Long getAffiliateUserId() {
		return affiliateUserId;
	}
	public void setAffiliateUserId(Long affiliateUserId) {
		this.affiliateUserId = affiliateUserId;
	}
	public String getInputParams() {
		return inputParams;
	}
	public void setInputParams(String inputParams) {
		this.inputParams = inputParams;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
