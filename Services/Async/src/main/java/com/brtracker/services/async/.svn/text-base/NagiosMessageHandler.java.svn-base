package com.brtracker.services.async;

import com.brtracker.shared.utils.logging.MyLogger;

public class NagiosMessageHandler implements MessageHandler  {

	private MyLogger logger = new MyLogger(NagiosMessageHandler.class, "nagiosMessageHandler");
	private UpdateNagiosMessage updateNagiosMessage;
	
	@Override
	public void handle(String message) {
		logger.logInfo("handle message: "+message);
		updateNagiosMessage.onMessage(message);
		
	}

	public UpdateNagiosMessage getUpdateNagiosMessage() {
		return updateNagiosMessage;
	}

	public void setUpdateNagiosMessage(UpdateNagiosMessage updateNagiosMessage) {
		this.updateNagiosMessage = updateNagiosMessage;
	}



	
}
