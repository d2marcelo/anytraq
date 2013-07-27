package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.lookup.AlertCategoryType;
import com.brtracker.shared.payload.controller.lookup.AlertType;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class AlertConfigurationRequest  extends AbstractSearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accountId;
	private Long userId;
	private AlertCategoryType alertCategoryType;
	private AlertType alertType;
	private String displayName;
	
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public AlertCategoryType getAlertCategoryType() {
		return alertCategoryType;
	}
	public void setAlertCategoryType(AlertCategoryType alertCategoryType) {
		this.alertCategoryType = alertCategoryType;
	}
	public AlertType getAlertType() {
		return alertType;
	}
	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	
}
