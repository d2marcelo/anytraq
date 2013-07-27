package com.brtracker.services.tracking.async;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.brtracker.services.tracking.DeviceMessageManager;
import com.brtracker.services.tracking.VehicleManager;
import com.brtracker.shared.utils.logging.MyLogger;

public class MidlinkMessageComsumer implements MessageListener {

	private MyLogger logger;
	private VehicleManager vehicleManager;
	private DeviceMessageManager driverMessageManager;
	
	public MidlinkMessageComsumer (){
		this.vehicleManager= new VehicleManager();
		this.driverMessageManager = new DeviceMessageManager();
		this.logger = new MyLogger(MidlinkMessageComsumer.class);
	}
	
	@Override
	public void onMessage(Message message) {
		TextMessage amqMessage = (TextMessage) message;
		logger.logInfo("Midlink message received.");
		try {
			String deviceMessageAttribute = amqMessage.getText();
			logger.logInfo("Message: "+ deviceMessageAttribute);
			String out = vehicleManager.upsert(deviceMessageAttribute);
			logger.logInfo("Upsert Output: "+ out);
			// send to async
			driverMessageManager.add(deviceMessageAttribute);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

