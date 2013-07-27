package com.brtracker.services.controller;

import java.util.Date;
import java.util.List;
import com.brtracker.services.controller.dao.DeviceDAOImpl;
import com.brtracker.shared.payload.controller.DeviceRequest;
import com.brtracker.shared.payload.controller.DeviceResponse;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.utils.StringUtil;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Memcached;
import com.brtracker.shared.utils.cache.CacheClient;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;


// TODO: Auto-generated Javadoc
/**
 * The Class DeviceManager.
 */
public class DeviceManager extends DeviceDAOImpl {

/** The dao. */


/** The logger. */
private MyLogger logger = new MyLogger(DeviceManager.class);
private Memcached memcached = SystemResourcesUtil.Memcached.get();
int expiration = memcached.getExpirationTime("device");
private CacheClient cacheClient = CacheClient.get();

	/**
	 * Adds the.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String add (String payload) {
		try {
			Device device = (Device) JSONMapper.toObject(payload, Device.class);
			device.setDateCreated(new Date(System.currentTimeMillis()));
			add(device);
			cacheClient.expireKeyasValue(this.getClass().getName());
			return  ServiceResponse.getServiceResponse(true, "Device added successfuly");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	/**
	 * Udpate.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String update (String payload) {
		try {
			Device device = (Device) JSONMapper.toObject(payload, Device.class);
			Long deviceId = device.getId();
			if (deviceId == null){
				if (device.getAddress() == null){
				  return  ServiceResponse.getServiceResponse(false, "You must send at least the device id or device address.");
				}
				else {
					device = getDevice(device.getAddress());
				}
			} else {
				device = getDevice(deviceId);
			}
			update(device);
			cacheClient.expireKeyasValue(this.getClass().getName());
			return  ServiceResponse.getServiceResponse(true, "Device added successfuly");
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
			Device device = (Device) JSONMapper.toObject(payload, Device.class);
			if (device.getId() != null) {
				delete(device);	
			} else {
				String unitAddress = device.getAddress();
			if (unitAddress == null) {
				 return  ServiceResponse.getServiceResponse(false, "You must send the device address as parameter.");
			}
			device = getDevice(unitAddress);
			if (device != null)
			delete(device);
			}
			return  ServiceResponse.getServiceResponse(true, "Device deleted successfuly");
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
			String key = StringUtil.generateReducedString(payload, 50);
			String output =  (String) cacheClient.get(key);
			if (output == null){
			DeviceRequest deviceRequest = (DeviceRequest) JSONMapper.toObject(payload, DeviceRequest.class);
			List<Device> device = listDevices(deviceRequest);
			DeviceResponse response = new DeviceResponse();
			response.setDeviceList(device);
			response.setTotalCount(listTotalDeviceCount(deviceRequest));
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

	public void forceUpdate () {
		cacheClient.expireKeyasValue(this.getClass().getName());
	}
	
	public String get(String unitAddress) {
		try {
			String key = StringUtil.generateReducedString("getDevice"+unitAddress, 5);
			String output =  (String) cacheClient.get(key);
			if (output == null){
				Device device = super.getDevice(unitAddress);
				if (device == null) {
					return ServiceResponse.getSingleObjectResponse(false, "Device Not Found", null);
				}  else {
					output = ServiceResponse.getSingleObjectResponse(true, "Device Found", JSONMapper.toString(device));
					cacheClient.set(key, expiration, output);
					cacheClient.storeKeyasValue(this.getClass().getName(), key, expiration);
					return output;
				}
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
	
	
	
	
}
