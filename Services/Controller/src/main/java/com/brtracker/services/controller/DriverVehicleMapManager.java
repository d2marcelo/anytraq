package com.brtracker.services.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.brtracker.services.controller.dao.DeviceVehicleMapDAOImpl;
import com.brtracker.services.controller.dao.DriverVehicleMapDAOImpl;
import com.brtracker.shared.payload.controller.DriverVehicleDeviceMap;
import com.brtracker.shared.payload.controller.DriverVehicleDeviceResponse;
import com.brtracker.shared.payload.controller.DriverVehicleMapRequest;
import com.brtracker.shared.payload.controller.DriverVehicleMapResponse;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;
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
 * The Class DriverVehicleMapManager.
 */
public class DriverVehicleMapManager extends DriverVehicleMapDAOImpl {
	
	
	/** The logger. */
	private MyLogger logger = new MyLogger(DriverVehicleMapManager.class);
	private DeviceVehicleMapDAOImpl deviceVehicleMapDAO = new DeviceVehicleMapDAOImpl();
	private Memcached memcached = SystemResourcesUtil.Memcached.get();
	int expiration = memcached.getExpirationTime("driverVehicleMap");
	private CacheClient cacheClient = CacheClient.get();

	
	/**
	 * Adds the.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String add (String payload) {
		try {
			DriverVehicleMap driverVechicleMap = (DriverVehicleMap) JSONMapper.toObject(payload, DriverVehicleMap.class);
			Driver driver = driverVechicleMap.getDriver();
			Vehicle vehicle = driverVechicleMap.getVehicle();
			if (driver.getId() == null) super.saveOrUpdate(driver);
			if (vehicle.getId() == null) super.saveOrUpdate(vehicle);
			driverVechicleMap.setDateCreated(new Date(System.currentTimeMillis()));
			super.add(driverVechicleMap);
			cacheClient.expireKeyasValue(this.getClass().getName());
			return  ServiceResponse.getServiceResponse(true, "DriverVehicleMap added successfuly");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	

	
	public String upsert (String payload) {
		try {
			DriverVehicleMapRequest driverVechicleMapRequest = (DriverVehicleMapRequest) JSONMapper.toObject(payload, DriverVehicleMapRequest.class);
			DriverVehicleMap driverVehicleMap = driverVechicleMapRequest.getDriverVehicleMap();
			Vehicle vehicle = driverVehicleMap.getVehicle();
			Driver driver = driverVehicleMap.getDriver();
			vehicle.setDateCreated(new Date(System.currentTimeMillis()));
			super.saveOrUpdate(vehicle);
			// check this
			driverVehicleMap.setVehicle(vehicle);
			driverVehicleMap.setDateCreated(new Date(System.currentTimeMillis()));
			if (driver.getId() != null && vehicle.getId() != null){
				List<DriverVehicleMap> list = (List<DriverVehicleMap>) super.find("from DriverVehicleMap where vehicle.id="+vehicle.getId());
				for (DriverVehicleMap dvm : list) super.delete(dvm);
				super.saveOrUpdate(driverVehicleMap);
			}
			
			List<Device> deviceList = driverVechicleMapRequest.getDeviceList();
			List<DeviceVehicleMap> list = (List<DeviceVehicleMap>) super.find("from DeviceVehicleMap where vehicle.id="+vehicle.getId());
			for (DeviceVehicleMap dvm : list) super.delete(dvm);
			if (!deviceList.isEmpty()){
				for (Device device : deviceList){
					DeviceVehicleMap map = new DeviceVehicleMap();
					map.setVehicle((Vehicle)super.findUniqueResult("from Vehicle where id="+vehicle.getId()));
					Device dev = (Device)super.findUniqueResult("from Device where id="+device.getId());
					dev.setAssigned(true);
					map.setDevice(dev);
					map.setDateCreated(new Date(System.currentTimeMillis()));
					logger.logInfo("Saving device vehicle map");
					super.saveOrUpdate(map);
					super.saveOrUpdate(dev);
				}
				
			}
			try {
				cacheClient.expireKeyasValue(this.getClass().getName());
				MqClientFactory.mqClient.sendMessageToTopic(AMQMessageFactory.generateTopicMessage(driver.getAccount().getId(), "DRIVER", driver.getId(), "UPDATE"), TopicName.ACCOUNT.name());
			} catch (Exception e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
			return  ServiceResponse.getServiceResponse(true, "DriverVehicleMap added successfuly");
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
			DriverVehicleMap driver = (DriverVehicleMap) JSONMapper.toObject(payload, DriverVehicleMap.class);
			super.update(super.getSingleDriverVehicleMap(driver.getId()));
			return  ServiceResponse.getServiceResponse(true, "DriverVehicleMap added successfuly");
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
			DriverVehicleMap driver = (DriverVehicleMap) JSONMapper.toObject(payload, DriverVehicleMap.class);
			super.delete(driver);
			return  ServiceResponse.getServiceResponse(true, "DriverVehicleMap deleted successfuly");
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
			DriverVehicleMapRequest driverVehicleMapRequest = (DriverVehicleMapRequest) JSONMapper.toObject(payload, DriverVehicleMapRequest.class);
			String key = StringUtil.generateReducedString(JSONMapper.toString(driverVehicleMapRequest.getDriverVehicleMap()), 100);
			String output =  (String) cacheClient.get(key);
			if (output == null){
				List<DriverVehicleMap> driver = super.list(driverVehicleMapRequest);
				DriverVehicleMapResponse response = new DriverVehicleMapResponse();
				response.setDriverVehicleMapList(driver);
				output = JSONMapper.toString(response);
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
		
		public String get(Long vehicleId) {
			try{
				String key = StringUtil.generateReducedString("get"+vehicleId, 20);
				String output =  (String) cacheClient.get(key);
				if (output == null){
					DriverVehicleMap devVehicleMap= super.getDriverVehicleMap(vehicleId);
					if (devVehicleMap == null) {
						return ServiceResponse.getSingleObjectResponse(false, "DriverVehicleMap Not Found", null);
					} else {
						output = ServiceResponse.getSingleObjectResponse(true, "DriverVehicleMap Found", JSONMapper.toString(devVehicleMap));
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
		
		public String asset (String payload) {
			try {
				String key = StringUtil.generateReducedString(payload, 50);
				String output =  (String) cacheClient.get(key);
				if (output == null){
					DriverVehicleMapRequest driverVehicleMapRequest = (DriverVehicleMapRequest) JSONMapper.toObject(payload, DriverVehicleMapRequest.class);
					List<DriverVehicleMap>  driverVehicleMap = super.list(driverVehicleMapRequest);
					DriverVehicleDeviceResponse response = new DriverVehicleDeviceResponse();
					List<DriverVehicleDeviceMap> dvdmList = new ArrayList<DriverVehicleDeviceMap>();
					for (DriverVehicleMap map : driverVehicleMap){
						DriverVehicleDeviceMap dvdm = new DriverVehicleDeviceMap();
						dvdm.setDriverVehicleMap(map);
						dvdm.setDeviceList(deviceVehicleMapDAO.listDevices(map.getVehicle().getId()));
						dvdmList.add(dvdm);
					}
					response.setDriverVehicleDeviceMap(dvdmList);
					response.setTotalCount(driverVehicleMap.size());
					output =  JSONMapper.toString(response);
					cacheClient.set(key, expiration, output);
					cacheClient.storeKeyasValue(this.getClass().getName(), key, expiration);
					return output;
				}else {
					return output;
				}
				
			} catch (JSONMapperException e) {
				return ServiceResponseException.generateResponse(e);
			}
				
		}

		public String getVehicleFromDriverId(Long driverId) {
			try{
				String key = StringUtil.generateReducedString("getVehicleFromDriverId"+driverId, 10);
				String output =  (String) cacheClient.get(key);
				if (output == null){
					Vehicle vehicle= super.getVehicle(driverId);
				if (vehicle == null) {
					return ServiceResponse.getSingleObjectResponse(false, "DriverVehicleMap Not Found", null);
				} else {
					output =  ServiceResponse.getSingleObjectResponse(true, "DriverVehicleMap Found", JSONMapper.toString(vehicle));
					cacheClient.set(key, expiration, output);
					cacheClient.storeKeyasValue(this.getClass().getName(), key, expiration);
					return output;
				}
				}else{
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



