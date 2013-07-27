package com.brtracker.services.controller;

import java.util.Date;
import com.brtracker.shared.payload.controller.DeviceRequest;
import com.brtracker.shared.payload.controller.DeviceResponse;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.lookup.DeviceType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.SingleObjectResponse;

public class DeviceTest extends BaseControllerTest {

	DeviceManager deviceManager = new DeviceManager();
	
	public void testDeviceAdd() throws JSONMapperException {
		Device device = new Device();
		device.setAddress("1234567890");
		device.setAssigned(true);
		device.setComments("this is a comment");
		device.setDateCreated(new Date(System.currentTimeMillis()));
		device.setDeviceTypeId(DeviceType.CAMERA.getId());
		device.setAttributes("map of features");
		String out = deviceManager.add(JSONMapper.toString(device));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}

	public void testDeviceUpdate() throws JSONMapperException {
		Device device = new Device();
		device.setAddress("1234567890");
		String out = deviceManager.update(JSONMapper.toString(device));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	public void testDeviceList() throws JSONMapperException {
		DeviceRequest device = new DeviceRequest();
		Device d = new Device();
		d.setAddress("1234567890");
		d.setAssigned(true);
		device.setDevice(d);
		String out = deviceManager.list(JSONMapper.toString(device));
		System.out.println(out);
		DeviceResponse resp = (DeviceResponse) JSONMapper.toObject(out, DeviceResponse.class);
		super.assertEquals(1, resp.getDeviceList().size());
	}

	public void testDeviceGet () throws JSONMapperException {
		Device device = new Device();
		device.setAddress("1234567890");
		String out = deviceManager.update(JSONMapper.toString(device));
		SingleObjectResponse  resp = (SingleObjectResponse) JSONMapper.toObject(out, SingleObjectResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	public void testDeviceDelete() throws JSONMapperException {
		Device device = new Device();
		device.setAddress("1234567890");
		String out = deviceManager.delete(JSONMapper.toString(device));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
}
