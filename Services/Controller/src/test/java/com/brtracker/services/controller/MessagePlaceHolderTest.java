package com.brtracker.services.controller;

import com.brtracker.shared.payload.controller.data.MessagePlaceHolderMap;

public class MessagePlaceHolderTest {

	
	public static void main(String[] args){
		MessagePlaceHolderManager m = new MessagePlaceHolderManager();
		MessagePlaceHolderMap map = new MessagePlaceHolderMap();
		map.setMessageKey("ALERT.DRIVER.EMERGENCY.MESSAGE");
		map.setPlaceHolderIndex(1);
		//map.setClassPath(classPath);
		
	}
	
}
