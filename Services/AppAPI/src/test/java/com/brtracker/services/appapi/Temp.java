package com.brtracker.services.appapi;

import java.util.List;
import java.util.Map;

import com.brtracker.shared.payload.controller.DeviceRequest;
import com.brtracker.shared.payload.controller.DeviceResponse;
import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.payload.tracking.VehicleStatusResponse;
import com.brtracker.shared.payload.tracking.data.VehicleStatus;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;

public class Temp {

	/**
	 * @param args
	 * @throws ServiceException 
	 * @throws JSONMapperException 
	 */
	public static void main(String[] args) throws ServiceException, JSONMapperException {
		String vehicleList = ClientCall.get("http://localhost:8080/appapi/vehicle/status/?accountId=1");
		VehicleStatusResponse vehicleStatusResp = (VehicleStatusResponse) JSONMapper.toObject(vehicleList, VehicleStatusResponse.class);
		List<VehicleStatus> stats = vehicleStatusResp.getVehicleStatusList();
		for (VehicleStatus s : stats){
			Map<String,String> map = (Map<String, String>) JSONMapper.toObject(s.getAttributes(), Map.class);
			map.put("driverPhoto","default.png");
			DeviceMessageAttribute m = new DeviceMessageAttribute();
			m.setAttributes(map);
			
			DeviceRequest d = new DeviceRequest();
			Device devi = new Device();
			devi.setId(s.getDeviceId());
			devi.setAssigned(true);
			d.setDevice(devi);
			String out = ClientCall.post("http://localhost:8080/accountws/device/list", JSONMapper.toString(d));
			DeviceResponse rep = (DeviceResponse) JSONMapper.toObject(out, DeviceResponse.class);
			m.setUnitAddress(rep.getDeviceList().get(0).getAddress());
			
			String a = ClientCall.post("http://localhost:8080/tracking/vehicleStatus/upsert", JSONMapper.toString(m));
			System.out.println(a);
		
		}
	}

}
