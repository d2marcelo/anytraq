package com.brtracker.services.midlink.processing;

import java.util.Date;
import java.util.List;

import com.brtracker.shared.utils.spring.HibernateHelperException;

public interface DeviceStateDao {

	void saveDeviceState(DeviceStateEntity deviceState) throws HibernateHelperException;
	DeviceStateEntity getDeviceState(String unitAddress) throws HibernateHelperException;
	List<DeviceStateEntity> getStaleDevices(Date check) throws HibernateHelperException;
	void updateStaleDevices(Date check) throws HibernateHelperException;
	
}
