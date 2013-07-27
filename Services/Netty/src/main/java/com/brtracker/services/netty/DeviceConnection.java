package com.brtracker.services.netty;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.netty.channel.MessageEvent;

import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class DeviceConnection {

	private static MyLogger logger = new MyLogger(DeviceConnection.class);
	private static Map<String, MessageEvent> map = new HashMap<String, MessageEvent>();
	private static Map<String, String> devicePool = new HashMap<String, String>();
	private static Map<String, String> deviceInfo = new HashMap<String, String>();
	
	
	
	public static void put (String unitAddress, MessageEvent event) {
		map.put(unitAddress, event);
	}
	
	public static void put (String unitAddress, String message) {
		devicePool.put(unitAddress, new Date().toString());
		Map<String,String> map = new HashMap<String,String>();
		map.put("date", new Date().toString());
		map.put("message",message);
		try {
			deviceInfo.put(unitAddress, JSONMapper.toString(map));
		} catch (JSONMapperException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getDeviceInfo (){
		try {
			return JSONMapper.toString(deviceInfo);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	public static String getDevicePool (){
		try {
			return JSONMapper.toString(devicePool);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	public static MessageEvent getMessageEvent (String unitAddress) {
		logger.logInfo("Get MessageEvent for unit:"+unitAddress);
		logger.logInfo("Obj:"+map.containsKey(unitAddress));
		return map.get(unitAddress);
	}
	
	public  static String getUnitAddress(SocketAddress remoteAddress) {
		for (Map.Entry<String, MessageEvent> m : map.entrySet()){
			if (m.getValue().getRemoteAddress().equals(remoteAddress)) return m.getKey();
		}
		return null;
	}
}
