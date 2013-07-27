package com.brtracker.shared.payload.tracking.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alert_report")
public class AlertReport {
	
	private Long id;
	private Long deviceId;
	private Long accountId;
	private Long dateCreated;
	private Date systemTime;
	private Long lastUpdated;
	private int alertType;
	private int alertSeverityType;
	private String threshold;
	private String violation;
	private String latitude;
	private String longitude;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="device_id", nullable=false)
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	@Column(name="account_id", nullable=false)
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	@Column(name="alert_type", nullable=false)
	public int getAlertType() {
		return alertType;
	}
	public void setAlertType(int alertType) {
		this.alertType = alertType;
	}
	@Column(name="alert_severity_type", nullable=false)
	public int getAlertSeverityType() {
		return alertSeverityType;
	}
	public void setAlertSeverityType(int alertSeverityType) {
		this.alertSeverityType = alertSeverityType;
	}
	
	@Column(name="latitude", nullable=false)
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name="longitude", nullable=false)
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Column(name="threshold", nullable=false)
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	
	@Column(name="violation", nullable=false)
	public String getViolation() {
		return violation;
	}
	public void setViolation(String violation) {
		this.violation = violation;
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
