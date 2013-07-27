package com.brtracker.shared.payload.tracking.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="message_audit")
public class MessageAudit  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String unitAddress;
	private String rawMessage;
	private String parsedMessage;
	private String messageType;
	private Date dateCreated;
	
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="unit_address", nullable=false)
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	@Column(name="raw_message", length=4000, nullable=false)
	public String getRawMessage() {
		return rawMessage;
	}
	public void setRawMessage(String rawMessage) {
		this.rawMessage = rawMessage;
	}
	@Column(name="parsed_message", length=4000, nullable=false)
	public String getParsedMessage() {
		return parsedMessage;
	}
	public void setParsedMessage(String parsedMessage) {
		this.parsedMessage = parsedMessage;
	}
	@Column(name="message_type", nullable=false)
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	@Column(name="date_created", nullable=false)
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	
}
