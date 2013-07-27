package com.brtracker.services.midlink.processing;

import com.brtracker.shared.utils.activemq.JmsSender;
import com.brtracker.shared.utils.json.JSONMapper;

public class DefaultMqCient implements MqClient {
	
	private JmsSender jmsSender;
	
	public void setJmsSender(JmsSender jmsSender) {
		this.jmsSender = jmsSender;
	}
	@Override
	public void sendMessageToQueue(Object message, String queue)
			throws Exception {

		String payload;
		payload = JSONMapper.toString(message);
		jmsSender.sendTextMessage(payload, queue);
		
	}
	@Override
	public void sendMessageToTopic(Object message, String topic)
			throws Exception {
		String payload;
		payload = JSONMapper.toString(message);
		jmsSender.sendTextMessageToTopic(payload, topic);
	}

}
