package com.brtracker.shared.payload.tracking.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="device_history")
public class DeviceHistory  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long deviceId;
	private String latitude;
	private String longitude;
	private Boolean moving;
	private Long dateCreated;
	private Long lastUpdated;
	private Date systemTime;
	private String attributes;
	private String searchDate;
	
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name="latitude", nullable=false , length=50)
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name="longitude", nullable=false , length=50)
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Column(name="attributes", nullable=false, length=4000)
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	@Column(name="is_moving", nullable=false)
	public Boolean isMoving() {
		return moving;
	}
	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	@Column(name="search_date", nullable=false)
	public String getSearchDate() {
		return searchDate;
	}
	
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	
	@Column(name="device_id", nullable=false)
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
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
