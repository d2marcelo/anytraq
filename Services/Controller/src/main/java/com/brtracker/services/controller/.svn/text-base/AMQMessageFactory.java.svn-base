package com.brtracker.services.controller;

import java.util.HashMap;
import java.util.Map;

public class AMQMessageFactory {

	
	public static Map<String,String> generateTopicMessage (Long accountId, String objectType, Long id, String actionType){
		Map<String, String> map = new HashMap<String,String>();
		map.put("accountId", accountId.toString());
		map.put("objectType", objectType);
		map.put("objectId",id.toString());
		map.put("actionType",actionType);
		return map;
	}
}
