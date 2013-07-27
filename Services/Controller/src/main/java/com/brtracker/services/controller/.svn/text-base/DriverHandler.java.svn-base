package com.brtracker.services.controller;

import java.util.Date;
import java.util.List;
import com.brtracker.services.controller.dao.DeviceVehicleMapDAOImpl;
import com.brtracker.services.controller.dao.DriverDAOImpl;
import com.brtracker.shared.payload.controller.DriverRequest;
import com.brtracker.shared.payload.controller.DriverResponse;
import com.brtracker.shared.payload.controller.DriverSearchRequest;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.utils.StringUtil;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Memcached;
import com.brtracker.shared.utils.activemq.TopicName;
import com.brtracker.shared.utils.cache.CacheClient;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class DriverManager.
 */
public class DriverHandler  {

	
	/** The logger. */
	private MyLogger logger = new MyLogger(DriverHandler.class);
	private DeviceVehicleMapDAOImpl deviceVehicleDao = new DeviceVehicleMapDAOImpl();
	private DriverDAOImpl driverDao = new DriverDAOImpl();
	private Memcached memcached = SystemResourcesUtil.Memcached.get();
	int expiration = memcached.getExpirationTime("driver");
	private CacheClient cacheClient = CacheClient.get();

	/**
	 * Adds the.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String add (String payload) {
		try {
			Driver driver = (Driver) JSONMapper.toObject(payload, Driver.class);
			driver.setDateCreated(new Date(System.currentTimeMillis()));
			driverDao.add(driver);
			cacheClient.expireKeyasValue(this.getClass().getName());
			return  ServiceResponse.getServiceResponse(true, "Driver added successfuly");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	/**
	 * Update.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String update (String payload) {
		try {
			Driver newDriver = (Driver) JSONMapper.toObject(payload, Driver.class);
			Driver currDriver;
			if (newDriver.getId() == null){
				if (newDriver.getUniqueKey() == null){
				  return  ServiceResponse.getServiceResponse(false, "You must send at least the driver id or driver social security number.");
				}
				else {
					currDriver = driverDao.getDriver(newDriver.getUniqueKey());
				}
			} else {
				currDriver = driverDao.getDriver(newDriver.getId());
			}
			newDriver.setDateCreated(currDriver.getDateCreated());
			driverDao.update(newDriver);
			try {
				MqClientFactory.mqClient.sendMessageToTopic(AMQMessageFactory.generateTopicMessage(newDriver.getAccount().getId(), "DRIVER", newDriver.getId(), "UPDATE"), TopicName.ACCOUNT.name());
			} catch (Exception e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
			cacheClient.expireKeyasValue(this.getClass().getName());
			return  ServiceResponse.getServiceResponse(true, "Driver added successfuly");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	/**
	 * Delete.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	
	
	public String delete (String payload) {
		try {
			Driver driver = (Driver) JSONMapper.toObject(payload, Driver.class);
			Long driverId = driver.getId();
			String uniqueKey = driver.getUniqueKey();
			if (driverId != null) {
				driver = driverDao.getDriver(driverId);
			} else {
			if (uniqueKey == null) {
				 return  ServiceResponse.getServiceResponse(false, "You must send the driver id or socialSecurity as parameter.");
			}
			driver = driverDao.getDriver(uniqueKey);
			}
		
			if (driver != null) {
				List<DriverVehicleMap> drvm = (List<DriverVehicleMap>) driverDao.find("from DriverVehicleMap where driver.id="+driver.getId());
				if (!drvm.isEmpty())
				return  ServiceResponse.getServiceResponse(false, "Sorry, This driver can not be deleted. It is assigned to one or more assets.");
				driverDao.delete(driver);
				logger.logInfo("Clearning cached");
				cacheClient.expireKeyasValue(this.getClass().getName());
				try {
					MqClientFactory.mqClient.sendMessageToTopic(AMQMessageFactory.generateTopicMessage(driver.getAccount().getId(), "DRIVER", driver.getId(), "DELETE"), TopicName.ACCOUNT.name());
				} catch (Exception e) {
					logger.logError(e.getMessage(), e);
					return ServiceResponseException.generateResponse(e);
				}
			} else {
				logger.logInfo("driver already deleted");
				return  ServiceResponse.getServiceResponse(true, "Driver already deleted. ");
			}
			
			return  ServiceResponse.getServiceResponse(true, "Driver deleted successfuly");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	public String list (String payload){
		try {
			DriverRequest driverRequest = (DriverRequest) JSONMapper.toObject(payload, DriverRequest.class);
			String key = StringUtil.generateReducedString(JSONMapper.toString(driverRequest.getDriver()), 120);
			String output =  (String) cacheClient.get(key);
			if (output == null){
				List<Driver> driver = driverDao.listDriver(driverRequest);
				DriverResponse response = new DriverResponse();
				response.setDriverList(driver);
				response.setTotalCount(driverDao.listTotalDriverCount(driverRequest));
				output =  JSONMapper.toString(response);
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
	
	public String search (String payload) {
		try {
			String key = StringUtil.generateReducedString(payload, 120);
			String output =  (String) cacheClient.get(key);
			if (output == null){
				long total;
				DriverSearchRequest request = (DriverSearchRequest) JSONMapper.toObject(payload, DriverSearchRequest.class);
				List<Driver> driver = driverDao.listDriverSearch(request);
				if (driver.isEmpty()) {
					driver = driverDao.listVehicleMap(deviceVehicleDao.listDeviceSearch(request));
					total = deviceVehicleDao.listDeviceVehicleMapCount();
				} else {
					total = driverDao.listTotalDriverSearchCount(request);
				}
				DriverResponse response = new DriverResponse();
				response.setDriverList(driver);
				response.setTotalCount(total);
				output =  JSONMapper.toString(response);
				cacheClient.set(key, expiration, output);
				cacheClient.storeKeyasValue(this.getClass().getName(), key, expiration);
				return output;
			} else{
				return output;
			}
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
		
	}
	
	public String get(String uniqueKey) {
		try {
			String key = StringUtil.generateReducedString("getDriver"+uniqueKey, 10);
			String output =  (String) cacheClient.get(key);
		if (output == null){
			Driver driver = driverDao.getDriver(uniqueKey);
			if (driver == null) {
				return ServiceResponse.getSingleObjectResponse(false, "Driver Not Found", null);
			}  else {
				output = ServiceResponse.getSingleObjectResponse(true, "Driver Found", JSONMapper.toString(driver));
				cacheClient.set(key, expiration, output);
				cacheClient.storeKeyasValue(this.getClass().getName(), key, expiration);
				return output;
			}
		}else {
			return output;
		}
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}	
		
	}

	public void clearCache() {
		cacheClient.expireKeyasValue(this.getClass().getName());
	}
}
