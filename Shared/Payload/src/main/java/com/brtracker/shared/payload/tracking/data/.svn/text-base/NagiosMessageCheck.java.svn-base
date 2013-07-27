package com.brtracker.shared.payload.tracking.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nagios_msg_check")
public class NagiosMessageCheck {
	
	private Long id;
	private String nagiosKey;
	private boolean checked;
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
	
	@Column(name="nagiosKey", nullable=false)
	public String getNagiosKey() {
		return nagiosKey;
	}
	public void setNagiosKey(String nagiosKey) {
		this.nagiosKey = nagiosKey;
	}
	@Column(name="checked", nullable=false)
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Column(name="date_created", nullable=false)
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
