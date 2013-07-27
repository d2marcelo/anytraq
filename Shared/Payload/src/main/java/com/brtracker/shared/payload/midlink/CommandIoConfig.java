package com.brtracker.shared.payload.midlink;

public class CommandIoConfig {
	private Long id;
	private String nameKey;
	private String ioKey;
	private String state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameKey() {
		return nameKey;
	}
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}
	public String getIoKey() {
		return ioKey;
	}
	public void setIoKey(String ioKey) {
		this.ioKey = ioKey;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
