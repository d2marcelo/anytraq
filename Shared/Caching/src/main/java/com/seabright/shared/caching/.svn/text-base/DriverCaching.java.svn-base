package com.seabright.shared.caching;

import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Account;
import com.brtracker.shared.utils.SystemResourcesUtil.Memcached;
import com.brtracker.shared.utils.cache.CacheClient;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;

public class DriverCaching {

	private CacheClient cacheClient;
	private int expiration;
	private String account_url;
	
	public DriverCaching()  {
		Memcached memcached = SystemResourcesUtil.Memcached.get();
		expiration = memcached.getExpirationTime("vehicle");
		Account account = SystemResourcesUtil.Account.get();
		account_url = account.getUrl() +":"+ account.getPort() +"/";
		cacheClient = CacheClient.get();
	}
	
	public Driver getrDriver (String key, String endpoint) throws ServiceException, JSONMapperException{
		Object obj = cacheClient.get(key);
		if (obj == null){
			String resp = ClientCall.get(account_url + endpoint);
			Driver driver = (Driver) JSONMapper.toObject(resp, Driver.class);
			cacheClient.set(key, expiration, driver);
			return driver;
		} else {
			return (Driver) obj;
		}
	}
	
	
	
}
