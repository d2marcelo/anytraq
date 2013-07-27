package com.seabright.shared.caching;

import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Account;
import com.brtracker.shared.utils.SystemResourcesUtil.Memcached;
import com.brtracker.shared.utils.cache.CacheClient;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;

public class VehicleCaching {

	private CacheClient cacheClient;
	private int expiration;
	private String account_url;
	
	public VehicleCaching()  {
		Memcached memcached = SystemResourcesUtil.Memcached.get();
		expiration = memcached.getExpirationTime("vehicle");
		Account account = SystemResourcesUtil.Account.get();
		account_url = account.getUrl() +":"+ account.getPort() +"/";
		cacheClient = CacheClient.get();
		
	}
	
	public Vehicle getrVehicle (String key, ControllerInterfaceLookup endpoint) throws ServiceException, JSONMapperException{
		Object obj = cacheClient.get(key);
		if (obj == null){
			String resp = ClientCall.get(account_url + endpoint);
			Vehicle vehicle = (Vehicle) JSONMapper.toObject(resp, Vehicle.class);
			cacheClient.set(key, expiration, vehicle);
			return vehicle;
		} else {
			return (Vehicle) obj;
		}
	}

}
