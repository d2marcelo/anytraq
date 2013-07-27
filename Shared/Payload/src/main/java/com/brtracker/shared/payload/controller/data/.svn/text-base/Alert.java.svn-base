package com.brtracker.shared.payload.controller.data;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alert")
public class Alert {

	private int id;
	private int alertCategoryId;
	private int alertTypeId;
	private String nameKey;
	private String descriptionKey;
	@SuppressWarnings("unused")
	private Timestamp lastUpdated;
	private int defaultSeverityType;
	private String defaultMessageKey;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="alert_type_id", nullable=false)
	public int getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(int alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
	
	@Column(name="last_updated", nullable=false)
	public Timestamp getLastUpdated() {
		return new Timestamp(new Date().getTime());
	}
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	@Column(name="alert_category_id", nullable=false)
	public int getAlertCategoryId() {
		return alertCategoryId;
	}
	public void setAlertCategoryId(int alertCategoryId) {
		this.alertCategoryId = alertCategoryId;
	}
	@Column(name="name_key", nullable=false)
	public String getNameKey() {
		return nameKey;
	}
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}
	@Column(name="description_key", nullable=false)
	public String getDescriptionKey() {
		return descriptionKey;
	}
	public void setDescriptionKey(String descriptionKey) {
		this.descriptionKey = descriptionKey;
	}
	@Column(name="default_severity_type", nullable=false)
	public int getDefaultSeverityType() {
		return defaultSeverityType;
	}
	public void setDefaultSeverityType(int defaultSeverityType) {
		this.defaultSeverityType = defaultSeverityType;
	}
	@Column(name="default_message_key", nullable=false)
	public String getDefaultMessageKey() {
		return defaultMessageKey;
	}
	public void setDefaultMessageKey(String defaultMessageKey) {
		this.defaultMessageKey = defaultMessageKey;
	}
	
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getAlertCategoryId());
		b.append(this.getAlertTypeId());
		b.append(this.getDefaultMessageKey());
		b.append(this.getDefaultSeverityType());
		b.append(this.getDescriptionKey());
		b.append(this.getId());
		b.append(this.getNameKey());
		return b.toString();
	}
	
}
