package com.brtracker.shared.payload.tracking.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alert_message")
public class AlertMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private int alertTypeId;
	private int alertSeverityId;
	private Long alertConfigId; 
	private Boolean read;
	private Boolean acknowledged;
	private Long acknUserId;
	private Long accountId;
	private Long userId;
	private Long driverId;
	private Long vehicleId;
	private String displayName;
	private String nameKey;
	private String messageKey;
	private String placeHolders;
	private Long dateCreated;
	private Boolean deleted;
	private Boolean cleared;
	private Long lastUpdated;
	private Date systemTime;
	private Boolean showUi;
	
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="display_name")
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(name="alert_type_id", nullable=false)
	public int getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(int alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
	@Column(name="alert_severity_id", nullable=false)
	public int getAlertSeverityId() {
		return alertSeverityId;
	}
	public void setAlertSeverityId(int alertSeverityId) {
		this.alertSeverityId = alertSeverityId;
	}
	@Column(name="is_read")
	public Boolean isRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
	@Column(name="is_acknowledged")
	public Boolean isAcknowledged() {
		return acknowledged;
	}
	public void setAcknowledged(Boolean acknowledged) {
		this.acknowledged = acknowledged;
	}
	@Column(name="ack_user_id")
	public Long getAcknUserId() {
		return acknUserId;
	}
	public void setAcknUserId(Long acknUserId) {
		this.acknUserId = acknUserId;
	}
	@Column(name="driver_id", nullable=false)
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	@Column(name="vehicle_id", nullable=false)
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	@Column(name="message_key", nullable=false)
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	@Column(name="place_holders", nullable=false, length=4000)
	public String getPlaceHolders() {
		return placeHolders;
	}
	public void setPlaceHolders(String placeHolders) {
		this.placeHolders = placeHolders;
	}
	@Column(name="account_id", nullable=false)
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name="name_key", nullable=false)
	public String getNameKey() {
		return nameKey;
	}
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}
	@Column(name="is_deleted")
	public Boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	@Column(name="is_clear")
	public Boolean isCleared() {
		return cleared;
	}
	public void setCleared(Boolean cleared) {
		this.cleared = cleared;
	}
	
	@Column(name="alert_config_id")
	public Long getAlertConfigId() {
		return alertConfigId;
	}
	public void setAlertConfigId(Long alertConfigId) {
		this.alertConfigId = alertConfigId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	@Column(name="show_ui", nullable=false)
	public Boolean getShowUi() {
		return showUi;
	}
	public void setShowUi(Boolean showUi) {
		this.showUi = showUi;
	}
	
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getId());
		b.append(this.getAlertSeverityId());
		b.append(this.getAlertTypeId());
		b.append(this.getDisplayName());
		b.append(this.getMessageKey());
		b.append(this.getNameKey());
		b.append(this.getPlaceHolders());
		b.append(this.getAccountId());
		b.append(this.getAcknUserId());
		b.append(this.getAlertConfigId());
		b.append(this.getDateCreated());
		b.append(this.getDriverId());
		b.append(this.getLastUpdated());
		b.append(this.getShowUi());
		b.append(this.getSystemTime());
		b.append(this.getUserId());
		b.append(this.getVehicleId());
		return b.toString();
	}
	
}
