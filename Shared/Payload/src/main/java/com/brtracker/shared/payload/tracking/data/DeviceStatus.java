package com.brtracker.shared.payload.tracking.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device_status")
public class DeviceStatus {

	private Long id;
	private String deviceAddress;
	private String accountName;
	private Long affiliateId;
	private String attributes;
	private Long lastUpdated;
	private Long dateCreated;
	private Date systemTime;
	
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="device_address", nullable=false)
	public String getDeviceAddress() {
		return deviceAddress;
	}
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}
	@Column(name="attributes", nullable=false, length=4000)
	public String getAttributes() {
		return attributes;
	}
	
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	@Column(name="account_name", nullable=false)
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	@Column(name="affiliate_id", nullable=false)
	public Long getAffiliateId() {
		return affiliateId;
	}
	
	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}
	@Column(name="date_created", nullable=false)
	public Long getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Column(name="last_updated", nullable=false)
	public Long getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	@Column(name="system_time", nullable=false)
	public Date getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}
	
	
	
	
	
	
}
