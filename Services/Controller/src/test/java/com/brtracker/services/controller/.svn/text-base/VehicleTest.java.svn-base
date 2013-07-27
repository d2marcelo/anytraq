package com.brtracker.services.controller;

import java.util.Date;
import com.brtracker.shared.payload.controller.VehicleRequest;
import com.brtracker.shared.payload.controller.VehicleResponse;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;

public class VehicleTest extends BaseControllerTest {

	VehicleHandler vehicleManager = new VehicleHandler();
	
	public void testVehicleAdd() throws JSONMapperException {
		Vehicle vehicle = new Vehicle();
		vehicle.setDateCreated(new Date(System.currentTimeMillis()));
		String out =vehicleManager.add(JSONMapper.toString(vehicle));
		System.out.println(out);
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	public void testVehicleUpdate() throws JSONMapperException {
		Vehicle vehicle = new Vehicle();
		String out = vehicleManager.update(JSONMapper.toString(vehicle));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	public void testVehicleList() throws JSONMapperException {
		VehicleRequest vehicleRequest = new VehicleRequest();
		Vehicle vehicle = new Vehicle();
		vehicleRequest.setVehicle(vehicle);
		String out = vehicleManager.list(JSONMapper.toString(vehicleRequest));
		VehicleResponse resp = (VehicleResponse) JSONMapper.toObject(out, VehicleResponse.class);
		super.assertEquals(1, resp.getVehicleList().size());
	}
	
	public void testVehicleDelete() throws JSONMapperException {
		Vehicle vehicle = new Vehicle();
		String out = vehicleManager.delete(JSONMapper.toString(vehicle));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}

}
