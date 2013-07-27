package com.brtracker.services.controller.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import com.brtracker.shared.payload.controller.DriverRequest;
import com.brtracker.shared.payload.controller.DriverSearchRequest;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;

public class DriverDAOImpl extends ControllerDAOImpl {

	
	public Driver getDriver (String uniqueKey){
		return (Driver) super.findUniqueResult("from Driver where uniqueKey like '%"+uniqueKey+"%'");
	}
	
	public Driver getDriver (Long id){
		return (Driver) super.findUniqueResult("from Driver where id="+id);
	}
	
	public List<Driver> listDriver(DriverRequest driverRequest) {
		Criteria criteria = createCriteria(Driver.class);
		LookupDAOCriteria.setAbstractSearchCriteria(driverRequest, criteria);
		LookupDAOCriteria.setDriverCriteria(driverRequest, criteria);
		List<Driver> driver = (List<Driver>) criteria.list();
		return driver;
	}
	
	public long listTotalDriverCount(DriverRequest driverRequest) {
		Criteria criteria = createCriteria(Driver.class);
		LookupDAOCriteria.setDriverCriteria(driverRequest, criteria);
		return LookupDAOCriteria.getRowCount(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Driver> listDriverSearch (DriverSearchRequest driverSearchRequest) {
		Criteria criteria = createCriteria(Driver.class);
		LookupDAOCriteria.setAbstractSearchCriteria(driverSearchRequest, criteria);
		LookupDAOCriteria.setDriverSearchCriteria(driverSearchRequest, criteria);
		return criteria.list();
	}
	
	
	public long listTotalDriverSearchCount(DriverSearchRequest driverSearchRequest) {
		Criteria criteria = createCriteria(Driver.class);
		return LookupDAOCriteria.setDriverSearchCriteriaTotal(driverSearchRequest, criteria);
	}
	
	public List<Driver> listVehicleMap (List<DeviceVehicleMap> deviceVehicleList){
		List<Driver> driver = new ArrayList<Driver>();
		for (DeviceVehicleMap v : deviceVehicleList){
			List<DriverVehicleMap> list = (List<DriverVehicleMap>) find("from DriverVehicleMap where vehicle.id="+v.getVehicle().getId());
			if (!list.isEmpty()) driver.add(list.get(0).getDriver());
		}
		return driver;
	}
}
