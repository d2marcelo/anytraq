package com.brtracker.shared.payload.controller.data;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="alert_configuration")
public class AlertConfiguration {

	private Long id;
	private Long accountId;
	private AccountUser user;
	private Alert alertType;
	private int alertSeverityTypeId;
	private boolean enabled;
	private String value;
	private boolean overrideEnabled;
	private String overrides;
	private String displayName;
	private String notification;
	@SuppressWarnings("unused")
	private Timestamp lastUpdated;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="account_id", nullable=false)
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	@ManyToOne
    @JoinColumn(name="alert_type_id", nullable=false)
	public Alert getAlertType() {
		return alertType;
	}
	public void setAlertType(Alert alertType) {
		this.alertType = alertType;
	}
	@Column(name="is_enabled")
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Column(name="value", nullable=false)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name="overrides",length=4000)
	public String getOverrides() {
		return overrides;
	}
	public void setOverrides(String overrides) {
		this.overrides = overrides;
	}
	@Column(name="last_updated", nullable=false)
	public Timestamp getLastUpdated() {
		return new Timestamp(new Date().getTime());
	}
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	@Column(name="override_enabled", nullable=false)
	public boolean isOverrideEnabled() {
		return overrideEnabled;
	}
	public void setOverrideEnabled(boolean overrideEnabled) {
		this.overrideEnabled = overrideEnabled;
	}
	@Column(name="display_name", nullable=false)
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(name="alert_severity_type_id", nullable=false)
	public int getAlertSeverityTypeId() {
		return alertSeverityTypeId;
	}
	public void setAlertSeverityTypeId(int alertSeverityTypeId) {
		this.alertSeverityTypeId = alertSeverityTypeId;
	}
	
	@Column(name="notification")
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	public AccountUser getUser() {
		return user;
	}
	public void setUser(AccountUser user) {
		this.user = user;
	}
	
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getAlertSeverityTypeId());
		b.append(this.getDisplayName());
		b.append(this.getNotification());
		b.append(this.getOverrides());
		b.append(this.getValue());
		b.append(this.getAccountId());
		b.append(this.getId());
		AccountUser usr = getUser();
		if (usr != null)
		b.append(usr.toString());
		Alert alert = this.getAlertType();
		if (alert != null)
		b.append(alert.toString());
		return b.toString();
	}
	
}
