package com.brtracker.services.midlink.processing.command;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.brtracker.shared.payload.controller.lookup.DeviceModel;
import com.brtracker.shared.payload.midlink.CommandRequest;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.json.JsonUtils;
import com.brtracker.shared.utils.logging.MyLogger;

public class DeviceCommandListener implements MessageListener {

	private MyLogger logger = new MyLogger(DeviceCommandListener.class);
	private CommandService commandService;
	
	@Override
	public void onMessage(Message amqMessage) {
		
		logger.logInfo("DeviceCommandListener on message");
		try {
			
			TextMessage mqMessage = (TextMessage) amqMessage;
			String messageText = mqMessage.getText();
			Json jsonMessage = Json.read(messageText);
			
			CommandRequest request = createCommandRequest(jsonMessage);
			
			commandService.processCommand(request);
			
		} catch (Exception e) {
			logger.logError("DeviceCommandListener unexpected exception", e);
		}
		
	}

	private CommandRequest createCommandRequest(Json jsonMessage) {
		
		CommandRequest request = new CommandRequest();
		
		String deviceModel = JsonUtils.asSafeString(jsonMessage, "deviceModel");
		if (deviceModel != null) {
			request.setDeviceModel(DeviceModel.valueOf(deviceModel));
		}
		Long templateId = JsonUtils.asSafeLong(jsonMessage, "commandTemplateId");
		if (templateId != null) {
			request.setCommandTemplateId(templateId);
		}
		Long accountUserId = JsonUtils.asSafeLong(jsonMessage, "accountUserId");
		if (accountUserId != null) {
			request.setAccountUserId(accountUserId);
		}
		Long affiliateUserId = JsonUtils.asSafeLong(jsonMessage, "affiliateUserId");
		if (affiliateUserId != null) {
			request.setAffiliateUserId(affiliateUserId);
		}
		String securityCode = JsonUtils.asSafeString(jsonMessage, "securityCode");
		if (securityCode != null) {
			request.setSecurityCode(securityCode);
		}
		String deviceId = jsonMessage.at("deviceId").asString();
		if (deviceId != null) {
			request.setDeviceAddress(deviceId);
		}
		
		return request;
	}
	
	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}

}
