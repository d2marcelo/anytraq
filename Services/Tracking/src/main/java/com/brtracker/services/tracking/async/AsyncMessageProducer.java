package com.brtracker.services.tracking.async;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import com.brtracker.shared.payload.async.AsyncGraphMessage;
import com.brtracker.shared.payload.async.lookup.AsyncMessageType;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.activemq.ActiveMQMessageProducer;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;

public class AsyncMessageProducer extends ActiveMQMessageProducer {


    private static final AsyncMessageProducer INSTANCE = new AsyncMessageProducer();
    
    public static AsyncMessageProducer getInstance (){
    	return INSTANCE;
    }

	public static void send (AsyncGraphMessage  asyncGraphmessage) throws JMSException, JSONMapperException {
		TextMessage txtMessage = getSession().createTextMessage();
		txtMessage.setText(JSONMapper.toString(asyncGraphmessage));
		getProducer().send(txtMessage);
	}
	
	public static void send (DeviceMessageAttribute msg, Device device, Driver driver) throws JMSException, JSONMapperException {
		AsyncGraphMessage async = new AsyncGraphMessage();
		async.setAttributes(msg.getAttributes());
		async.setDriver(driver);
		async.setDevice(device);
		async.setMessageType(AsyncMessageType.DeviceMessage);
		send(async);
	}

	
}
