package com.anytraq.notification.service;

public class NotificationServiceException extends RuntimeException {

	public static enum Reason {
		UNK, INVALID_RECIPIENT, AWS_DELIVERY_ERROR, SMS_INIT_ERROR, SMS_SERVER_ERROR, SMS_BAD_REQUEST   
	}
	
	private Reason reason = Reason.UNK;
	private String details;

	private static final long serialVersionUID = 1L;

	public NotificationServiceException() {
	}

	public NotificationServiceException(String message) {
		super(message);
	}

	public NotificationServiceException(String message, Reason reason) {
		this(message);
		this.reason = reason;
	}

	public NotificationServiceException(Throwable e) {
		super(e);
	}

	public NotificationServiceException(Throwable e, Reason reason) {
		this(e);
		this.reason = reason;
	}

	public NotificationServiceException(String msg, Throwable e) {
		super(msg, e);
	}

	public NotificationServiceException(String msg, Throwable e, Reason reason) {
		this(msg, e);
		this.reason = reason;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return super.toString() + " - NotificationServiceException [reason=" + reason + ", details="
				+ details + "]";
	}

}
