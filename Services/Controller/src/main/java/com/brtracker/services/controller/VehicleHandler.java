package com.brtracker.services.controller;

import java.util.Date;
import java.util.List;
import com.brtracker.services.controller.dao.VehicleDAOImpl;
import com.brtracker.shared.payload.controller.VehicleDeviceRequest;
import com.brtracker.shared.payload.controller.VehicleRequest;
import com.brtracker.shared.payload.controller.VehicleResponse;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
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
 * The Class VehicleManager.
 */
public class VehicleHandler  {

	
	/** The logger. */
	private MyLogger logger = new MyLogger(VehicleHandler.class);
	private VehicleDAOImpl dao = new VehicleDAOImpl();
	private Memcached memcached = SystemResourcesUtil.Memcached.get();
	int expiration = memcached.getExpirationTime("vehicle");
	private CacheClient cacheClient = CacheClient.get();
	
	/**
	 * Adds the.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	
	
	public String add (String payload) {
		try {
			Vehicle vehicle = (Vehicle) JSONMapper.toObject(payload, Vehicle.class);
			vehicle.setDateCreated(new Date(System.currentTimeMillis()));
			dao.add(vehicle);
			cacheClient.expireKeyasValue(this.getClass().getName());
		     return  ServiceResponse.getServiceResponse(true, "Vehicle added successfuly.");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	public String addVehicleDeviceMap (String payload) {
		try {
			VehicleDeviceRequest vehicleDeviceRequest = (VehicleDeviceRequest) JSONMapper.toObject(payload, VehicleDeviceRequest.class);
			Vehicle vehicle = vehicleDeviceRequest.getVehicle();
			vehicle.setDateCreated(new Date(System.currentTimeMillis()));
			dao.saveOrUpdate(vehicle);
			for (Device device: vehicleDeviceRequest.getDeviceList()){
				device = dao.getDevice(device.getId());
				device.setAssigned(true);
				DeviceVehicleMap map = new DeviceVehicleMap();
				map.setVehicle(vehicle);
				map.setDevice(device);
				map.setDateCreated(new Date(System.currentTimeMillis()));
				dao.saveOrUpdate(map);
			}
			cacheClient.expireKeyasValue(this.getClass().getName());
			 return  ServiceResponse.getServiceResponse(true, "Vehicle added successfuly.");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	public String updateVehicleDeviceMap (String payload) {
		try {
			VehicleDeviceRequest vehicleDeviceRequest = (VehicleDeviceRequest) JSONMapper.toObject(payload, VehicleDeviceRequest.class);
			Vehicle vehicle = vehicleDeviceRequest.getVehicle();
			Long vehicleId = vehicle.getId();
			if (vehicleId == null){
				return  ServiceResponse.getServiceResponse(false, "Vehicle ID can not be null .");
			}
			dao.deleteVehicleDeviceMap(vehicleId);
			for (Device device: vehicleDeviceRequest.getDeviceList()){
				DeviceVehicleMap map = new DeviceVehicleMap();
				map.setVehicle(vehicle);
				map.setDevice(device);
				map.setDateCreated(new Date(System.currentTimeMillis()));
				dao.saveOrUpdate(map);
			}
			cacheClient.expireKeyasValue(this.getClass().getName());
			 return  ServiceResponse.getServiceResponse(true, "Vehicle updated successfuly.");
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
			Vehicle vehicle = (Vehicle) JSONMapper.toObject(payload, Vehicle.class);
			String uniqueKey = vehicle.getUniqueKey();
			if (vehicle.getId() == null){
				if (uniqueKey == null){
				  return  ServiceResponse.getServiceResponse(false, "You must send at least the vehicle id or vehicle unique key.");
				}
			}
			dao.update(vehicle);
			try {
				MqClientFactory.getMqClient().sendMessageToTopic(AMQMessageFactory.generateTopicMessage(vehicle.getId(), "VEHICLE", vehicle.getId(), "UPDATE"), TopicName.ACCOUNT.name());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cacheClient.expireKeyasValue(this.getClass().getName());
			return  ServiceResponse.getServiceResponse(true, "Vehicle added successfuly");
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
			Vehicle vehicle = (Vehicle) JSONMapper.toObject(payload, Vehicle.class);
			Long id = vehicle.getId();
			if (id != null) {
				vehicle = dao.getVehicle(id);
			} else {
			String licensePlate = vehicle.getUniqueKey();
			if (licensePlate == null) {
				 return  ServiceResponse.getServiceResponse(false, "You must send the vehicle licensePlate as parameter.");
			}
			vehicle = dao.getVehicle(licensePlate);
			}
			if (vehicle != null)
				dao.delete(vehicle);
			try {
				MqClientFactory.getMqClient().sendMessageToTopic(AMQMessageFactory.generateTopicMessage(vehicle.getId(), "VEHICLE", vehicle.getId(), "DELETE"), TopicName.ACCOUNT.name());
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<DeviceVehicleMap> dvm = (List<DeviceVehicleMap>) dao.find("from DeviceVehicleMap where vehicle.id="+vehicle.getId());
			for (DeviceVehicleMap d : dvm) dao.delete(d);
			List<DriverVehicleMap> drvm = (List<DriverVehicleMap>) dao.find("from DriverVehicleMap where vehicle.id="+vehicle.getId());
			for (DriverVehicleMap dr : drvm) dao.delete(dr);
			return  ServiceResponse.getServiceResponse(true, "Vehicle deleted successfuly");
			
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
				VehicleRequest vehicleRequest = (VehicleRequest) JSONMapper.toObject(payload, VehicleRequest.class);
				List<Vehicle> vehicle = dao.listVehicle(vehicleRequest);
				VehicleResponse response = new VehicleResponse();
				response.setVehicleList(vehicle);
				response.setTotalCount(dao.listTotalVehicleCount(vehicleRequest));
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

	public String get(String uniqueKey) {
		try {
			String key = StringUtil.generateReducedString("get"+uniqueKey, 10);
			String output =  (String) cacheClient.get(key);
			if (output == null){
				Vehicle vehicle = dao.getVehicle(uniqueKey);
				if (vehicle == null) {
					return ServiceResponse.getSingleObjectResponse(false, "Vehicle Not Found", null);
				}  else {
					output = ServiceResponse.getSingleObjectResponse(true, "Vehicle Found", JSONMapper.toString(vehicle));
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