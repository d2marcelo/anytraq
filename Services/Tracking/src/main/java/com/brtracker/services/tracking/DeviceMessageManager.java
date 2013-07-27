package com.brtracker.services.tracking;

import java.util.Date;
import java.util.Map;
import javax.jms.JMSException;
import com.brtracker.services.tracking.async.AsyncMessageProducer;
import com.brtracker.services.tracking.dao.DeviceMessageDAOImpl;
import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.lookup.ServiceType;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.payload.tracking.data.DeviceMessage;
import com.brtracker.shared.payload.tracking.lookup.DeviceMessageAttributeType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;
import com.brtracker.shared.utils.wsutils.SingleObjectResponse;
import com.seabright.shared.caching.DeviceVehicleMapCaching;
import com.seabright.shared.caching.DriverVehicleMapCaching;

public class DeviceMessageManager extends DeviceMessageDAOImpl {

	MyLogger logger = new MyLogger(DeviceMessageManager.class);
	String CONTROLLER_URL = ServiceLookup.getServiceIP(ServiceType.CONTROLLER);
	
	public String add (String payload){
		String response  = "";
		try {
			DeviceMessageAttribute deviceMessageAtt = (DeviceMessageAttribute) JSONMapper.toObject(payload, DeviceMessageAttribute.class);
			Map<String,String> attribute = deviceMessageAtt.getAttributes();
			DeviceMessage deviceMessage = new DeviceMessage();
			deviceMessage.setDateCreated(new Date(System.currentTimeMillis()));
			deviceMessage.setDeviceTime(attribute.get(DeviceMessageAttributeType.TIMELLIS));
			Driver driver = Driver(deviceMessageAtt.getUnitAddress());
			deviceMessage.setDriverId(driver.getId());
			deviceMessage.setAttributes(JSONMapper.toString(attribute));
			super.saveOrUpdate(deviceMessage);
			AsyncMessageProducer.send(deviceMessageAtt, getDevice(deviceMessageAtt.getUnitAddress()), driver);
			response = ServiceResponse.getServiceResponse(true, "Device message added successfuly");
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
			return ServiceResponseException.generateResponse(e);
		} catch (ServiceException e) {
			logger.logError(e.getMessage(), e);
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			logger.logError(e.getMessage(), e);
			return ServiceResponseException.generateResponse(e);
		} catch (JMSException e) {
			logger.logError(e.getMessage(), e);
		}
		return  response;
		
	}
	
	private Device getDevice (String unitAddress) throws JSONMapperException, ServiceException{
		String url = ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.device_GET, CONTROLLER_URL);
		String endpoint = url +"?unitAddress="+unitAddress;
		logger.logInfo("client call to : "+endpoint);
		SingleObjectResponse resp = (SingleObjectResponse) JSONMapper.toObject(ClientCall.get(endpoint), SingleObjectResponse.class);
		return (Device) JSONMapper.toObject(resp.getObject(), Device.class);
	}
	private Driver Driver(String unitAddress) throws ServiceException, JSONMapperException{
		DriverVehicleMapCaching driverVehicleMapCaching = new DriverVehicleMapCaching();
		DeviceVehicleMapCaching deviceVechicleMapCaching = new DeviceVehicleMapCaching();
		String deviceVehicleMapEndpoint = ControllerInterfaceLookup.deviceVehicleMap_GET.getPath() +"?unitAddress="+unitAddress;
		DeviceVehicleMap deviceVehicleMap = deviceVechicleMapCaching.getDeviceVehicleMap(unitAddress +"_GET_DEVICE_VEHICLE_MAP", deviceVehicleMapEndpoint);
		String driverVehicleMapEndpoint = ControllerInterfaceLookup.driverVehicleMap_GET.getPath() +"?vehicleId="+deviceVehicleMap.getVehicle().getId();
		DriverVehicleMap driverVehicleMap = driverVehicleMapCaching.getDriverVehicleMap(unitAddress +"GET_DRIVER_VEHICLE_MAP", driverVehicleMapEndpoint);
		return driverVehicleMap.getDriver();
	}
}
