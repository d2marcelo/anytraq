package com.brtracker.services.tracking;

import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.payload.tracking.VehicleStatusResponse;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;

public class VehicleStatusTest extends BaseTrackingTest {
	
	VehicleManager manager = new VehicleManager();

	public static void main(String[] args) {
		System.out.println("test");
	}
	public void testVehicleStatusUpsert () throws JSONMapperException {
		DeviceMessageAttribute att = new DeviceMessageAttribute();
		att.setUnitAddress("045092047146");
		att.put("driverId", "4");
		att.put("deviceId", "1");
		att.put("accountId", "1");
		att.put("accountId", "1");
		att.put("latitude","-17.42452544660558");
		att.put("longitude","-49.641133546829224");
		att.put("licencePlate","12312312");
		att.put("vehicleState","STOPPED");
		att.put("currentFuel","null");
		att.put("currentSpeed","0.0");
		att.put("driverName","Marcelo Oliveira");
		att.put("driverPhoto","driver1_small.png");
		att.put("course","78.5");
		
		String out = manager.upsert(JSONMapper.toString(att));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	public void testVehicleStatusList () throws JSONMapperException {
		String out = manager.list(new Long(1));
		VehicleStatusResponse resp = (VehicleStatusResponse) JSONMapper.toObject(out, VehicleStatusResponse.class);
		super.assertEquals(1, resp.getVehicleStatusList().size());
	}
}
