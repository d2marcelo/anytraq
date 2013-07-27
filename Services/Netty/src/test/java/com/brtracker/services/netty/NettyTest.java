package com.brtracker.services.netty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brtracker.shared.payload.controller.lookup.ManufactureType;
import com.brtracker.shared.payload.netty.DeviceMessageRequest;
import com.brtracker.shared.payload.netty.WLIUSMessageRequest;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;

public class NettyTest {

	/**
	 * @param args
	 * @throws JSONMapperException 
	 */
	public static void main(String[] args) throws JSONMapperException {
		System.out.println(String.valueOf(DateUtil.getGMTDateAndTime()));
System.exit(1);		
				
		Map<String, String> map = new HashMap<String, String>();
		List<String> list = new ArrayList<String>();
		list.add("FF");
		list.add("AA");
		map.put("payload", JSONMapper.toString(list));
		map.put("deviceId", "1231312312");
		System.out.println(JSONMapper.toString(map));
		System.exit(1);
		
		
		DeviceMessageRequest request = new DeviceMessageRequest();
		request.setManufactureType(ManufactureType.WIRELESS_LINKS);
		WLIUSMessageRequest wRequest = new WLIUSMessageRequest();
		wRequest.setDeviceAddress("013110133049");
		int[] i = new int[16];
		i[0] = 2;
		i[1] = 49;
		i[3] = 0;
		i[4] = 0;
		i[5] = 0;
		i[6] = 6;
		i[7] = -2;
		i[8] = 0;
		i[9] = -55;
		i[10] = 0;
		i[11] = 1;
		i[12] = 0;
		i[13] = 52;
		i[14] = 0;
		i[15] = 3;
		wRequest.setMessage(i);
		request.setMessageObject(JSONMapper.toString(wRequest));
		System.out.println(JSONMapper.toString(request));

	}

}
