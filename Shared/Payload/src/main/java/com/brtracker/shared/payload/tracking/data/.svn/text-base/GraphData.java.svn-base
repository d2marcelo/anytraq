package com.brtracker.shared.payload.tracking.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="graph_data")
public class GraphData  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long deviceId;
	private int graphDataType;
	private Double xData;
	private Double yData;
	private String latitude;
	private String longitude;
	private String course;
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
	
	@Column(name="graph_data_type", nullable=false)
	public int getGraphDataType() {
		return graphDataType;
	}
	public void setGraphDataType(int graphDataType) {
		this.graphDataType = graphDataType;
	}
	
	@Column(name="x_data" , nullable=false )
	public Double getxData() {
		return xData;
	}
	public void setxData(Double xData) {
		this.xData = xData;
	}
	
	@Column(name="y_data", nullable=false )
	public Double getyData() {
		return yData;
	}
	public void setyData(Double yData) {
		this.yData = yData;
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
	@Column(name="device_id", nullable=false)
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	@Column(name="course")
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
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
