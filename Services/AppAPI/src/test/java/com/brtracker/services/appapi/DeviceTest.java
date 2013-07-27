package com.brtracker.services.appapi;

import java.util.Date;

import com.brtracker.services.appapi.endpoint.DeviceService;
import com.brtracker.shared.payload.controller.DeviceRequest;
import com.brtracker.shared.payload.controller.DeviceResponse;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.lookup.DeviceType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.SingleObjectResponse;

public class DeviceTest extends BaseAppAPITest{

	DeviceService deviceService = new DeviceService();
	
	public void testDeviceAdd() throws JSONMapperException {
		Device device = new Device();
		device.setAddress("1234567890");
		device.setAssigned(true);
		device.setComments("this is a comment");
		device.setDateCreated(new Date(System.currentTimeMillis()));
		device.setDeviceTypeId(DeviceType.CAMERA.getId());
		device.setAttributes("map of features");
		String out = deviceService.add(JSONMapper.toString(device));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}

	public void testDeviceUpdate() throws JSONMapperException {
		Device device = new Device();
		device.setAddress("1234567890");
		String out = deviceService.update(JSONMapper.toString(device));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	public void testDeviceList() throws JSONMapperException {
		DeviceRequest device = new DeviceRequest();
		Device d = new Device();
		d.setAddress("1234567890");
		d.setAssigned(true);
		device.setDevice(d);
		String out = deviceService.list(JSONMapper.toString(device));
		System.out.println(out);
		DeviceResponse resp = (DeviceResponse) JSONMapper.toObject(out, DeviceResponse.class);
		super.assertEquals(1, resp.getDeviceList().size());
	}

	public void testDeviceGet () throws JSONMapperException {
		String out = deviceService.get("1234567890");
		System.out.println(out);
		SingleObjectResponse  resp = (SingleObjectResponse) JSONMapper.toObject(out, SingleObjectResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	public void testDeviceDelete () throws JSONMapperException {
		Device device = new Device();
		device.setAddress("1234567890");
		String out = deviceService.delete(JSONMapper.toString(device));
		System.out.println(out);
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}

	
}
