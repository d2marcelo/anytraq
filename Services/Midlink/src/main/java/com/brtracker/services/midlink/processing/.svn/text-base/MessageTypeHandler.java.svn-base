package com.brtracker.services.midlink.processing;

import java.util.List;

import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class MessageTypeHandler {
	
	private MyLogger logger = new MyLogger(MessageTypeHandler.class);
	private List<MessageAction> actions;
	
	public void handleMessage(TrackingMessage trackingMessage) {
		
		if (validMessage(trackingMessage)) {
			
			for (MessageAction action: actions) {
				try {
					
					action.execute(trackingMessage);
					
				} catch (Exception e) {
					logger.logError("Unexpected exception while action " + 
							action.getName() + " execution on message " + trackingMessage, e);
				}
			}
		}
	}

	private boolean validMessage(TrackingMessage trackingMessage) {
		// check for message duplication, validity in general
		return true;
	}

	public void setActions(List<MessageAction> actions) {
		this.actions = actions;
	}
}
