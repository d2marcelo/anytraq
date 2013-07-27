package com.brtracker.shared.payload.tracking.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="idle_report")
public class IdleReport {
	
	private Long id;
	private Long deviceId;
	private Long accountId;
	private Date systemTime;
	private Long dateCreated;
	private Long lastUpdated;
	private Long idleTime;
	private String latitude;
	private String longitude;
	private boolean tripStarted;
	
		
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
	
	@Column(name="system_time", nullable=false)
	public Date getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
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
	@Column(name="idle_time", nullable=false)
	public Long getIdleTime() {
		return idleTime;
	}
	public void setIdleTime(Long idleTime) {
		this.idleTime = idleTime;
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
	@Column(name="trip_started", nullable=false)
	public boolean isTripStarted() {
		return tripStarted;
	}
	public void setTripStarted(boolean tripStarted) {
		this.tripStarted = tripStarted;
	}
	
	
}
