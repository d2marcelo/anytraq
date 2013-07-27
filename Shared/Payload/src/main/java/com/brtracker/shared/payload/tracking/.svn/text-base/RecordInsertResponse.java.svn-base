package com.brtracker.shared.payload.tracking;

public class RecordInsertResponse {

	private boolean returnValue;
	private String message;
	private String stackTrace;
	private Long lastId;
	
	public RecordInsertResponse (){};
	
	public RecordInsertResponse (boolean returnValue, String message, String stackTrace) {
		this.message = message;
		this.returnValue = returnValue;
		this.stackTrace = stackTrace;
	}
	
	public RecordInsertResponse (boolean returnValue, String message, Long lastId) {
		this.message = message;
		this.returnValue = returnValue;
		this.lastId = lastId;
		this.stackTrace = "N/A";
	}
	
	public RecordInsertResponse (boolean returnValue, String message) {
		this.message = message;
		this.returnValue = returnValue;
		this.stackTrace = "N/A";
	}
	
	
	public boolean isReturnValue() {
		return returnValue;
	}

	public void setReturnValue(boolean returnValue) {
		this.returnValue = returnValue;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public Long getLastId() {
		return lastId;
	}

	public void setLastId(Long lastId) {
		this.lastId = lastId;
	}

	


	
}
