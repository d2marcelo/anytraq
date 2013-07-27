package com.anytraq.notification.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="recipient_info")
public class RecipientInfoEntity {
	
	private Long id;
	private String recipientAddress;
	private Date currentPeriodStartDate;
	private int totalMessagesSent;
	private int currentMessagesSent;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="recipient_address", nullable=false, unique=true)
	public String getRecipientAddress() {
		return recipientAddress;
	}
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}
	
	@Column(name="total_messages_sent", nullable=true, unique=false)
	public int getTotalMessagesSent() {
		return totalMessagesSent;
	}
	public void setTotalMessagesSent(int totalMessagesSent) {
		this.totalMessagesSent = totalMessagesSent;
	}
	
	@Column(name="current_messages_sent", nullable=true, unique=false)
	public int getCurrentMessagesSent() {
		return currentMessagesSent;
	}
	public void setCurrentMessagesSent(int currentMessagesSent) {
		this.currentMessagesSent = currentMessagesSent;
	}
	
	@Column(name="current_period_start_date", nullable=true, unique=false)
	public Date getCurrentPeriodStartDate() {
		return currentPeriodStartDate;
	}
	public void setCurrentPeriodStartDate(Date currentPeriodStartDate) {
		this.currentPeriodStartDate = currentPeriodStartDate;
	}
}
