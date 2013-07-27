package com.brtracker.services.midlink.processing;

import com.brtracker.shared.utils.packet.TrackingMessage;

public class MessageHandlerTask implements Runnable {

	private TrackingMessage trackingMessage;
	private MessageTypeHandler messageHandler;
	
	public MessageHandlerTask(MessageTypeHandler messageHandler, TrackingMessage trackingMessage) {
		this.messageHandler = messageHandler;
		this.trackingMessage = trackingMessage;
	}
	
	@Override
	public void run() {
		messageHandler.handleMessage(trackingMessage);
	}

}
