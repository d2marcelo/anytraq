package com.brtracker.services.async;

import java.util.HashMap;
import java.util.Map;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.logging.MyLogger;

public class AsyncMidlinkMessageListener implements MessageListener {

	private MyLogger logger;
	private Map<String, MessageHandler> handlers;
		
	
	public AsyncMidlinkMessageListener(){
		logger = new MyLogger(AsyncMidlinkMessageListener.class);
		handlers = new HashMap<String, MessageHandler>();
	}
	
	
	public void onMessage(Message message) {
		try {
			logger.logInfo("Message Received...");
			if (message instanceof TextMessage) {
				logger.logInfo("Setting MessageBodyContent");
				TextMessage amqMessage = (TextMessage) message;
				String textMessage = amqMessage.getText();
				MessageHandler handler = lookupHandler(textMessage);
				if (handler != null) {
					handler.handle(textMessage);
				} else {
					logger.logInfo("Async message handler not found for message " + textMessage);
				}
			} else {
				logger.logError("Message not supported..");
			}
		} catch (Exception e) {
			logger.logError("Unexpected exception " , e);
		}
	}

	private MessageHandler lookupHandler(String myMessage) throws Exception {
		logger.logInfo("Get Handler for message: "+myMessage);
		DeviceMessageAttribute deviceMessageAttribute = (DeviceMessageAttribute) JSONMapper.toObject(myMessage, DeviceMessageAttribute.class);
		Map<String, String> attributes = deviceMessageAttribute.getAttributes();
		String messageType = (String) attributes.get("messageType");
		logger.logInfo("Get Handler for messageType:"+messageType +"="+handlers.size());
		return handlers.get(messageType);
	}

	public void setHandlers(Map<String, MessageHandler> handlers) {
		this.handlers = handlers;
	}

}
