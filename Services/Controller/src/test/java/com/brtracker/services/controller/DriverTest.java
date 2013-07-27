package com.brtracker.services.controller;

import java.util.Date;
import com.brtracker.shared.payload.controller.DriverRequest;
import com.brtracker.shared.payload.controller.DriverResponse;
import com.brtracker.shared.payload.controller.DriverSearchRequest;
import com.brtracker.shared.payload.controller.DriverSearchResponse;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;

public class DriverTest extends BaseControllerTest {

	DriverHandler driverManager = new DriverHandler();
	
	public void testDriverAdd() throws JSONMapperException {
		Driver driver = new Driver();
		//driver.setAccountId(new Long(1));
		driver.setDateCreated(new Date(System.currentTimeMillis()));
		driver.setFirstName("marcelo");
		driver.setLastName("oliveira");
		String out = driverManager.add(JSONMapper.toString(driver));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}

	public void testDriverUpdate() throws JSONMapperException {
		Driver driver = new Driver();
		String out = driverManager.update(JSONMapper.toString(driver));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	public void testDriverList() throws JSONMapperException {
		DriverRequest driverRequest = new DriverRequest();
		Driver driver = new Driver();
		driverRequest.setDriver(driver);
		String out = driverManager.list(JSONMapper.toString(driverRequest));
		DriverResponse resp = (DriverResponse) JSONMapper.toObject(out, DriverResponse.class);
		super.assertEquals(1, resp.getDriverList().size());
	}
	
	public void testDriverSearch() throws JSONMapperException {
		DriverSearchRequest driverRequest = new DriverSearchRequest();
		driverRequest.setAccountId(new Long(1));
		driverRequest.setSearchFor("marcelo");
		String out = driverManager.search(JSONMapper.toString(driverRequest));
		DriverSearchResponse resp = (DriverSearchResponse) JSONMapper.toObject(out, DriverSearchResponse.class);
		super.assertEquals(1, resp.getDriverSearch().size());
	}
	public void testDriverDelete() throws JSONMapperException {
		Driver driver = new Driver();
		String out = driverManager.delete(JSONMapper.toString(driver));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}

}

