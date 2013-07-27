package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.VehicleManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("vehicle")
public class VehicleService {

	private static MyLogger logger = new MyLogger(DeviceMessageService.class);
	private VehicleManager vehicleManager = new VehicleManager();
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String add (String payload)  {
		logger.logInfo("Vehicle Add Endpoint: "+payload);
		return vehicleManager.add(payload);
		
	}
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String update (String payload)  {
		logger.logInfo("Vehicle Update Endpoint: "+payload);
		return vehicleManager.update(payload);
		
	}
	
	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete (String payload)  {
		logger.logInfo("Vehicle Delete Endpoint: "+payload);
		return vehicleManager.delete(payload);
		
	}
	
	@POST
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String list (String payload)  {
		logger.logInfo("Vehicle List Endpoint: "+payload);
		return vehicleManager.list(payload);
		
	}
	
	@POST
	@Path("addVehicleDeviceMap")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addVDMap (String payload)  {
	logger.logInfo("add vehicle service"+payload);
	return vehicleManager.addVehicleDeviceMap(payload);
	}
	
	@POST
	@Path("updateVehicleDeviceMap")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateVDMap (String payload)  {
	logger.logInfo("add vehicle service"+payload);
	return vehicleManager.updateVehicleDeviceMap(payload);
	}
	
	@GET
	@Path("status")
	@Produces(MediaType.APPLICATION_JSON)
	public String status (@QueryParam("accountId") Long accountId)  {
		logger.logInfo("Vehicle Status Endpoint: "+accountId);
		return vehicleManager.status(accountId);
		
	}
	
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDevice (@QueryParam("uniqueKey") String uniqueKey)  {
	logger.logInfo("Lookup getDevices endpoint called."+uniqueKey);
	return vehicleManager.get(uniqueKey);
	}
	
	
	
}
