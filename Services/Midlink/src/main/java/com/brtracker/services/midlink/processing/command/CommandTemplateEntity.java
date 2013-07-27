package com.brtracker.services.midlink.processing.command;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="command_template")
public class CommandTemplateEntity {

	private Long id;
	private String command;
	private String nameKey;
	private String deviceModel;
	private Date createdOn;
	private Date lastUpdateOn;
	private Boolean needsVerification;
	private Boolean adminRole;
	private String jsonPayload;
	private String labelKey;
	private String risk;
	private String inputParameters;
	
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
	
	@Column(name="label_key")
	public String getLabelKey() {
		return labelKey;
	}
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	
	@Column(name="risk")
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	
	@Column(name="device_model", nullable=false)
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	
	@Column(name="admin_role")
	public Boolean getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(Boolean adminRole) {
		this.adminRole = adminRole;
	}
	
	@Column(name="input_paramters", nullable=false)
	public String getInputParameters() {
		return inputParameters;
	}
	public void setInputParameters(String inputParameters) {
		this.inputParameters = inputParameters;
	}
	
	@Column(name="name_key")
	public String getNameKey() {
		return nameKey;
	}
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}
	
}
