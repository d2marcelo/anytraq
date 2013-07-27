package com.brtracker.services.tracking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.brtracker.services.tracking.dao.VehicleDAOImpl;
import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.payload.controller.lookup.EntityType;
import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.payload.tracking.VehicleStatusResponse;
import com.brtracker.shared.payload.tracking.data.VehicleStatus;
import com.brtracker.shared.payload.tracking.lookup.DeviceMessageAttributeType;
import com.brtracker.shared.payload.tracking.lookup.VehicleStatusMsgType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;
import com.brtracker.shared.utils.wsutils.SingleObjectResponse;

public class VehicleManager extends VehicleDAOImpl {

	private MyLogger logger = new MyLogger(VehicleManager.class);
		
	public String list (Long accountId) {
		try {
			logger.logInfo("list vehicle status");
			VehicleStatusResponse response = new VehicleStatusResponse();
			response.setVehicleStatusList(super.listVehicleStatus(accountId));
			response.setTotalCount(response.getVehicleStatusList().size());
			return JSONMapper.toString(response);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}

	public void update (String payload){
		try {
			Map<String,String> obj = (Map<String, String>) JSONMapper.toObject(payload, Map.class);
			upsert(getVehicleStatus(obj));
			
		} catch (JSONMapperException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public String upsert(String payload) {
		try {
			
			String CONTROLLER_URL = ServiceLookup.getServiceIP(ServiceType.CONTROLLER);
			DeviceMessageAttribute message = (DeviceMessageAttribute) JSONMapper.toObject(payload, DeviceMessageAttribute.class);
			String deviceTime = new Date(Long.parseLong(message.getAttributes().get(DeviceMessageAttributeType.TIMELLIS))).toString();
			String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.device_GET, CONTROLLER_URL);
			String endpoint = url +"?unitAddress="+message.getUnitAddress();
			logger.logInfo("client call to : "+endpoint);
			SingleObjectResponse resp = (SingleObjectResponse) JSONMapper.toObject(ClientCall.get(endpoint), SingleObjectResponse.class);
			VehicleStatus vehicleStatus;
			
			if (resp.isReturnValue()) {
				// device found
				Device device = (Device) JSONMapper.toObject(resp.getObject(), Device.class);
				vehicleStatus = getVehicleStatus(device.getId());
				String deviceVehMapUrl = CONTROLLER_URL+"/accountws/deviceVehicleMap/get?unitAddress="+device.getAddress();
				logger.logInfo("List asset associated with device:"+deviceVehMapUrl);
				String deviceVehicleMapStr = ClientCall.get(deviceVehMapUrl);
				SingleObjectResponse deviceObj = (SingleObjectResponse) JSONMapper.toObject(deviceVehicleMapStr, SingleObjectResponse.class);
				if (!deviceObj.isReturnValue()){
					// no drivervehiclemap remove current instance if it exist.
					if (vehicleStatus != null)
					super.delete(vehicleStatus);
					return "";
				}
				DeviceVehicleMap deviceVehicleMap = (DeviceVehicleMap) JSONMapper.toObject(deviceObj.getObject(), DeviceVehicleMap.class);
				Vehicle vehicle = deviceVehicleMap.getVehicle();
				Map<String,String> vehicleAttributes= (Map<String, String>) JSONMapper.toObject(vehicle.getAttributes(), Map.class);
				String driverVMapURL = CONTROLLER_URL+"/accountws/driverVehicleMap/get?vehicleId="+vehicle.getId();
				logger.logInfo("List driver associated with device:"+driverVMapURL);
				String driverVehicleMapStr = ClientCall.get(driverVMapURL);
				SingleObjectResponse driverObj = (SingleObjectResponse) JSONMapper.toObject(driverVehicleMapStr, SingleObjectResponse.class);
				if (!driverObj.isReturnValue()){
					// no drivervehiclemap remove current instance if it exist.
					if (vehicleStatus != null)
					super.delete(vehicleStatus);
					return "";
				}
				logger.logInfo("Object: "+driverObj.getObject());
				DriverVehicleMap driverVehicleMap = (DriverVehicleMap) JSONMapper.toObject(driverObj.getObject(), DriverVehicleMap.class);
				Driver driver = driverVehicleMap.getDriver();
				
				Map<String,String> currAtt = new HashMap<String,String>();
				if (vehicleStatus == null) {
					// new status
					vehicleStatus = new VehicleStatus();
					vehicleStatus.setAccountId(device.getAccount().getId());
					vehicleStatus.setDeviceId(device.getId());
					vehicleStatus.setDateCreated(new Date(System.currentTimeMillis()));
				} else {
					// append status
					// check if status needs to be closed.
					
					currAtt = (Map<String, String>) JSONMapper.toObject(vehicleStatus.getAttributes(), Map.class);
				}
				for (Map.Entry<String,String> map : message.getAttributes().entrySet()){
					logger.logInfo("key:"+map.getKey()+" value: "+map.getValue());
					currAtt.put(map.getKey(), map.getValue());
				}
				currAtt.put(VehicleStatusMsgType.accountId.name(), device.getAccount().getId().toString());
				currAtt.put(VehicleStatusMsgType.deviceId.name(), device.getId().toString());
				currAtt.put(VehicleStatusMsgType.deviceAddress.name(), device.getAddress().toString());
				currAtt.put(VehicleStatusMsgType.driverId.name(), driver.getId().toString());
				currAtt.put(VehicleStatusMsgType.driverName.name(), driver.getFirstName() +" "+driver.getLastName());
				currAtt.put(VehicleStatusMsgType.vehicleId.name(), vehicle.getId().toString());
				currAtt.put(VehicleStatusMsgType.entityType.name(), EntityType.getEntityType(vehicle.getType()).name());
				currAtt.put(VehicleStatusMsgType.driverPhoto.name(), driver.getPhotoURL());
				currAtt.put(VehicleStatusMsgType.entitySubtype.name(), vehicleAttributes.get("ATTRIBUTE.ENTITY.SUBTYPE"));
				currAtt.put(VehicleStatusMsgType.lastUpdated.name(), deviceTime);
				currAtt.put(VehicleStatusMsgType.deviceUniqueKey.name(), device.getAddress());
				currAtt.put(VehicleStatusMsgType.vehicleUniqueKey.name(), vehicle.getUniqueKey());
				
				vehicleStatus.setAttributes(JSONMapper.toString(currAtt));
				update(vehicleStatus);
				return ServiceResponse.getServiceResponse(true, "Vehicle Status Updated Successfully");
			} else {
				return ServiceResponse.getServiceResponse(false, "Unable to find unitAddress: "+ resp.getMessage());
			}
			
		} catch (JSONMapperException e) {
			e.printStackTrace();
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			e.printStackTrace();
			return ServiceResponseException.generateResponse(e);
		} catch (ServiceException e) {
			e.printStackTrace();
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	
	private String getVehicleStatus (Map<String,String> map ) throws JSONMapperException, ServiceException {
		
		String type = map.get("objectType");
		Long id = Long.parseLong(map.get("objectId"));
		String CONTROLLER_URL = ServiceLookup.getServiceIP(ServiceType.CONTROLLER);
		
		if (type.contains("DRIVER")) {
			String url = CONTROLLER_URL+"/accountws/driverVehicleMap/getVehicle?driverId="+id;
			SingleObjectResponse resp = (SingleObjectResponse) JSONMapper.toObject(ClientCall.get(url), SingleObjectResponse.class);
			Vehicle vehicle  = (Vehicle) JSONMapper.toObject(resp.getObject(), Vehicle.class);
			id = vehicle.getId();
		}
		
		String url  = CONTROLLER_URL+"/accountws/deviceVehicleMap/getDevice?vehicleId="+id;
		SingleObjectResponse resp = (SingleObjectResponse) JSONMapper.toObject(ClientCall.get(url), SingleObjectResponse.class);
		Device device =  (Device) JSONMapper.toObject(resp.getObject(), Device.class);
		VehicleStatus out = super.getVehicleStatus(device.getId());
		DeviceMessageAttribute msg= new DeviceMessageAttribute();
		msg.setAttributes((Map)JSONMapper.toObject(out.getAttributes(), Map.class));
		msg.setUnitAddress(device.getAddress());
		return JSONMapper.toString(msg);
	}
	
}

