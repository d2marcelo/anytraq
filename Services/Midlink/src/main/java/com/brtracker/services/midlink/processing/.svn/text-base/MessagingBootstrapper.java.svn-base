package com.brtracker.services.midlink.processing;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.activemq.JmsSender;
import com.brtracker.shared.utils.logging.MyLogger;

public class MessagingBootstrapper implements InitializingBean, DisposableBean {

	private MyLogger logger = new MyLogger(MessagingBootstrapper.class);

	private String packetQueueName = SystemConfiguration.NETTY;
	private String deviceEventQueueName = SystemConfiguration.NETTY_DEV_EVENTS;
	private JmsSender jmsSender;
	private PacketListener packetListener;
	private DeviceEventListener deviceEventListener;
	
	public void start() throws Exception {

		try {
			logger.logInfo("Registering the packet listener");
			jmsSender.registerQueueConsumer(packetQueueName, packetListener);
			jmsSender.registerQueueConsumer(deviceEventQueueName, deviceEventListener);
			
			logger.logInfo("Packet listener successfully registered");
		} catch (Exception e) {
			logger.logError("Unexpected exception during messaging bootstrap", e);
		}

	}

	public PacketListener getPacketListener() {
		return packetListener;
	}

	public void setPacketListener(PacketListener packetListener) {
		this.packetListener = packetListener;
	}

	public void setJmsSender(JmsSender jmsSender) {
		this.jmsSender = jmsSender;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setDeviceEventListener(DeviceEventListener deviceEventListener) {
		this.deviceEventListener = deviceEventListener;
	}

}
