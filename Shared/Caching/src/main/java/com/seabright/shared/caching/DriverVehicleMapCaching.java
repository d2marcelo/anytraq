package com.seabright.shared.caching;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Account;
import com.brtracker.shared.utils.SystemResourcesUtil.Memcached;
import com.brtracker.shared.utils.cache.CacheClient;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.SingleObjectResponse;

public class DriverVehicleMapCaching {

	private CacheClient cacheClient;
	private int expiration;
	private String account_url;
	
	public DriverVehicleMapCaching()  {
		Memcached memcached = SystemResourcesUtil.Memcached.get();
		expiration = memcached.getExpirationTime("driverVehicleMap");
		Account account = SystemResourcesUtil.Account.get();
		account_url = account.getUrl() +":"+ account.getPort() +"/";
		cacheClient = CacheClient.get();
	}
	
	public DriverVehicleMap  getDriverVehicleMap (String key, String endpoint) throws ServiceException, JSONMapperException{
		Object obj = cacheClient.get(key);
		if (obj == null){
			String resp = ClientCall.get(account_url + endpoint);
			SingleObjectResponse  singleObj = (SingleObjectResponse) JSONMapper.toObject(resp, SingleObjectResponse.class);
			DriverVehicleMap driverVehicleMap =(DriverVehicleMap) JSONMapper.toObject(singleObj.getObject(),DriverVehicleMap.class); 
			cacheClient.set(key, expiration, driverVehicleMap);
			return driverVehicleMap;
		} else {
			return (DriverVehicleMap) obj;
		}
	}
}
