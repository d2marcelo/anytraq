package com.brtracker.services.midlink.processing;

import java.util.Date;
import java.util.List;

import com.brtracker.shared.payload.midlink.VehicleState;
import com.brtracker.shared.utils.spring.HibernateHelper;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class DeviceStateDaoImpl extends HibernateHelper implements
		DeviceStateDao {

	@Override
	public void saveDeviceState(DeviceStateEntity deviceState) throws HibernateHelperException {
		super.saveOrUpdate(deviceState);
	}

	@Override
	public List<DeviceStateEntity> getStaleDevices(Date check) throws HibernateHelperException  {
		List<DeviceStateEntity> notifications = super.find(
				"fetch.stale.devices", 
				new String[] {"checkDate", "state"}, 
				new Object[] {check, VehicleState.MOVING.name()});
		
		return notifications;
	}

	@Override
	public void updateStaleDevices(Date check) throws HibernateHelperException  {
		super.executeNamedUpdateWithParams("update.stale.devices", 
				new String[] {"newState", "checkDate", "oldState"}, 
				new Object[] {VehicleState.STOPPED.name(), check, VehicleState.MOVING.name()});
	}

	@Override
	public DeviceStateEntity getDeviceState(String unitAddress)
			throws HibernateHelperException {
		List<DeviceStateEntity> devices = super.find("find.by.unit.address", 
				new String[] {"unitAddress"},
				new Object[] {unitAddress});
		return devices != null && devices.size() == 1 ? devices.get(0) : null;
	}

}
