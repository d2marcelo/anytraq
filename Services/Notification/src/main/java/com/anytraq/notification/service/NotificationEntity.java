package com.anytraq.notification.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notification_message")
public class NotificationEntity {
	
	public final static String PENDING_STATE = "PENDING"; 
	public final static String RETRY_STATE = "RETRY";
	public final static String SUCCESS_STATE = "SUCCESS";
	public final static String FAILED_STATE = "FAILED";
	public final static String EXCEEDED_QUOTA_STATE = "EXCEEDED_QUOTA";
	
	private Long id;
	private String templateKey;
	private String locale;
	private String type;
	private String recipient;
	private String payload;
	private String state;
	private int retries;
	private Date scheduledTime;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="template_key", nullable=false, length=64)
	public String getTemplateKey() {
		return templateKey;
	}
	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}
	
	@Column(name="locale", nullable=false, length=16)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	@Column(name="type", nullable=false, length=32)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="payload", nullable=true, length=4000)
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	@Column(name="state", nullable=false, length=64)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="retries", nullable=true)
	public int getRetries() {
		return retries;
	}
	public void setRetries(int retries) {
		this.retries = retries;
	}
	@Column(name="scheduled_time", nullable=false)
	public Date getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	
	public String buildUniqueKey() {
		return templateKey + ":" + locale + ":" + type;
	}
	
	@Column(name="recipient", nullable=false, unique=false)
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
}
