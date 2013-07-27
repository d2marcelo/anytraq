package com.brtracker.services.controller.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;

import com.brtracker.shared.payload.controller.DriverVehicleDeviceMap;
import com.brtracker.shared.payload.controller.DriverVehicleMapRequest;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;

public class DriverVehicleMapDAOImpl extends ControllerDAOImpl {

	
	public List<DriverVehicleMap> list (DriverVehicleMapRequest driverVehicleMapRequest) {
		Criteria criteria = createCriteria(DriverVehicleMap.class);
		LookupDAOCriteria.setAbstractSearchCriteria(driverVehicleMapRequest, criteria);
		LookupDAOCriteria.setDriverVehicleMapCriteria(driverVehicleMapRequest, criteria);
		return criteria.list();
	}
	
	
	public DriverVehicleMap getMap(String uniqueKey) {
		return (DriverVehicleMap) findUniqueResult("from DriverVehicleMap d inner join fetch d.vehicle v where v.uniqueKey='"+uniqueKey+"'");
	}

	public DriverVehicleMap getSingleDriverVehicleMap(Long id) {
		return (DriverVehicleMap) findUniqueResult("from DriverVehicleMap where id="+id);
	}
	
	public DriverVehicleMap getDriverVehicleMap(Long vehicleId) {
		return (DriverVehicleMap) findUniqueResult("from DriverVehicleMap where vehicle.id="+vehicleId);
	}
	
	public List<DriverVehicleMap> getDriverMap(Long driverId) {
		return (List<DriverVehicleMap>) find("from DriverVehicleMap where driver.id="+driverId);
	}
	
	public List<DriverVehicleMap> getVehicleMap(Long vehicleId) {
		return (List<DriverVehicleMap>) find("from DriverVehicleMap where vehicle.id="+vehicleId);
	}
	
	public List<DriverVehicleMap> listDriverMap (List<Driver> driverList){
		List<DriverVehicleMap> driver = new ArrayList<DriverVehicleMap>();
		for (Driver d : driverList){
			List<DriverVehicleMap> list = getDriverMap(d.getId());
			if (!list.isEmpty()) driver.add(list.get(0));
		}
		return driver;
	}
	
	public List<DriverVehicleMap> listVehicleMap (List<Vehicle> vehicleList){
		List<DriverVehicleMap> driver = new ArrayList<DriverVehicleMap>();
		for (Vehicle v : vehicleList){
			List<DriverVehicleMap> list = getVehicleMap(v.getId());
			if (!list.isEmpty()) driver.add(list.get(0));
		}
		return driver;
	}

	public Vehicle getVehicle(Long driverId) {
		DriverVehicleMap map =  (DriverVehicleMap) findUniqueResult("from DriverVehicleMap dv inner join fetch dv.driver d where d.id="+driverId);
		return map.getVehicle();
	}
	
}
