package com.brtracker.services.tracking.async;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import com.brtracker.services.tracking.VehicleManager;
import com.brtracker.shared.utils.logging.MyLogger;

public class AccountMessageComsumer implements MessageListener {

	private MyLogger logger = new MyLogger(AccountMessageComsumer.class);
	private VehicleManager vehicleManager;
	
	public AccountMessageComsumer (){
		this.vehicleManager= new VehicleManager();
	}
	
	@Override
	public void onMessage(Message message) {
		TextMessage amqMessage = (TextMessage) message;
		logger.logInfo("Account message topic received.");
		try {
			String deviceMessageAttribute = amqMessage.getText();
			logger.logInfo("Message: "+ deviceMessageAttribute);
			vehicleManager.update(deviceMessageAttribute);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

