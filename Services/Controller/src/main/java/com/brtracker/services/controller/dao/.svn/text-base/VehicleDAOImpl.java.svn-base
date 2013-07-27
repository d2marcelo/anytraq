package com.brtracker.services.controller.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.brtracker.shared.payload.controller.DriverSearchRequest;
import com.brtracker.shared.payload.controller.VehicleRequest;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.utils.spring.HibernateHelperException;


public class VehicleDAOImpl extends ControllerDAOImpl {
	
	
	public Driver getDriver(Long vehicleId) {
		DriverVehicleMap map = (DriverVehicleMap) findUniqueResult ("from DriverVehicleMap where vehicle.id="+vehicleId);
		if (map != null){
			return map.getDriver();
		}
		return null;
	}
	
	public Device getDevice(Long deviceId) {
		return  (Device) findUniqueResult ("from Device where id="+deviceId);
	}
	
	public void deleteVehicleDeviceMap (Long vehicleId) throws HibernateHelperException{
		List<DeviceVehicleMap> deviceVehicleMap = (List<DeviceVehicleMap>) find("from DeviceVehicleMap where vehicle.id="+vehicleId);
		for (DeviceVehicleMap map : deviceVehicleMap)
			delete(map);
		
	}
	public Vehicle getVehicle (String uniqueKey) {
		return (Vehicle) findUniqueResult ("from Vehicle where uniqueKey like'%"+uniqueKey+"%'");
	}
	
	public Vehicle getVehicle (Long id) {
		return (Vehicle) findUniqueResult ("from Vehicle where id="+id);
	}
	
	public List<Vehicle> listVehicleSearch (DriverSearchRequest driverSearchRequest) {
		Criteria criteria = createCriteria(Vehicle.class);
		LookupDAOCriteria.setAbstractSearchCriteria(driverSearchRequest, criteria);
		LookupDAOCriteria.setVehicleSearchCriteria(driverSearchRequest, criteria);
		return criteria.list();
	}
		
	public List<Vehicle> listVehicle (VehicleRequest vehicleRequest) {
		Criteria criteria = createCriteria(Vehicle.class);
		LookupDAOCriteria.setAbstractSearchCriteria(vehicleRequest, criteria);
		LookupDAOCriteria.setVehicleCriteria(vehicleRequest, criteria);
		List<Vehicle> vehicle = (List<Vehicle>) criteria.list();
		return vehicle;
	}
	
	public long listTotalVehicleCount(VehicleRequest vehicleRequest) {
		Criteria criteria = createCriteria(Vehicle.class);
		return LookupDAOCriteria.getRowCount(criteria);
	}
}
