package com.brtracker.shared.payload.midlink;

import com.brtracker.shared.payload.controller.lookup.DeviceModel;

public class DeviceCommand {

	private long id;
	private String deviceAdress;
	private String command;
	private CommandRisk risk;
	private DeviceModel deviceModel;
	private CommandState state;
	private long createdOn;
	private long updatedOn;
	private int retriesDelivery;
	private int retriesVerify;
	private long scheduledFor;
	private String securityCode;
	private Long accountUserId;
	private Long affiliateUserId;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDeviceAdress() {
		return deviceAdress;
	}
	public void setDeviceAdress(String deviceAdress) {
		this.deviceAdress = deviceAdress;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public CommandRisk getRisk() {
		return risk;
	}
	public void setRisk(CommandRisk risk) {
		this.risk = risk;
	}
	public DeviceModel getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(DeviceModel deviceModel) {
		this.deviceModel = deviceModel;
	}
	public CommandState getState() {
		return state;
	}
	public void setState(CommandState state) {
		this.state = state;
	}
	public long getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}
	public long getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(long updatedOn) {
		this.updatedOn = updatedOn;
	}
	public int getRetriesDelivery() {
		return retriesDelivery;
	}
	public void setRetriesDelivery(int retriesDelivery) {
		this.retriesDelivery = retriesDelivery;
	}
	public int getRetriesVerify() {
		return retriesVerify;
	}
	public void setRetriesVerify(int retriesVerify) {
		this.retriesVerify = retriesVerify;
	}
	public long getScheduledFor() {
		return scheduledFor;
	}
	public void setScheduledFor(long scheduledFor) {
		this.scheduledFor = scheduledFor;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
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
	
	
	
}
