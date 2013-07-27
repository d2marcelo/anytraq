package com.brtracker.shared.payload.metadata.data;

public class NotificationTemplate {

	private Long id;
	private int messageTypeId;
	private String message;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getMessageTypeId() {
		return messageTypeId;
	}
	public void setMessageTypeId(int messageTypeId) {
		this.messageTypeId = messageTypeId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
