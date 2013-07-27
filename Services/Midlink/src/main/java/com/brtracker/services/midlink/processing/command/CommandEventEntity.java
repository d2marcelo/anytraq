package com.brtracker.services.midlink.processing.command;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="command_event")
public class CommandEventEntity {

	private Long id;
	private String command;
	private String deviceModel;
	private String deviceId;
	private Date createdOn;
	private Date lastUpdateOn;
	private Boolean needsVerification;
	private String jsonPayload;
	private String rawPayload;
	private String state;
	private Integer retriesDelivery;
	private Integer retriesVerify;
	private Date scheduledFor;
	private String failureReason;
	private Long accountUserId;
	private Long affiliateUserId;
	private String securityCode;
	private int messageId;
	private String risk;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="command", nullable=false)
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	
	@Column(name="created_on")
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name="last_updated_on")
	public Date getLastUpdateOn() {
		return lastUpdateOn;
	}
	public void setLastUpdateOn(Date lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}
	
	@Column(name="needs_verification")
	public Boolean getNeedsVerification() {
		return needsVerification;
	}
	public void setNeedsVerification(Boolean needsVerification) {
		this.needsVerification = needsVerification;
	}
	
	@Column(name="json_payload")
	public String getJsonPayload() {
		return jsonPayload;
	}
	public void setJsonPayload(String jsonPayload) {
		this.jsonPayload = jsonPayload;
	}
	
	@Column(name="raw_payload")
	public String getRawPayload() {
		return rawPayload;
	}
	public void setRawPayload(String rawPayload) {
		this.rawPayload = rawPayload;
	}
	
	@Column(name="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="retries_delivery")
	public Integer getRetriesDelivery() {
		return retriesDelivery;
	}
	public void setRetriesDelivery(Integer retriesDelivery) {
		this.retriesDelivery = retriesDelivery;
	}
	public int incrementRetriesDelivery() {
		this.retriesDelivery++;
		return this.retriesDelivery;
	}
	
	@Column(name="retries_verify")
	public Integer getRetriesVerify() {
		return retriesVerify;
	}
	public void setRetriesVerify(Integer retriesVerify) {
		this.retriesVerify = retriesVerify;
	}
	
	@Column(name="scheduled_for")
	public Date getScheduledFor() {
		return scheduledFor;
	}
	public void setScheduledFor(Date scheduledFor) {
		this.scheduledFor = scheduledFor;
	}
	
	@Column(name="failure_reason")
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	
	@Column(name="security_code")
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
	@Column(name="device_id")
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name="message_id")
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	@Column(name="device_model")
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	@Column(name="account_user_id")
	public Long getAccountUserId() {
		return accountUserId;
	}
	public void setAccountUserId(Long accountUserId) {
		this.accountUserId = accountUserId;
	}
	@Column(name="affiliate_user_id")
	public Long getAffiliateUserId() {
		return affiliateUserId;
	}
	public void setAffiliateUserId(Long affiliateUserId) {
		this.affiliateUserId = affiliateUserId;
	}

	@Column(name="risk")
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	
}
