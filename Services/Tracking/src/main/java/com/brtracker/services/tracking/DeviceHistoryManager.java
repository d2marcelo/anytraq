package com.brtracker.services.tracking;

import java.util.List;
import com.brtracker.services.tracking.dao.DeviceHistoryDAOImpl;
import com.brtracker.shared.payload.tracking.DeviceHistoryResponse;
import com.brtracker.shared.payload.tracking.data.DeviceHistory;
import com.brtracker.shared.utils.StringUtil;
import com.brtracker.shared.utils.cache.CacheClient;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class DeviceHistoryManager extends DeviceHistoryDAOImpl {

	private MyLogger logger = new MyLogger(DeviceHistoryManager.class);
	private int expiration = 300;
	private CacheClient cacheClient = CacheClient.get();
	
	public String get(Long deviceId , String date) {
		logger.logInfo("get device history for address date:"+date);
		try {
			String key = StringUtil.generateReducedString("get"+deviceId+date, 20);
			String output =  (String) cacheClient.get(key);
			if (output == null){
			List<DeviceHistory> list = getDeviceHistory(deviceId, date);
			DeviceHistoryResponse resp = new DeviceHistoryResponse();
			resp.setDeviceHistoryList(list);
			output = JSONMapper.toString(resp);
			cacheClient.set(key, expiration, output);
			cacheClient.storeKeyasValue(this.getClass().getName(), key, expiration);
			return output;
			} else {
				return output;
			}
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		} 
	}
	
	public void clearCache() {
		cacheClient.expireKeyasValue(this.getClass().getName());
	}

	public String getDates(Long deviceId) {
		logger.logInfo("get device dates for address date:"+deviceId);
		try {
			String key = StringUtil.generateReducedString("getDates"+deviceId, 20);
			String output =  (String) cacheClient.get(key);
			if (output == null){
			List<String> list = super.getDeviceDates(deviceId);
			output = JSONMapper.toString(list);
			cacheClient.set(key, expiration, output);
			cacheClient.storeKeyasValue(this.getClass().getName(), key, expiration);
			return output;
			} else {
				return output;
			}
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		} 
	}

	

}
