package com.brtracker.shared.payload.tracking.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="driver_behavior")
public class DriverBehavior  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long deviceId;
	private String stats;
	private String latitude;
	private String longitude;
	private Long dateCreated;
	private Long lastUpdated;
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
	
	@Column(name="stats", nullable=false, length=4000)
	public String getStats() {
		return stats;
	}
	public void setStats(String stats) {
		this.stats = stats;
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
	@Column(name="device_id", nullable=false)
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	
	
		
}
