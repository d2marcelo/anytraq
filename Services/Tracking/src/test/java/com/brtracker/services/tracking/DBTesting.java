package com.brtracker.services.tracking;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.lookup.DeviceType;
import com.brtracker.shared.utils.cache.CacheClient;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.Context;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.SingleObjectResponse;


public class DBTesting {

	public static Test suite() {
        TestSuite suite = new TestSuite("Test suite for com.xxx.yyyy.test");
        //$JUnit-BEGIN$
        
        //$JUnit-END$
        return suite;
    }
	public static void main(String[] args) throws JSONMapperException, HibernateHelperException, InterruptedException, ServiceException {
	
		String out = ClientCall.get("http://107.20.202.100:8080/accountws/driverVehicleMap/get?vehicleId=1285");
		System.out.println(out);
		SingleObjectResponse m = (SingleObjectResponse) JSONMapper.toObject(out, SingleObjectResponse.class);
		System.out.println(JSONMapper.toObject(m.getObject(), DriverVehicleMap.class));
		
		
		System.exit(1);
		Context.getContext();
		String test = "test";
		CacheClient instance = CacheClient.get();
		instance.set("test", 3600, test);
		Object obect = instance.getFuture("test");
		System.out.println(obect);
		System.exit(1);
		//Timestamp time12 = (Timestamp) CacheClient.getInstance().getFuture("time");
		//System.out.println(time12.getTime());
		
		String  add= "1111111111";
		String  serial= "8888888888";
		for (int i=10 ; i<=90; i++){
			Device device = new Device();
			device.setAddress(add+i);
			device.setAssigned(true);
			device.setAttributes("");
			device.setComments("test device");
			device.setDeviceTypeId(DeviceType.TRACKING.getId());
			device.setSerial(serial+i);
			ClientCall.post("http://localhost:8080/appapi/device/add", JSONMapper.toString(device));
		}
		System.exit(1);
	
		Context.getContext();
		System.exit(1);
		
		Thread.sleep(120000);
		Timestamp time2 = new Timestamp(System.currentTimeMillis());
		System.out.println(time2);
		System.exit(1);
		Context.getContext();
		
				

	}
	
}
