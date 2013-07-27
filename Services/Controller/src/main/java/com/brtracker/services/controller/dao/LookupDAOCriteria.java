package com.brtracker.services.controller.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.brtracker.shared.payload.controller.AffiliateUserRequest;
import com.brtracker.shared.payload.controller.AlertConfigurationRequest;
import com.brtracker.shared.payload.controller.DeviceRequest;
import com.brtracker.shared.payload.controller.DeviceVehicleMapRequest;
import com.brtracker.shared.payload.controller.DriverRequest;
import com.brtracker.shared.payload.controller.DriverSearchRequest;
import com.brtracker.shared.payload.controller.DriverVehicleMapRequest;
import com.brtracker.shared.payload.controller.AccountUserRequest;
import com.brtracker.shared.payload.controller.VehicleRequest;
import com.brtracker.shared.payload.controller.data.Account;
import com.brtracker.shared.payload.controller.data.AccountUser;
import com.brtracker.shared.payload.controller.data.Affiliate;
import com.brtracker.shared.payload.controller.data.AffiliateUser;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.payload.controller.lookup.AlertCategoryType;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;
import com.brtracker.shared.payload.reporting.OrderCriteria;
import com.brtracker.shared.payload.reporting.OrderType;


// TODO: Auto-generated Javadoc
/**
 * The Class LookupDAOCriteria.
 */
public class LookupDAOCriteria   {

	/**
	 * Sets the abstract search criteria.
	 *
	 * @param search the search
	 * @param criteria the criteria
	 */
	public static void setAbstractSearchCriteria(AbstractSearchCriteria search, Criteria criteria){
		
		criteria.setFirstResult(search.getStartIndex());
		criteria.setMaxResults(search.getFetchSize());
		OrderCriteria order = search.getOrder();
		if (order != null){
		OrderType orderType = order.getOrder();
		String propertyName = order.getPropertyName();
		if (orderType != null  && propertyName != null){
			if (orderType.equals(OrderType.asc))
				criteria.addOrder(Order.asc(propertyName));
			else 
				criteria.addOrder(Order.desc(propertyName));
		}
		}
	}
	
	public static long getRowCount(Criteria criteria){
		return (Long) criteria.setProjection(Projections.rowCount()).list().get(0);
		
	}
	
	/**
	 * Sets the user criteria.
	 *
	 * @param userRequest the user request
	 * @param criteria the criteria
	 */
	public static void setAccountUserCriteria (AccountUserRequest userRequest, Criteria criteria) {
		    AccountUser user = (userRequest.getUser() == null) ? new AccountUser() : userRequest.getUser();
		    Long userId = user.getId();
		    String username = user.getUsername();
		    String password = user.getPassword();
		    Account account = user.getAccount();
			Long accountId = null;
			if (account != null) accountId =user.getAccount().getId();
			if (userId != null) criteria.add(Restrictions.eq("id", userId));
			if (accountId != null) criteria.add(Restrictions.eq("account.id", accountId));
			if (username != null) criteria.add(Restrictions.eq("username", username));
			if (password != null) criteria.add(Restrictions.eq("password", password));
	}
	
	public static void setAffiliateUserCriteria (AffiliateUserRequest userRequest, Criteria criteria) {
	    AffiliateUser user = (userRequest.getUser() == null) ? new AffiliateUser() : userRequest.getUser();
	    Long userId = user.getId();
	    String username = user.getUsername();
	    String password = user.getPassword();
	    Affiliate affiliate = user.getAffiliate();
	    Long accountId = null;
		if (affiliate != null) accountId =user.getAffiliate().getId();
		if (userId != null) criteria.add(Restrictions.eq("id", userId));
		if (accountId != null) criteria.add(Restrictions.eq("account.id", accountId));
		if (username != null) criteria.add(Restrictions.eq("username", username));
		if (password != null) criteria.add(Restrictions.eq("password", password));
}
	
	
	
	public static void setDeviceCriteria (DeviceRequest deviceRequest, Criteria criteria) {
	    Device device = (deviceRequest.getDevice() == null) ? new Device() : deviceRequest.getDevice();
	    Long deviceId = device.getId();
	    Long affiliateId = deviceRequest.getAffiliateId();
	    Long accountId = null;
	    if (device.getAccount() != null){
	    	accountId = device.getAccount().getId();
	    }
	    
		String unitAddress = device.getAddress();
		boolean assigned = device.isAssigned();
		if (deviceId != null) criteria.add(Restrictions.eq("id", deviceId));
		if (accountId != null) criteria.add(Restrictions.eq("account.id", accountId));
		if (unitAddress != null) criteria.add(Restrictions.eq("address", unitAddress));
		if (affiliateId != null) criteria.createCriteria("account").add(Restrictions.eq("affiliate.id", affiliateId));
		criteria.add(Restrictions.eq("assigned", assigned));
	}
	
	
	
	
	public static void setDriverSearchCriteria (DriverSearchRequest driverRequest, Criteria criteria) {
	    Long accountId = driverRequest.getAccountId();
	    int fetchSize = driverRequest.getFetchSize();
	    String search = driverRequest.getSearchFor();
	    int startIndex = driverRequest.getStartIndex();
	    String firstName=null;
	    String lastName=null;
	    boolean multi=false;
	    String[] multiSearch = search.split(" ");
	    System.out.println(multiSearch.length);
	    if (multiSearch.length > 1){
	    	firstName = multiSearch[0];
	    	lastName= multiSearch[1];
	    	multi=true;
	    }
	    if (multi){
	    	criteria.add(Restrictions.and(Restrictions.and(Restrictions.like("firstName", "%"+firstName+"%"),Restrictions.like("lastName", "%"+lastName+"%")), Restrictions.eq("account.id", accountId)));
	    } else {
	    	criteria.add(Restrictions.and(Restrictions.or(Restrictions.like("firstName", "%"+search+"%"),Restrictions.like("lastName", "%"+search+"%")), Restrictions.eq("account.id", accountId)));
	    }
	    criteria.setMaxResults(fetchSize);
	    criteria.setFirstResult(startIndex);
	}
	
	public static long  setDriverSearchCriteriaTotal (DriverSearchRequest driverRequest, Criteria criteria) {
	    Long accountId = driverRequest.getAccountId();
	    String search = driverRequest.getSearchFor();
	    if (search != null)
		criteria.add(Restrictions.and(Restrictions.or(Restrictions.like("firstName", "%"+search+"%"),Restrictions.like("lastName", "%"+search+"%")), Restrictions.eq("account.id", accountId)));
	    else {
	    if (accountId != null)
	    criteria.add(Restrictions.eq("account.id", accountId));
	    }
	    return (Long) criteria.setProjection(Projections.rowCount()).list().get(0);
	}
	
	public static void setDeviceVehicleCriteria (DeviceVehicleMapRequest request, Criteria criteria){
		  DeviceVehicleMap deviceVehicleMap = (request.getDeviceVehicleMap() == null) ? new DeviceVehicleMap() : request.getDeviceVehicleMap();
		  Vehicle vehicle = deviceVehicleMap.getVehicle();
		  if (vehicle != null) {
		  Criteria vehicleCriteria = criteria.createCriteria("vehicle");
		  Long accountId = vehicle.getAccount().getId();
		  int type = vehicle.getType();
		  String uniqueKey = vehicle.getUniqueKey();
		  Long id = vehicle.getId();
		  
		    if (type != 0) vehicleCriteria.add(Restrictions.eq("type", type));
			if (accountId != null) vehicleCriteria.add(Restrictions.eq("account.id", accountId));
			if (id != null) vehicleCriteria.add(Restrictions.eq("id", id));
			if (uniqueKey != null) vehicleCriteria.add(Restrictions.eq("uniqueKey", uniqueKey));
		  }
		  
	}
	public static void setDriverCriteria (DriverRequest driverRequest, Criteria criteria) {
	    Driver driver = (driverRequest.getDriver() == null) ? new Driver() : driverRequest.getDriver();
	    Long driverId = driver.getId();
	    Account account = driver.getAccount();
	    Long accountId = null;
	    if (account != null) accountId = driver.getAccount().getId();
	    String uniqueKey = driver.getUniqueKey();
	    String firstname = driver.getFirstName();
	    String lastname = driver.getLastName();
		if (driverId != null) criteria.add(Restrictions.eq("id", driverId));
		if (firstname != null) criteria.add(Restrictions.like("firstName", "%"+firstname+"%"));
		if (lastname != null) criteria.add(Restrictions.like("lastName", "%"+lastname+"%"));
		if (uniqueKey != null) criteria.add(Restrictions.eq("uniqueKey", uniqueKey));
		if (accountId != null) criteria.add(Restrictions.eq("account.id", accountId));
	}
	
	public static void setDriverVehicleMapCriteria (DriverVehicleMapRequest driverRequest, Criteria criteria) {
		DriverVehicleMap driverVehicle = (driverRequest.getDriverVehicleMap() == null) ? new DriverVehicleMap() : driverRequest.getDriverVehicleMap();
	    Driver driver = driverVehicle.getDriver();
	    Vehicle vehicle = driverVehicle.getVehicle();
	    if (driver != null){
		Criteria driverCriteria = criteria.createCriteria("driver");
		Long driverId = driver.getId();
		Long accountId = driver.getAccount().getId();
		if (driverId != null) driverCriteria.add(Restrictions.eq("id", driverId));
		if (accountId != null) driverCriteria.add(Restrictions.eq("account.id", accountId));
	    }
	    if (vehicle != null){
	    	Criteria vehicleCriteria = criteria.createCriteria("vehicle");
	    	Long accountId = vehicle.getAccount().getId();
	    	if (accountId != null) vehicleCriteria.add(Restrictions.eq("account.id", accountId));	
	    }
	}
	
	
	public static void setVehicleCriteria (VehicleRequest vehicleRequest, Criteria criteria) {
	    Vehicle vehicle = (vehicleRequest.getVehicle() == null) ? new Vehicle() : vehicleRequest.getVehicle();
	    Long vehicleId = vehicle.getId();
	    Long accountId = null;
	    if (vehicle.getAccount() != null)
	    accountId = vehicle.getAccount().getId();
	    String uniqueKey = vehicle.getUniqueKey();
		if (vehicleId != null) criteria.add(Restrictions.eq("id", vehicleId));
		if (accountId != null) criteria.add(Restrictions.eq("account.id", accountId));
		if (uniqueKey != null) criteria.add(Restrictions.like("uniqueKey", "%"+uniqueKey+"%"));
	}

	public static void setAlertConfigurationCriteria (AlertConfigurationRequest request,Criteria criteria){
		Long accountId = request.getAccountId();
		AlertCategoryType category = request.getAlertCategoryType();
		Long userId = request.getUserId();
	    if (accountId != null) criteria.add(Restrictions.eq("accountId", accountId));
	    if (category != null) criteria.createCriteria("alertType").add(Restrictions.eq("alertCategoryId", category.getId()));
	    if (userId != null) criteria.add(Restrictions.eq("user.id", userId));
	}
	
	public static void setVehicleSearchCriteria(DriverSearchRequest driverSearchRequest, Criteria criteria) {
		Long accountId = driverSearchRequest.getAccountId();
	    int fetchSize = driverSearchRequest.getFetchSize();
	    String search = driverSearchRequest.getSearchFor();
	    int startIndex = driverSearchRequest.getStartIndex();
		criteria.add(Restrictions.and(Restrictions.or(Restrictions.eq("uniqueKey", search),Restrictions.eq("make", search)), Restrictions.eq("account.id", accountId)));
	    criteria.setMaxResults(fetchSize);
	    criteria.setFirstResult(startIndex);
	}
	
	public static void setDeviceVehicleMapSearchCriteria(DriverSearchRequest driverSearchRequest, Criteria criteria) {
		Long accountId = driverSearchRequest.getAccountId();
	    int fetchSize = driverSearchRequest.getFetchSize();
	    String search = driverSearchRequest.getSearchFor();
	    int startIndex = driverSearchRequest.getStartIndex();
	   Criteria crit = criteria.createCriteria("device");
	    if (search != null)
	    	crit.add(Restrictions.and(Restrictions.eq("address", search), Restrictions.eq("accountId", accountId)));
	    else {
		    if (accountId != null)
		    	crit.add(Restrictions.eq("account.id", accountId));
		}
	    criteria.setMaxResults(fetchSize);
	    criteria.setFirstResult(startIndex);
	}
	
	public static long setDeviceVehicleMapSearchCriteriaTotal (DriverSearchRequest driverSearchRequest, Criteria criteria) {
		Long accountId = driverSearchRequest.getAccountId();
	    String search = driverSearchRequest.getSearchFor();
	    Criteria crit = criteria.createCriteria("device");
	    if (search != null)
	    	crit.add(Restrictions.and(Restrictions.eq("address", search), Restrictions.eq("account.id", accountId)));
	    else {
		    if (accountId != null)
		    	crit.add(Restrictions.eq("account.id", accountId));
		}
	    return (Long) crit.setProjection(Projections.rowCount()).list().get(0);
	}
	
}
