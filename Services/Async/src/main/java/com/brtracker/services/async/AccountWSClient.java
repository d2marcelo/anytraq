package com.brtracker.services.async;

import java.util.ArrayList;
import java.util.List;
import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.shared.payload.controller.AlertConfigurationRequest;
import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.DefaultAlertConfiguration;
import com.brtracker.shared.payload.controller.DefaultAlertConfigurationResponse;
import com.brtracker.shared.payload.controller.DeviceRequest;
import com.brtracker.shared.payload.controller.DeviceResponse;
import com.brtracker.shared.payload.controller.GeofenceAlertConfiguration;
import com.brtracker.shared.payload.controller.GeofenceAlertConfigurationResponse;
import com.brtracker.shared.payload.controller.VehicleResponse;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.payload.controller.lookup.AlertCategoryType;
import com.brtracker.shared.payload.tracking.AlertMessageRequest;
import com.brtracker.shared.payload.tracking.AlertMessageResponse;
import com.brtracker.shared.payload.tracking.TrackingInterfaceLookup;
import com.brtracker.shared.payload.tracking.data.AlertMessage;
import com.brtracker.shared.utils.StringUtil;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Memcached;
import com.brtracker.shared.utils.cache.CacheClient;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.SingleObjectResponse;

public class AccountWSClient {

	private Memcached memcached = SystemResourcesUtil.Memcached.get();
	private int expiration = memcached.getExpirationTime("device");
	private CacheClient cacheClient = CacheClient.get();
	private String CONTROLLER_URI = ServiceLookup.getControllerIP();
	private MyLogger logger = new MyLogger(AccountWSClient.class, this.getClass().getSimpleName());
	
	
	public Device getDevice (String unitAddress) throws JSONMapperException, ObjectNotFoundException{
		
		String key = StringUtil.generateReducedString("getDevice"+unitAddress, 60);
		String output =  (String) cacheClient.get(key);
		if (output == null){
			String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.device_GET, CONTROLLER_URI);
			String endpoint = url +"?unitAddress="+unitAddress;
			SingleObjectResponse resp;
			
			// wait until the account service restore.
			while (true){
			try {
				resp = (SingleObjectResponse) JSONMapper.toObject(ClientCall.get(endpoint), SingleObjectResponse.class);
				break;
			} catch (ServiceException e) {
				try {
					logger.logError(e.getMessage());
					logger.logInfo("============================================");
					logger.logInfo("Account Service is down. url: " + endpoint);
					logger.logInfo("Trying in 10 seconds.");
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			}
			
				if (!resp.isReturnValue()) throw new ObjectNotFoundException("OBJECT Device NOT FOUND Unit Address: "+unitAddress);
					output = resp.getObject();
					Device device = (Device) JSONMapper.toObject(output, Device.class);
					cacheClient.set(key, expiration, output);
					cacheClient.storeKeyasValue(device.getAccount().getId(), AccountWSClient.class.getName(), key, expiration);
					return device;
					
				} else {
					return (Device) JSONMapper.toObject(output, Device.class);
		}
		
	}
	
	
	public Device getDevice (Long id) throws JSONMapperException, ObjectNotFoundException{
		
		String key = StringUtil.generateReducedString("getDevice"+id, 60);
		String output =  (String) cacheClient.get(key);
		if (output == null){
			String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.device_LIST, CONTROLLER_URI);
			
			DeviceRequest deviceRequest = new DeviceRequest();
			Device device = new Device();
			device.setId(id);
			deviceRequest.setDevice(device);
			String payload = JSONMapper.toString(deviceRequest);
			DeviceResponse resp;
			
			// wait until the account service restore.
			while (true){
			try {
				resp = (DeviceResponse) JSONMapper.toObject(ClientCall.post(url, payload), DeviceResponse.class);
				break;
			} catch (ServiceException e) {
				try {
					logger.logError(e.getMessage());
					logger.logInfo("============================================");
					logger.logInfo("Account Service is down. url: " + url);
					logger.logInfo("Trying in 10 seconds.");
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			}
			
				if (resp.getDeviceList().size() == 0) throw new ObjectNotFoundException("OBJECT Device NOT FOUND Unit id: "+id);
					Device deviceOutput = resp.getDeviceList().get(0);
					cacheClient.set(key, expiration, JSONMapper.toString(deviceOutput));
					cacheClient.storeKeyasValue(deviceOutput.getAccount().getId(), AccountWSClient.class.getName(), key, expiration);
					return deviceOutput;
					
				} else {
					return (Device) JSONMapper.toObject(output, Device.class);
		}
		
	}
	
	public DriverVehicleMap getDriverVehicleMapFromDevice (String unitAddress) throws  JSONMapperException, ObjectNotFoundException{
		
		String key = StringUtil.generateReducedString("getDriverFromDevice"+unitAddress, 60);
		String output =  (String) cacheClient.get(key);
		if (output == null){
			String deviceVehicleMapEndpoint = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.deviceVehicleMap_GET, CONTROLLER_URI) +"?unitAddress="+unitAddress;
			SingleObjectResponse resp = null;
			while (true){
			try {
				resp = (SingleObjectResponse) JSONMapper.toObject(ClientCall.get(deviceVehicleMapEndpoint), SingleObjectResponse.class);
				break;
			} catch (ServiceException e) {
				try {
					logger.logError(e.getMessage());
					logger.logInfo("============================================");
					logger.logInfo("Account Service is down. url: " + deviceVehicleMapEndpoint);
					logger.logInfo("Trying in 10 seconds.");
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			}
			if (!resp.isReturnValue()) throw new ObjectNotFoundException("OBJECT DriverVehicleMap NOT FOUND Unit Address: "+unitAddress);;
			DeviceVehicleMap deviceVehicleMap = (DeviceVehicleMap) JSONMapper.toObject(resp.getObject(), DeviceVehicleMap.class);
			String driverVehicleMapEndpoint = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_GET, CONTROLLER_URI) +"?vehicleId="+deviceVehicleMap.getVehicle().getId();
			while (true){
			try {
				resp = (SingleObjectResponse) JSONMapper.toObject(ClientCall.get(driverVehicleMapEndpoint), SingleObjectResponse.class);
				break;
			} catch (ServiceException e) {
				try {
					logger.logError(e.getMessage());
					logger.logInfo("============================================");
					logger.logInfo("Account Service is down. url: " + driverVehicleMapEndpoint);
					logger.logInfo("Trying in 10 seconds.");
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			}
			if (!resp.isReturnValue()) throw new ObjectNotFoundException("OBJECT DriverVehicleMap NOT FOUND Unit Address: "+unitAddress);
				output = resp.getObject();
				DriverVehicleMap driverVehicleMap = (DriverVehicleMap) JSONMapper.toObject(resp.getObject(), DriverVehicleMap.class);
				cacheClient.set(key, expiration, output);
				cacheClient.storeKeyasValue(driverVehicleMap.getDriver().getAccount().getId(), AccountWSClient.class.getName(), key, expiration);
				return driverVehicleMap; 
		} else {
				DriverVehicleMap driverVehicleMap = (DriverVehicleMap) JSONMapper.toObject(output, DriverVehicleMap.class);
				return driverVehicleMap;
		}
	}
	
	public List<Device> getDeviceFromDriver (Long driverId) throws  JSONMapperException, ObjectNotFoundException{
		String key = StringUtil.generateReducedString("getDriverFromDevice"+driverId, 60);
		String output =  (String) cacheClient.get(key);
		if (output == null){
			String driverVehMapUrl = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_GET_VEHICLE, CONTROLLER_URI)+ "?driverId="+driverId;;
			VehicleResponse resp = null;
			while(true){
			try {
				resp = (VehicleResponse) JSONMapper.toObject(ClientCall.get(driverVehMapUrl), VehicleResponse.class);
				break;
			} catch (ServiceException e) {
				try {
					logger.logError(e.getMessage());
					logger.logInfo("============================================");
					logger.logInfo("Account Service is down. url: " + driverVehMapUrl);
					logger.logInfo("Trying in 10 seconds.");
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			}
			List<Vehicle> vehicleList = resp.getVehicleList();
			List<Device> deviceList= new ArrayList<Device>();
			for (Vehicle vehicle : vehicleList){
			String devicepUrl = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.deviceVehicleMap_GET_DEVICE, CONTROLLER_URI)+ "?vehicleId="+vehicle.getId();
			SingleObjectResponse resp1 = null;
			while(true){
			try {
				resp1 = (SingleObjectResponse) JSONMapper.toObject(ClientCall.get(devicepUrl), SingleObjectResponse.class);
				break;
			} catch (ServiceException e) {
				try {
					logger.logError(e.getMessage());
					logger.logInfo("============================================");
					logger.logInfo("Account Service is down. url: " + devicepUrl);
					logger.logInfo("Trying in 10 seconds.");
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					logger.logError(e.getMessage());
				}
			}
			}
			if (resp1.isReturnValue()) {
				Device d = (Device) JSONMapper.toObject(resp1.getObject(), Device.class);
				deviceList.add(d);
			}
			}
			if (deviceList.isEmpty())throw new ObjectNotFoundException("OBJECTS Device NOT FOUND driverId: "+driverId);
			DeviceResponse deviceResponse = new DeviceResponse();
			deviceResponse.setDeviceList(deviceList);
			output = JSONMapper.toString(deviceResponse);
			if (!deviceList.isEmpty()){
			cacheClient.set(key, expiration, output);
			cacheClient.storeKeyasValue(deviceList.get(0).getAccount().getId(), AccountWSClient.class.getName(), key, expiration);
			}
			return deviceList;
		}else {
			DeviceResponse deviceResponse= (DeviceResponse) JSONMapper.toObject(output, DeviceResponse.class);
			return deviceResponse.getDeviceList();
		}
	}
	
	public Device getDeviceFromVehicle (Long vehicleId) throws  JSONMapperException, ObjectNotFoundException{
		String key = StringUtil.generateReducedString("getDeviceFromVehicle"+vehicleId, 60);
		String output =  (String) cacheClient.get(key);
		if (output == null){
			String devicepUrl = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.deviceVehicleMap_GET_DEVICE, CONTROLLER_URI)+ "?vehicleId="+vehicleId;
			SingleObjectResponse resp;
			while (true){
			try {
				resp = (SingleObjectResponse) JSONMapper.toObject(ClientCall.get(devicepUrl), SingleObjectResponse.class);
				break;
			} catch (ServiceException e) {
				try {
					logger.logError(e.getMessage());
					logger.logInfo("============================================");
					logger.logInfo("Account Service is down. url: " + devicepUrl);
					logger.logInfo("Trying in 10 seconds.");
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					logger.logError(e.getMessage());
				}
			}
			}
			if (!resp.isReturnValue()) throw new ObjectNotFoundException("OBJECT DeviceVehicleMap NOT FOUND vehicleId: "+vehicleId);
			output = resp.getObject();
			Device device = (Device) JSONMapper.toObject(resp.getObject(), Device.class);
			cacheClient.set(key, expiration, output);
			cacheClient.storeKeyasValue(device.getAccount().getId(), AccountWSClient.class.getName(), key, expiration);
			return device;
		} else {
			Device device = (Device) JSONMapper.toObject(output, Device.class);
			return device;
		}
		
	}
	
	public DeviceVehicleMap getDeviceVehicleMap (String unitAddress) throws JSONMapperException, ObjectNotFoundException{
		String key = StringUtil.generateReducedString("getDeviceVehicleMap"+unitAddress, 60);
		String output =  (String) cacheClient.get(key);
		if (output == null){
			String deviceVehMapUrl = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.deviceVehicleMap_GET,CONTROLLER_URI)+ "?unitAddress="+unitAddress;
			String deviceVehicleMapStr;
			while (true) {
			try {
				deviceVehicleMapStr = ClientCall.get(deviceVehMapUrl);
				break;
			} catch (ServiceException e) {
				try {
					logger.logError(e.getMessage());
					logger.logInfo("============================================");
					logger.logInfo("Account Service is down. url: " + deviceVehMapUrl);
					logger.logInfo("Trying in 10 seconds.");
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					logger.logError(e.getMessage());
				}
			}
			}
			SingleObjectResponse resp = (SingleObjectResponse) JSONMapper.toObject(deviceVehicleMapStr, SingleObjectResponse.class);
			if (!resp.isReturnValue()) throw new ObjectNotFoundException("OBJECT DeviceVehicleMap NOT FOUND Unit Address: "+unitAddress);
			output = resp.getObject();
			DeviceVehicleMap deviceVehicleMap = (DeviceVehicleMap) JSONMapper.toObject(resp.getObject(), DeviceVehicleMap.class);
			cacheClient.set(key, expiration, output);
			cacheClient.storeKeyasValue(deviceVehicleMap.getDevice().getAccount().getId(), AccountWSClient.class.getName(), key, expiration);
			return deviceVehicleMap;
		} else {
			DeviceVehicleMap deviceVehicleMap = (DeviceVehicleMap) JSONMapper.toObject(output, DeviceVehicleMap.class);
			return deviceVehicleMap;
		}
	}
	
	public DriverVehicleMap getDriverVehicleMap (Long vehicleId) throws JSONMapperException, ObjectNotFoundException{
		String key = StringUtil.generateReducedString("getDriverVehicleMap"+vehicleId, 60);
		String output =  (String) cacheClient.get(key);
		if (output == null){
			String driverVMapURL = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.driverVehicleMap_GET,CONTROLLER_URI)+ "?vehicleId="+vehicleId;
			String driverVehicleMapStr;
			while (true) {
			try {
				driverVehicleMapStr = ClientCall.get(driverVMapURL);
				break;
			} catch (ServiceException e) {
				try {
					logger.logError(e.getMessage());
					logger.logInfo("============================================");
					logger.logInfo("Account Service is down. url: " + driverVMapURL);
					logger.logInfo("Trying in 10 seconds.");
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			}
			SingleObjectResponse resp = (SingleObjectResponse) JSONMapper.toObject(driverVehicleMapStr, SingleObjectResponse.class);
			if (!resp.isReturnValue()) throw new ObjectNotFoundException("OBJECT DriverVehicleMap NOT FOUND VehicleId: "+vehicleId);
			output = resp.getObject();
			DriverVehicleMap driverVehicleMap = (DriverVehicleMap) JSONMapper.toObject(resp.getObject(), DriverVehicleMap.class);
			cacheClient.set(key, expiration, output);
			cacheClient.storeKeyasValue(driverVehicleMap.getDriver().getAccount().getId(), AccountWSClient.class.getName(), key, expiration);
			return driverVehicleMap;
		} else {
			DriverVehicleMap driverVehicleMap = (DriverVehicleMap) JSONMapper.toObject(output, DriverVehicleMap.class);
			return driverVehicleMap;
		}
	}
	

	
	public List<DefaultAlertConfiguration> getDefaultAlertConfiguration (Long accountId, AlertCategoryType type) throws  JSONMapperException, ServiceException {
		String key = StringUtil.generateReducedString("getDefaultAlertConfiguration"+accountId + type, 80);
		String output =  (String) cacheClient.get(key);
		if (output == null){
			AlertConfigurationRequest acr = new AlertConfigurationRequest();
			acr.setAccountId(accountId);
			acr.setAlertCategoryType(type);
			String controllerUrl = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.defaultAlertConfiguration_LIST, ServiceLookup.getControllerIP());
			String configPayload = JSONMapper.toString(acr);
			output = ClientCall.post(controllerUrl, configPayload);
			cacheClient.set(key, expiration, output);
			cacheClient.storeKeyasValue(accountId, AccountWSClient.class.getName(), key, expiration);
			DefaultAlertConfigurationResponse resp = (DefaultAlertConfigurationResponse) JSONMapper.toObject(output,DefaultAlertConfigurationResponse.class);
			return resp.getDefaultAlertConfiguration();
		} else {
			DefaultAlertConfigurationResponse resp = (DefaultAlertConfigurationResponse) JSONMapper.toObject(output,DefaultAlertConfigurationResponse.class);
			return resp.getDefaultAlertConfiguration();
		}
	}
	
	public List<GeofenceAlertConfiguration> getGeofenceAlertConfiguration (Long accountId) throws  JSONMapperException{
		String key = StringUtil.generateReducedString("getGeofenceAlertConfiguration"+accountId, 80);
		String output =  (String) cacheClient.get(key);
		if (output == null){
			AlertConfigurationRequest acr = new AlertConfigurationRequest();
			acr.setAccountId(accountId);
			acr.setAlertCategoryType(AlertCategoryType.GEOFENCING);
			String controllerUrl = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.geofenceAlertConfiguration_LIST, ServiceLookup.getControllerIP());
			String configPayload = JSONMapper.toString(acr);
			String alertConfigStr;
			while (true) {
			try {
				alertConfigStr = ClientCall.post(controllerUrl, configPayload);
				break;
			} catch (ServiceException e) {
				try {
					logger.logError(e.getMessage());
					logger.logInfo("============================================");
					logger.logInfo("Account Service is down. url: " + controllerUrl);
					logger.logInfo("Trying in 10 seconds.");
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					logger.logError(e.getMessage());
				}
			}
			}
			output = alertConfigStr;
			GeofenceAlertConfigurationResponse resp = (GeofenceAlertConfigurationResponse) JSONMapper.toObject(alertConfigStr,GeofenceAlertConfigurationResponse.class);
			cacheClient.set(key, expiration, output);
			cacheClient.storeKeyasValue(accountId, AccountWSClient.class.getName(), key, expiration);
			return resp.getGeofenceAlertConfigurationList();
		} else {
			GeofenceAlertConfigurationResponse resp = (GeofenceAlertConfigurationResponse) JSONMapper.toObject(output,GeofenceAlertConfigurationResponse.class);
			return resp.getGeofenceAlertConfigurationList();
		}
	}
	
	
	public List<AlertMessage> getAlertMessageList (AlertMessageRequest request) throws ServiceException, JSONMapperException{
		String key = StringUtil.generateReducedString("getAlertMessageList"+request.toString(), 80);
		String output =  (String) cacheClient.get(key);
		if (output == null){
		String url = TrackingInterfaceLookup.getURLPath(TrackingInterfaceLookup.alertMessage_LIST, ServiceLookup.getTrackingIP());
		String alertResponse = ClientCall.post(url,JSONMapper.toString(request));
		AlertMessageResponse alertMessageResponse = (AlertMessageResponse) JSONMapper.toObject(alertResponse, AlertMessageResponse.class);
		List<AlertMessage> alertMessageList = alertMessageResponse.getAlertMessageList();
		cacheClient.set(key, expiration, alertResponse);
		cacheClient.storeKeyasValue(request.getAlertMessage().getAccountId(), AccountWSClient.class.getName(), key, expiration);
		return alertMessageList;
		} else {
			AlertMessageResponse resp = (AlertMessageResponse) JSONMapper.toObject(output,AlertMessageResponse.class);
			return resp.getAlertMessageList();
		}
	}
	public void expireMencached(Long accountId){
		cacheClient.expireKeyasValue(accountId, this.getClass().getName());
	}
}
