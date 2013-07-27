package com.brtracker.services.midlink.processing.command;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device_io_config")
public class CommandIoConfigEntity {

	private Long id;
	private String nameKey;
	private String ioKey;
	private String state;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="name_key")
	public String getNameKey() {
		return nameKey;
	}
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}
	
	@Column(name="io_key")
	public String getIoKey() {
		return ioKey;
	}
	public void setIoKey(String ioKey) {
		this.ioKey = ioKey;
	}
	
}
