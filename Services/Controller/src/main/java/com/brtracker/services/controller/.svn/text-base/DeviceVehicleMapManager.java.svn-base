package com.brtracker.services.controller;

import java.util.Date;
import java.util.List;
import com.brtracker.services.controller.dao.DeviceVehicleMapDAOImpl;
import com.brtracker.shared.payload.controller.DeviceVehicleMapRequest;
import com.brtracker.shared.payload.controller.DeviceVehicleMapResponse;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;
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
 * The Class DeviceVehicleMapManager.
 */
public class DeviceVehicleMapManager extends DeviceVehicleMapDAOImpl {
	
	
	/** The logger. */
	private MyLogger logger = new MyLogger(DeviceVehicleMapManager.class);
	private Memcached memcached = SystemResourcesUtil.Memcached.get();
	int expiration = memcached.getExpirationTime("deviceVehicleMap");
	private CacheClient cacheClient = CacheClient.get();


	/**
	 * Adds the.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String add (String payload) {
		try {
			DeviceVehicleMap deviceVehicleMap = (DeviceVehicleMap) JSONMapper.toObject(payload, DeviceVehicleMap.class);
			deviceVehicleMap.setDateCreated(new Date(System.currentTimeMillis()));
			Vehicle vehicle = deviceVehicleMap.getVehicle();
			Device device = deviceVehicleMap.getDevice();
			if (vehicle.getId() == null){
				logger.logInfo("Vehicle does not exist , creating");
				super.saveOrUpdate(vehicle);
				deviceVehicleMap.setVehicle(vehicle);
			}
			if (device.getId() == null){
				logger.logInfo("Device does not exist , creating");
				super.saveOrUpdate(device);
				deviceVehicleMap.setDevice(device);
			}
			super.add(device);
			return  ServiceResponse.getServiceResponse(true, "DeviceVehicleMap added successfuly");
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
			DeviceVehicleMap device = (DeviceVehicleMap) JSONMapper.toObject(payload, DeviceVehicleMap.class);
			super.update(device);
			cacheClient.expireKeyasValue(this.getClass().getName());
			return  ServiceResponse.getServiceResponse(true, "DeviceVehicleMap added successfuly");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	
	public String delete (String payload) {
		try {
			DeviceVehicleMap device = (DeviceVehicleMap) JSONMapper.toObject(payload, DeviceVehicleMap.class);
			super.delete(super.getMap(device.getId()));
			return  ServiceResponse.getServiceResponse(true, "DeviceVehicleMap deleted successfuly");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	
		
	public String get(String unitAddress) {
		try{
			String key = StringUtil.generateReducedString("getDeviceVehicleMap"+unitAddress, 10);
			String output =  (String) cacheClient.get(key);
			if (output == null){
				DeviceVehicleMap devVehicleMap= super.getMap(unitAddress);
				if (devVehicleMap == null) {
					return ServiceResponse.getSingleObjectResponse(false, "DeviceVehicleMap Not Found", null);
				} else {
					output =  ServiceResponse.getSingleObjectResponse(true, "Device Found", JSONMapper.toString(devVehicleMap));
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

	public String getDeviceVehicleMap(String licensePlate) {
		try{
			DeviceVehicleMap devVehicleMap= super.getDeviceMap(licensePlate);
			if (devVehicleMap == null) {
				return ServiceResponse.getSingleObjectResponse(false, "DeviceVehicleMap Not Found", null);
			} else {
				return ServiceResponse.getSingleObjectResponse(true, "Device Found", JSONMapper.toString(devVehicleMap));
				
			}
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}

	public String list(String payload) {
		try{
			DeviceVehicleMapRequest request = (DeviceVehicleMapRequest) JSONMapper.toObject(payload, DeviceVehicleMapRequest.class);
			String key = StringUtil.generateReducedString(JSONMapper.toString(request.getDeviceVehicleMap()), 100);
			String output =  (String) cacheClient.get(key);
			if (output == null){
				List<DeviceVehicleMap> list = listDeviceVehicleMap(request);
				DeviceVehicleMapResponse response = new DeviceVehicleMapResponse();
				response.setDeviceVehicleMapList(list);
				response.setTotalCount(super.listDeviceVehicleMapCount());
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

	public String getDevicefromVehicleId(Long vehicleId) {
		try{
			String key = StringUtil.generateReducedString("getDevicefromVehicleId"+vehicleId, 20);
			String output =  (String) cacheClient.get(key);
			if (output == null){
				Device device= super.getDevice(vehicleId);
					if (device == null) {
						return ServiceResponse.getSingleObjectResponse(false, "DeviceVehicleMap Not Found", null);
					} else {	
						output =  ServiceResponse.getSingleObjectResponse(true, "Device Found", JSONMapper.toString(device));
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



