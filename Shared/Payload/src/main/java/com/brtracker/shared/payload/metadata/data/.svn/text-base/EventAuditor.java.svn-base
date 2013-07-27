package com.brtracker.shared.payload.metadata.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_auditor")
public class EventAuditor {
	
	private Long id;
	private String idType;
	private String idValue;
	private String eventType;
	private String input;
	private String output;
	private boolean failed;
	private Date dateCreated;
	
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="id_type" , length=100, nullable=false)
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	@Column(name="id_value" , length=100, nullable=false)
	public String getIdValue() {
		return idValue;
	}
	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}
	@Column(name="event_type" , length=100, nullable=false)
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	@Column(name="input" , length=4000)
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	@Column(name="output" , length=4000)
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	@Column(name="has_failed" )
	public boolean isFailed() {
		return failed;
	}
	public void setFailed(boolean failed) {
		this.failed = failed;
	}
	@Column(name="date_created", nullable=false)
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
