package com.brtracker.services.tracking.dao;

import com.brtracker.shared.payload.tracking.data.DeviceMessage;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class DeviceMessageDAOImpl extends TrackingDAOImpl {

	
	public void add (DeviceMessage deviceMessage) throws HibernateHelperException {
		super.saveOrUpdate(deviceMessage);
	}
}
