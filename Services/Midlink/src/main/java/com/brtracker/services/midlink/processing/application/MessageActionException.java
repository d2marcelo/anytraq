package com.brtracker.services.midlink.processing.application;

import com.brtracker.shared.utils.packet.TrackingMessage;

public class MessageActionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private TrackingMessage trackingMessage;
	
	public MessageActionException() {
		super();
	}

	public MessageActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageActionException(String message) {
		super(message);
	}

	public MessageActionException(Throwable cause) {
		super(cause);
	}

	public MessageActionException(String message, Throwable cause, TrackingMessage trackingMessage) {
		super(message, cause);
		this.trackingMessage = trackingMessage;
	}

	public MessageActionException(String message, TrackingMessage trackingMessage) {
		super(message);
		this.trackingMessage = trackingMessage;
	}

	public MessageActionException(Throwable cause, TrackingMessage trackingMessage) {
		super(cause);
		this.trackingMessage = trackingMessage;
	}

	public TrackingMessage getTrackingMessage() {
		return trackingMessage;
	}

	public void setTrackingMessage(TrackingMessage trackingMessage) {
		this.trackingMessage = trackingMessage;
	}

}
