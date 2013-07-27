package com.brtracker.services.controller.dao;

import java.util.List;
import org.hibernate.Criteria;
import com.brtracker.shared.payload.controller.DeviceRequest;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.utils.spring.HibernateHelperException;


// TODO: Auto-generated Javadoc
/**
 * The Class DeviceDAOImpl.
 */
public class DeviceDAOImpl extends ControllerDAOImpl {
	
	
	public Device getDevice (String unitAddress) {
		return (Device) super.findUniqueResult("from Device where address='"+unitAddress+"'");
	}

	public Device getDevice (Long id) throws HibernateHelperException{
		return (Device) super.findUniqueResult("from Device where id="+id);
	}
	
	
	public List<Device> listDevices(DeviceRequest deviceRequest) {
		Criteria criteria = createCriteria(Device.class);
		LookupDAOCriteria.setAbstractSearchCriteria(deviceRequest, criteria);
		LookupDAOCriteria.setDeviceCriteria(deviceRequest, criteria);
		List<Device> device = (List<Device>) criteria.list();
		return device;
	}
	
	public long listTotalDeviceCount(DeviceRequest deviceRequest) {
		Criteria criteria = super.createCriteria(Device.class);
		LookupDAOCriteria.setDeviceCriteria(deviceRequest, criteria);
		return LookupDAOCriteria.getRowCount(criteria);
	}
	
}
