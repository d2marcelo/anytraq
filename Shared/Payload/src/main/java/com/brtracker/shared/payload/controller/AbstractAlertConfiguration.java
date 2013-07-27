package com.brtracker.shared.payload.controller;

import java.util.List;
import java.util.Map;

import com.brtracker.shared.payload.controller.data.AccountUser;
import com.brtracker.shared.payload.controller.data.User;
import com.brtracker.shared.payload.controller.lookup.AlertCategoryType;
import com.brtracker.shared.payload.controller.lookup.AlertSeverityType;
import com.brtracker.shared.payload.controller.lookup.AlertType;


public abstract class AbstractAlertConfiguration {
	
	private Long id;
	private AlertSeverityType alertSeverityType;
	private AlertCategoryType alertCategoryType;
	private AlertType alertType;
	private String nameKey;
	private String messageKey;
	private String displayName;
	private Long accountId;
	private AccountUser user;
	private boolean enabled;
	private Map<String,Object> notificationConfiguration;
	private List<ConfigurationOverride> overrides;

	
	public Long getId() {
		return id;
	}
	
	
	public String getNameKey() {
		return nameKey;
	}


	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}


	public void setId(Long id) {
		this.id = id;
	}
	public AlertSeverityType getAlertSeverityType() {
		return alertSeverityType;
	}
	public void setAlertSeverityType(AlertSeverityType alertSeverityType) {
		this.alertSeverityType = alertSeverityType;
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
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public List<ConfigurationOverride> getOverrides() {
		return overrides;
	}
	public void setOverrides(List<ConfigurationOverride> overrides) {
		this.overrides = overrides;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public AlertCategoryType getAlertCategoryType() {
		return alertCategoryType;
	}
	public void setAlertCategoryType(AlertCategoryType alertCategoryType) {
		this.alertCategoryType = alertCategoryType;
	}
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}


	public AccountUser getUser() {
		return user;
	}


	public void setUser(AccountUser user) {
		this.user = user;
	}


	public Map<String, Object> getNotificationConfiguration() {
		return notificationConfiguration;
	}


	public void setNotificationConfiguration(
			Map<String, Object> notificationConfiguration) {
		this.notificationConfiguration = notificationConfiguration;
	}


	
	
	
		
}
