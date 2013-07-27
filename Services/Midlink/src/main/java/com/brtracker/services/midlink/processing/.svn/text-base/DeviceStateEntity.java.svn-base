package com.brtracker.services.midlink.processing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device_state")
public class DeviceStateEntity {

	private Long id;
	private String unitAddress;
	private Date lastActivityTs;
	private String state;
	private String jsonDetails;
	private Double lat;
	private Double lng;
	private Double speed;
	private Integer satellites = -1;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="unit_address", nullable=false, length=256, unique=true)
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	
	@Column(name="last_activity_ts", nullable=false)
	public Date getLastActivityTs() {
		return lastActivityTs;
	}
	public void setLastActivityTs(Date lastActivityTs) {
		this.lastActivityTs = lastActivityTs;
	}
	
	@Column(name="state", nullable=false, length=64)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="jsonDetails", nullable=true, length=2048)
	public String getJsonDetails() {
		return jsonDetails;
	}
	public void setJsonDetails(String jsonDetails) {
		this.jsonDetails = jsonDetails;
	}
	
	@Column(name="lat", nullable=false)
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	@Column(name="lng", nullable=false)
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	@Column(name="speed", nullable=false)
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	@Column(name="satellites")
	public Integer getSatellites() {
		return satellites;
	}
	public void setSatellites(Integer satellites) {
		if (satellites == null) {
			this.satellites = -1;
		} else {
			this.satellites = satellites;
		}
	}
}
