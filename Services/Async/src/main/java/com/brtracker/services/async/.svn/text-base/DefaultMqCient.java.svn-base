package com.brtracker.services.async;

import org.springframework.beans.factory.DisposableBean;
import com.brtracker.shared.utils.activemq.JmsSender;
import com.brtracker.shared.utils.activemq.JmsUtilsException;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;

public class DefaultMqCient implements MqClient, DisposableBean {
	
	private JmsSender jmsSender;
	private MyLogger logger = new MyLogger(DefaultMqCient.class, this.getClass().getSimpleName());

	public void setJmsSender(JmsSender jmsSender) {
		this.jmsSender = jmsSender;
	}
	@Override
	public void sendMessageToQueue(Object message, String queue) {
		String payload;
		try {
			payload = JSONMapper.toString(message);
			jmsSender.sendTextMessage(payload, queue);
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
		} catch (JmsUtilsException e) {
			logger.logError(e.getMessage(), e);
		}	
		
	}
	@Override
	public void sendMessageToTopic(Object message, String topic) {
		try {
		String payload;
		payload = JSONMapper.toString(message);
		jmsSender.sendTextMessageToTopic(payload, topic);
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
		} catch (JmsUtilsException e) {
			logger.logError(e.getMessage(), e);
		}
	}
	@Override
	public void destroy() throws Exception {
		jmsSender.destroy();
	}

}
