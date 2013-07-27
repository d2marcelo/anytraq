package com.brtracker.services.async.dao;

import com.brtracker.shared.payload.tracking.data.ActivityReport;
import com.brtracker.shared.payload.tracking.data.DeviceHistory;
import com.brtracker.shared.payload.tracking.data.DeviceStatus;
import com.brtracker.shared.payload.tracking.data.DriverBehavior;
import com.brtracker.shared.payload.tracking.data.GraphData;
import com.brtracker.shared.payload.tracking.data.IdleReport;
import com.brtracker.shared.payload.tracking.data.VehicleStatus;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public interface AsyncDao {

	public DeviceHistory previousDeviceHistory (Long deviceId);
	public GraphData previousGraphData (Long deviceId, int graphDataType);
	public IdleReport previousIdleReport (Long deviceId);
	public ActivityReport previousActivityReport (Long deviceId);
	public DriverBehavior getDriverBehavior(Long driverId);
	public void addOrUpdate (Object data) throws HibernateHelperException ;
	public VehicleStatus getVehicleStatus (String entity , Long id);
	public DeviceHistory previousRecord (Long id) throws HibernateHelperException;
	public DeviceStatus getDeviceStatus(String address);
	public void delete(Object object)throws HibernateHelperException ;
}
