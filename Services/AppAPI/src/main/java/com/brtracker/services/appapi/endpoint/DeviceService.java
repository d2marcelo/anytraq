package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.DeviceManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("device")
public class DeviceService {

	private static MyLogger logger = new MyLogger(DeviceService.class);
	private DeviceManager deviceManager = new DeviceManager();
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String add (String payload)  {
		logger.logInfo("Device Add Endpoint: "+payload);
		return deviceManager.add(payload);
		
	}
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String update (String payload)  {
		logger.logInfo("Device Update Endpoint: "+payload);
		return deviceManager.update(payload);
		
	}
	
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public String get (@QueryParam("unitAddress") String unitAddress)  {
		logger.logInfo("Device Get Endpoint: "+unitAddress);
		return deviceManager.get(unitAddress);
		
	}
	
	@GET
	@Path("history")
	@Produces(MediaType.APPLICATION_JSON)
	public String history (@QueryParam("deviceId") Long deviceId, @QueryParam("date") String date)  {
		logger.logInfo("Device Get Endpoint: "+deviceId +" date:"+ date);
		return deviceManager.history(deviceId, date);
		
	}
	
	@GET
	@Path("historyDates")
	@Produces(MediaType.APPLICATION_JSON)
	public String history (@QueryParam("deviceId")Long deviceId)  {
		logger.logInfo("Device Get Endpoint: "+deviceId);
		return deviceManager.historyDates(deviceId);
		
	}
	
	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete (String payload)  {
		logger.logInfo("Device Delete Endpoint: "+payload);
		return deviceManager.delete(payload);
		
	}
	
	@POST
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String list (String payload)  {
		logger.logInfo("Device List Endpoint: "+payload);
		return deviceManager.list(payload);
		
	}
	
	
}
