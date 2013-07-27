package com.brtracker.services.netty;

import com.brtracker.shared.utils.activemq.JmsSender;

public class JMSConnector {

	private static JmsSender jmsSender;

	public static JmsSender getJmsSender() {
		return jmsSender;
	}

	public void setJmsSender(JmsSender jmsSender) {
		this.jmsSender = jmsSender;
	}
	
	
}
