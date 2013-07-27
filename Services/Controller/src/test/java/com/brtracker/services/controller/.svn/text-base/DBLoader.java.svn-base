package com.brtracker.services.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.brtracker.services.controller.dao.AccountDAOImpl;
import com.brtracker.services.controller.dao.DriverDAOImpl;
import com.brtracker.shared.payload.controller.AccountUserRequest;
import com.brtracker.shared.payload.controller.DeviceRequest;
import com.brtracker.shared.payload.controller.DeviceVehicleMapRequest;
import com.brtracker.shared.payload.controller.DriverSearchRequest;
import com.brtracker.shared.payload.controller.LoginRequest;
import com.brtracker.shared.payload.controller.data.Account;
import com.brtracker.shared.payload.controller.data.AccountUser;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.controller.data.DeviceVehicleMap;
import com.brtracker.shared.payload.controller.data.Driver;
import com.brtracker.shared.payload.controller.data.DriverVehicleMap;
import com.brtracker.shared.payload.controller.data.Vehicle;
import com.brtracker.shared.payload.tracking.VehicleStatusResponse;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.Context;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;


public class DBLoader {

	/**
	 * @param args
	 * @throws ServiceException 
	 * @throws HibernateHelperException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws JSONMapperException, ServiceException, HibernateHelperException {
		
		DeviceRequest deviceRequest = new DeviceRequest();
		deviceRequest.setAffiliateId(new Long(1));
		System.out.println(JSONMapper.toString(deviceRequest));
		System.exit(1);
		AccountUserRequest a = new AccountUserRequest();
		
		AccountUser usr = new AccountUser();
		usr.setUsername("kerry@seabrightsoftware.com");
		usr.setPassword("FNs-JI1EAfPkaxntjd6BWw");
		a.setUser(usr);
		System.out.println(JSONMapper.toString(a));
		System.exit(1);
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("kerry@seabrightsoftware.com");
		loginRequest.setPassword("redsox");
		System.out.println(JSONMapper.toString(loginRequest));
		System.exit(1);
		
		Device device = new Device();
		
		device.setAddress("013110133059");
		device.setAssigned(false);
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("imsi", "724314225346875");
		map1.put("modemEID", "355915031343817");
		map1.put("unitId", "013110133059");
		map1.put("iccId", "8955314229946614941");
		map1.put("keepAlive", "1800");
		map1.put("smsPassword", "redsox");
		map1.put("host", "www.anytraqdevice.com");
		map1.put("port", "3001");
		map1.put("deviceFamily", "PiccoloSTX");
		map1.put("imageVersion", "M2.66T Aug 21 2011");
		map1.put("configVersion", "8.59");
		map1.put("phoneNumber", "6285922599");
		map1.put("simCard", "00000000000BFD6778");
		map1.put("sap", "0003753398");
		device.setAttributes(JSONMapper.toString(map1));
		device.setComments("Brazil");
		device.setDateCreated(new Date(System.currentTimeMillis()));
		device.setDeviceTypeId(2);
		device.setSerial("8955314229946614941");
		System.out.println(JSONMapper.toString(device));		
		System.exit(1);
		
		
		
		
		com.brtracker.shared.utils.SystemResourcesUtil.Midlink controller = SystemResourcesUtil.Midlink.get();
		String controlelrUrl = controller.getUrl() +":"+controller.getPort();
		System.exit(1);
		VehicleHandler m = new VehicleHandler();
	
		System.exit(1);
		String str = ClientCall.get("http://ec2-107-20-247-207.compute-1.amazonaws.com:8080/appapi/vehicle/status?accountId=1");
		Object obj = (Object) JSONMapper.toObject(str, Object.class);
		VehicleStatusResponse resp = (VehicleStatusResponse) JSONMapper.toObject(JSONMapper.toString(obj), VehicleStatusResponse.class);
		System.out.println(resp.getVehicleStatusList().get(0).getAttributes());
		System.exit(1);
		DeviceVehicleMapRequest dvm = new DeviceVehicleMapRequest();
		DeviceVehicleMap map = new DeviceVehicleMap();
		Vehicle vehicle = new Vehicle();
		map.setVehicle(vehicle);
		dvm.setDeviceVehicleMap(map);
		System.out.println(JSONMapper.toString(dvm));
		System.exit(1);
		//Context.getContext();
		String test = "888234.75";
		Float flo = Float.parseFloat(test);
		System.out.println(flo);
		System.exit(1);
		AccountDAOImpl dao = new AccountDAOImpl();
		List<Account> acc = dao.listAccount("affiliatId");
		System.out.println(acc.size());
		System.exit(1);
		DriverSearchRequest request = new DriverSearchRequest();
		request.setSearchFor("marcelo");
		System.out.println(JSONMapper.toString(request));
		System.exit(1);
		Context.getContext();
		
	}

}
