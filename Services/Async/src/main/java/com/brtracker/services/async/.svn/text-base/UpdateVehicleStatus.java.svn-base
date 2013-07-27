package com.brtracker.services.async;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javassist.tools.rmi.ObjectNotFoundException;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.controller.lookup.EntityType;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.payload.tracking.data.DeviceStatus;
import com.brtracker.shared.payload.tracking.data.VehicleStatus;
import com.brtracker.shared.payload.tracking.lookup.VehicleStatusMsgType;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.EventAuditorBuilder;
import com.brtracker.shared.utils.EventType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceException;

public class UpdateVehicleStatus extends AsyncAction {

	private MyLogger logger = new MyLogger(UpdateVehicleStatus.class, this.getClass().getSimpleName());
	private AccountWSClient accountWSClient;
	private AsyncDao dao;
	private EventAuditorBuilder eventAuditBuilder;
	
	
	@Override
	public void run () {
	try {
			logger.logInfo("UpdateVehicleStatus "+message);
			parseMessage();
			if (deviceState.equals(DeviceState.NO_GPS)) return;
			Device device = accountWSClient.getDevice(address);
			DeviceVehicleMap deviceVehicleMap = accountWSClient.getDeviceVehicleMap(address);
			Vehicle vehicle = deviceVehicleMap.getVehicle();
			DriverVehicleMap driverVehicleMap = accountWSClient.getDriverVehicleMap(vehicle.getId());
			Driver driver = driverVehicleMap.getDriver();
			VehicleStatus vehicleStatus = dao.getVehicleStatus("deviceId",device.getId());
			logger.logInfo("Update Status.."+vehicleStatus);
			update(deviceMessageAttribute, vehicleStatus, device, driver, vehicle);
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_VEHICLE_STATUS).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (ObjectNotFoundException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_VEHICLE_STATUS).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("ObjectNotFoundException: "+ e.getMessage()).toString(),EVENT_AUDITOR_QUEUE);
		} catch (InvalidMessageException e) {
			logger.logError(e.getMessage(), e);
			MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_DEVICE_STATUS).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("InvalidMessageException: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
		}
	}
		
		public void update (String payload) throws JSONMapperException, ServiceException, HibernateHelperException {
			Map<String,String> obj = (Map<String, String>) JSONMapper.toObject(payload, Map.class);
			VehicleStatus vehicleStatus = getVehicleStatus(obj);			
			List<Device> devices = getDevices(obj);
			if (devices.isEmpty() && vehicleStatus != null) dao.delete(vehicleStatus);
			
						for (Device device : devices){
							String address = device.getAddress();
							logger.logInfo("update status for device:"+address);
							DeviceVehicleMap deviceVehicleMap;
						try {
							deviceVehicleMap = accountWSClient.getDeviceVehicleMap(address);
							logger.logInfo("deviceVehicleMap:"+deviceVehicleMap.getId());
							DriverVehicleMap driverVehicle = accountWSClient.getDriverVehicleMap(deviceVehicleMap.getVehicle().getId());
							logger.logInfo("driverVehicleMap:"+driverVehicle.getId());
							update (null, vehicleStatus, device,  driverVehicle.getDriver(), driverVehicle.getVehicle());
						} catch (ObjectNotFoundException e) {
							logger.logInfo("objects not found.. delete vehicle status entry");
							dao.delete(vehicleStatus);
						}
						}
						
		}
		
		public List<Device> getDevices (Map<String,String> map) {
			List<Device> listDevices = new ArrayList<Device>();
			try {
					logger.logInfo("getDevice. "+map.keySet() + " "+ map.values());
					String type = map.get("objectType");
					Long id = Long.parseLong(map.get("objectId"));
					if (type.contains(DRIVER)) {
						listDevices= accountWSClient.getDeviceFromDriver(id);
					}
					if (type.contains(DEVICE)) {
						Device device = accountWSClient.getDevice(id);
						listDevices.add(device);
					}
					if (type.contains(VEHICLE)) {
						Device device = accountWSClient.getDeviceFromVehicle(id);
						listDevices.add(device);
					}
					return listDevices;
			} catch (JSONMapperException e) {
				return listDevices;
			} catch (ObjectNotFoundException e) {
				return listDevices;
			}
		}
		
		public VehicleStatus getVehicleStatus (Map<String,String> map)  {
			logger.logInfo("getVehicleStatus. "+map.keySet() + " "+ map.values());
			String type = map.get("objectType");
			Long id = Long.parseLong(map.get("objectId"));
			logger.logInfo("object: "+ type + " id: "+ id+" dao: "+dao);
			VehicleStatus vehicleStatus= null;
			if (type.contains(DRIVER)) 
				vehicleStatus =  dao.getVehicleStatus("driverId", id);
			
			if (type.contains(DEVICE)) 
				vehicleStatus =  dao.getVehicleStatus("deviceId", id);
			
			if (type.contains(VEHICLE)) 
				vehicleStatus =  dao.getVehicleStatus("vehicleId", id);
			
			return vehicleStatus;
			
		}
	
		private synchronized void update (DeviceMessageAttribute msg, VehicleStatus vehicleStatus, Device device, Driver driver, Vehicle vehicle){
			try {
				// create a map to store attributes of vehicle status
				Map<String,String> currAtt = new HashMap<String,String>();	
			if (msg == null){
				logger.logInfo("DeviceMessageAttribute not available, check deviceStatus");
				// no message, could be a device just got mapped , check if device status has it's location
				msg = new DeviceMessageAttribute();
				DeviceStatus deviceStatus = dao.getDeviceStatus(device.getAddress());
				if (deviceStatus != null){
					logger.logInfo("Device status found for device with address: "+device.getAddress());
					msg.setMessageDate(String.valueOf(deviceStatus.getLastUpdated()));
					logger.logInfo("Attributes from device status:"+deviceStatus.getAttributes());
					msg.setAttributes((Map)JSONMapper.toObject(deviceStatus.getAttributes(), Map.class));
					msg.setUnitAddress(deviceStatus.getDeviceAddress());
				} else  {
					logger.logInfo("nothing to do if no msg available");
					return;
				}
				
			} 
			// maybe found something in device status;
			
			
			
			// get vehicle attributes 
			Map<String, String> vehicleAttributes =(Map<String, String>) JSONMapper.toObject(vehicle.getAttributes(), Map.class);
			Long deviceTime = Long.parseLong(msg.getMessageDate());
			Date systemTime = DateUtil.getUTCDateAndTime();
			
			
			if (vehicleStatus == null) {
					// new status must have msg
				    vehicleStatus = new VehicleStatus();
					vehicleStatus.setAccountId(device.getAccount().getId());
					vehicleStatus.setDeviceId(device.getId());
					vehicleStatus.setDateCreated(deviceTime);
					vehicleStatus.setSystemTime(systemTime);
			} else {
					// append status
					// check if status needs to be closed.
					currAtt = (Map<String, String>) JSONMapper.toObject(vehicleStatus.getAttributes(), Map.class);
					Map<String, String> prevVehicleStatusAtt = (Map<String, String>) JSONMapper.toObject(vehicleStatus.getAttributes(), Map.class);
					String prevLat = prevVehicleStatusAtt.get(VehicleStatusMsgType.latitude.name());
					String prevLon = prevVehicleStatusAtt.get(VehicleStatusMsgType.longitude.name());
					boolean deviceHasMoved = super.hasDeviceMoved(prevLat,prevLon);
					if (deviceHasMoved){
					currAtt.put(VehicleStatusMsgType.previousLat.name(), prevLat);
					currAtt.put(VehicleStatusMsgType.previousLong.name(), prevLon);
					}
			}
			
			for (Map.Entry<String,String> map : msg.getAttributes().entrySet()){
						logger.logInfo("key: "+map.getKey()+" value: "+map.getValue());
						currAtt.put(map.getKey(), map.getValue());
				}
				currAtt.put(VehicleStatusMsgType.lastUpdated.name(), deviceTime.toString());
				currAtt.put(VehicleStatusMsgType.accountId.name(), device.getAccount().getId().toString());
				currAtt.put(VehicleStatusMsgType.deviceId.name(), device.getId().toString());
				currAtt.put(VehicleStatusMsgType.deviceAddress.name(), device.getAddress().toString());
				currAtt.put(VehicleStatusMsgType.driverId.name(), driver.getId().toString());
				currAtt.put(VehicleStatusMsgType.driverName.name(), driver.getFirstName() +" "+driver.getLastName());
				currAtt.put(VehicleStatusMsgType.vehicleId.name(), vehicle.getId().toString());
				currAtt.put(VehicleStatusMsgType.entityType.name(), EntityType.getEntityType(vehicle.getType()).name());
				currAtt.put(VehicleStatusMsgType.driverPhoto.name(), driver.getPhotoURL());
				currAtt.put(VehicleStatusMsgType.entitySubtype.name(), vehicleAttributes.get("ATTRIBUTE.ENTITY.SUBTYPE"));
				currAtt.put(VehicleStatusMsgType.deviceUniqueKey.name(), device.getAddress());
				currAtt.put(VehicleStatusMsgType.deviceUniqueKey.name(), device.getAddress());
				currAtt.put(VehicleStatusMsgType.deviceUniqueKey.name(), device.getAddress());
				currAtt.put(VehicleStatusMsgType.vehicleUniqueKey.name(), vehicle.getUniqueKey());
				vehicleStatus.setLastUpdated(deviceTime);
				vehicleStatus.setVehicleId(vehicle.getId());
				vehicleStatus.setDriverId(driver.getId());
				vehicleStatus.setAttributes(JSONMapper.toString(currAtt));
				dao.addOrUpdate(vehicleStatus);
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_VEHICLE_STATUS).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("JSONMapperException: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				MqClientFactory.mqClient.sendMessageToQueue(eventAuditBuilder.addEventType(EventType.UPDATE_VEHICLE_STATUS).addIdType(DEVICE_ADDRESS).addIdValue(null).addInput(message).addFailure(true).addOutput("HibernateHelperException: "+ e.getMessage()).toString(), EVENT_AUDITOR_QUEUE);
			} 
		}

		public AccountWSClient getAccountWSClient() {
			return accountWSClient;
		}

		public void setAccountWSClient(AccountWSClient accountWSClient) {
			this.accountWSClient = accountWSClient;
		}

		public AsyncDao getDao() {
			return dao;
		}

		public void setDao(AsyncDao dao) {
			this.dao = dao;
		}

		public EventAuditorBuilder getEventAuditBuilder() {
			return eventAuditBuilder;
		}

		public void setEventAuditBuilder(EventAuditorBuilder eventAuditBuilder) {
			this.eventAuditBuilder = eventAuditBuilder;
		}
		
		
}
