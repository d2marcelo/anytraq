package com.brtracker.services.async.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.brtracker.shared.payload.tracking.data.ActivityReport;
import com.brtracker.shared.payload.tracking.data.DeviceHistory;
import com.brtracker.shared.payload.tracking.data.DeviceStatus;
import com.brtracker.shared.payload.tracking.data.DriverBehavior;
import com.brtracker.shared.payload.tracking.data.GraphData;
import com.brtracker.shared.payload.tracking.data.IdleReport;
import com.brtracker.shared.payload.tracking.data.VehicleStatus;
import com.brtracker.shared.utils.spring.HibernateHelper;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class AsyncDaoImpl extends HibernateHelper implements AsyncDao {
	
	
	public DeviceHistory previousDeviceHistory (Long deviceId){
		Criteria criteria = createCriteria(DeviceHistory.class);
		criteria.add(Restrictions.eq("deviceId", deviceId));
		criteria.setMaxResults(1);
		criteria.addOrder(Order.desc("id"));
		return (DeviceHistory) criteria.uniqueResult();
	}
	
	
	public GraphData previousGraphData (Long deviceId, int graphDataType){
		Criteria criteria = createCriteria(GraphData.class);
		criteria.add(Restrictions.eq("deviceId", deviceId));
		criteria.add(Restrictions.eq("graphDataType", graphDataType));
		criteria.setMaxResults(1);
		criteria.addOrder(Order.desc("id"));
		return (GraphData) criteria.uniqueResult();
	}
	
	public IdleReport previousIdleReport (Long deviceId){
		Criteria criteria = createCriteria(IdleReport.class);
		criteria.add(Restrictions.eq("deviceId", deviceId));
		criteria.add(Restrictions.eq("tripStarted", false));
		criteria.setMaxResults(1);
		criteria.addOrder(Order.desc("id"));
		return (IdleReport) criteria.uniqueResult();
	}
	
	
	public ActivityReport previousActivityReport (Long deviceId){
		Criteria criteria = createCriteria(ActivityReport.class);
		criteria.add(Restrictions.eq("deviceId", deviceId));
		criteria.add(Restrictions.eq("tripFinished", false));
		criteria.setMaxResults(1);
		criteria.addOrder(Order.desc("id"));
		return (ActivityReport) criteria.uniqueResult();
	}
	
	public DriverBehavior getDriverBehavior(Long driverId){
		DriverBehavior driverBehavior = (DriverBehavior) super.findUniqueResult("from DriverBehavior where driverId="+driverId);
		return driverBehavior;
	}
	
	
	public void add (Object data) throws HibernateHelperException{
		super.saveOrUpdate(data);
	}
	
	public VehicleStatus getVehicleStatus (String entity , Long id) {
		return (VehicleStatus) super.findUniqueResult("from VehicleStatus where "+entity+"="+id);
	}
	public DeviceHistory previousRecord (Long id) throws HibernateHelperException{
		 Criteria criteria = createCriteria(DeviceHistory.class);
		 criteria.add(Restrictions.lt("id", id));
		 criteria.setMaxResults(1);
		 return (DeviceHistory) criteria.uniqueResult();
	}
	
	public DeviceStatus getDeviceStatus(String address) {
		return (DeviceStatus) super.findUniqueResult("from DeviceStatus where deviceAddress='"+address+"'");
	}


	@Override
	public void addOrUpdate(Object data) throws HibernateHelperException {
		super.saveOrUpdate(data);
		
	}
	
}
