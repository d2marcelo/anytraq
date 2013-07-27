package com.brtracker.shared.utils;

import com.brtracker.shared.utils.activemq.JmsSender;
import com.brtracker.shared.utils.activemq.JmsUtilsException;


public class CheckJmsTemplate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JmsSender jmst = new JmsSender();
		try {
			jmst.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			jmst.sendTextMessage("Hello there", "test-key");
		} catch (JmsUtilsException e) {
			e.printStackTrace();
		}
		
		System.out.println("asdfasdfads");
		
		try {
			jmst.sendTextMessage("Hello there 2 ", "test-key");
		} catch (JmsUtilsException e) {
			e.printStackTrace();
		}

		System.out.println("asdfasdfads");
		
		try {
			jmst.sendTextMessage("Hello there 2 ", "test-key");
		} catch (JmsUtilsException e) {
			e.printStackTrace();
		}


	}

}
