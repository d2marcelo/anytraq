package com.seabright.shared.caching;

import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Account;
import com.brtracker.shared.utils.SystemResourcesUtil.Memcached;
import com.brtracker.shared.utils.cache.CacheClient;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.SingleObjectResponse;

public class DeviceVehicleMapCaching {

	private CacheClient cacheClient;
	private int expiration;
	private String account_url;
	
	private MyLogger logger = new MyLogger(DeviceVehicleMapCaching.class);
	
	public DeviceVehicleMapCaching()  {
		Memcached memcached = SystemResourcesUtil.Memcached.get();
		expiration = memcached.getExpirationTime("deviceVehicleMap");
		Account account = SystemResourcesUtil.Account.get();
		account_url = account.getUrl() +":"+ account.getPort() +"/";
		cacheClient = CacheClient.get();
	}
	
	public DeviceVehicleMap  getDeviceVehicleMap (String key, String endpoint) throws ServiceException, JSONMapperException{
		Object obj = cacheClient.get(key);
		if (obj == null){
			logger.logInfo(account_url + endpoint);
			String resp = ClientCall.get(account_url + endpoint);
			SingleObjectResponse  singleObj = (SingleObjectResponse) JSONMapper.toObject(resp, SingleObjectResponse.class);
			DeviceVehicleMap deviceVehicleMap =(DeviceVehicleMap) JSONMapper.toObject(singleObj.getObject(),DeviceVehicleMap.class); 
			cacheClient.set(key, expiration, deviceVehicleMap );
			return deviceVehicleMap;
		} else {
			return (DeviceVehicleMap) obj;
		}
	}
}
	