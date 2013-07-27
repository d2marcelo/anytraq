package com.brtracker.services.tracking.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.brtracker.shared.payload.tracking.data.DeviceHistory;
import com.brtracker.shared.utils.logging.MyLogger;

public class DeviceHistoryDAOImpl extends TrackingDAOImpl {
	private MyLogger logger = new MyLogger(DeviceHistoryDAOImpl.class);
	
	public List<DeviceHistory> getDeviceHistory(Long deviceId, String date) {
	logger.logInfo("DriverId:"+ deviceId+" date:"+date);
	Criteria criteria = createCriteria(DeviceHistory.class);
	criteria.add(Restrictions.eq("deviceId", deviceId));
	criteria.add(Restrictions.eq("searchDate", date));
	List<DeviceHistory> list = criteria.list();
	logger.logInfo("Return size:"+ list.size());
	return list;
	}

	public List<String> getDeviceDates(Long deviceId) {
		logger.logInfo("DriverId:"+ deviceId);
		return  (List<String>) super.find("select distinct searchDate from DeviceHistory where deviceId="+deviceId);
	}
	
	

}
