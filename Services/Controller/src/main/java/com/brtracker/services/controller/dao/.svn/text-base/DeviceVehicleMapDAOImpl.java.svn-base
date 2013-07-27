package com.brtracker.services.controller.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import com.brtracker.shared.payload.controller.DeviceVehicleMapRequest;
import com.brtracker.shared.payload.controller.DriverSearchRequest;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Driver;

public class DeviceVehicleMapDAOImpl  extends ControllerDAOImpl {

	
	public DeviceVehicleMap getMap (String unitAddress){
		return (DeviceVehicleMap) findUniqueResult("from DeviceVehicleMap d inner join fetch d.device dev where dev.address='"+unitAddress+"'");
	}
	

	public List<DeviceVehicleMap> listDeviceVehicleMap (DeviceVehicleMapRequest deviceVehicleRequest){
		Criteria criteria = createCriteria(DeviceVehicleMap.class);
		LookupDAOCriteria.setDeviceVehicleCriteria(deviceVehicleRequest, criteria);
		LookupDAOCriteria.setAbstractSearchCriteria(deviceVehicleRequest, criteria);
		return criteria.list();
	}
	
	public long listDeviceVehicleMapCount (){
		return LookupDAOCriteria.getRowCount(createCriteria(DeviceVehicleMap.class));
	}
	public DeviceVehicleMap getMap (Long id){
		return (DeviceVehicleMap) findUniqueResult("from DeviceVehicleMap where id="+id);
	}
	
	public List<DeviceVehicleMap> listDeviceVehicleMap (List<Device> deviceList){
		List<DeviceVehicleMap> device = new ArrayList<DeviceVehicleMap>();
		for (Device d : deviceList){
			List<DeviceVehicleMap> list = getDeviceVehicleMap(d.getId());
			if (!list.isEmpty()) device.add(list.get(0));
		}
		return device;
	}

	public List<DeviceVehicleMap> getDeviceVehicleMap(Long deviceId) {
		return (List<DeviceVehicleMap>) find("from DeviceVehicleMap where device.id="+deviceId);
	}

	public DeviceVehicleMap getDeviceMap(String uniqueKey) {
		return (DeviceVehicleMap) findUniqueResult("from DeviceVehicleMap dvm inner join fetch dvm.vehicle v where v.uniqueKey='"+uniqueKey+"'");
	}
	
	public Device getDevice(Long vehicleId) {
		DeviceVehicleMap map =  (DeviceVehicleMap) findUniqueResult("from DeviceVehicleMap dvm inner join fetch dvm.vehicle v where v.id="+vehicleId);
		return map.getDevice();
	}
	
	public List<Device> listDevices (Long vehicleId) {
		List<DeviceVehicleMap> map =   (List<DeviceVehicleMap>) find("from DeviceVehicleMap where vehicle.id="+vehicleId);
		List<Device> deviceList = new ArrayList<Device>();
		for (DeviceVehicleMap m : map) deviceList.add(m.getDevice());
		return deviceList;
	}
	
	public long listTotalDeviceSearchCount(DriverSearchRequest deviceRequest) {
		Criteria criteria = super.createCriteria(DeviceVehicleMap.class);
		return LookupDAOCriteria.setDeviceVehicleMapSearchCriteriaTotal(deviceRequest, criteria);
	}
	
	public List<DeviceVehicleMap> listDeviceSearch (DriverSearchRequest driverSearchRequest) {
		Criteria criteria = createCriteria(DeviceVehicleMap.class);
		LookupDAOCriteria.setAbstractSearchCriteria(driverSearchRequest, criteria);
		LookupDAOCriteria.setDeviceVehicleMapSearchCriteria(driverSearchRequest, criteria);
		return criteria.list();
	}
}
