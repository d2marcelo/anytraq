package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.DriverManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("driver")
public class DriverService {

	private static MyLogger logger = new MyLogger(DriverService.class);
	private DriverManager driverManager = new DriverManager();
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String add (String payload)  {
		logger.logInfo("Driver Add Endpoint: "+payload);
		return driverManager.add(payload);
		
	}
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String update (String payload)  {
		logger.logInfo("Driver Update Endpoint: "+payload);
		return driverManager.update(payload);
		
	}
	
	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete (String payload)  {
		logger.logInfo("Driver Delete Endpoint: "+payload);
		return driverManager.delete(payload);
		
	}
	
	@POST
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String list (String payload)  {
		logger.logInfo("Driver List Endpoint: "+payload);
		return driverManager.list(payload);
		
	}
	
	@POST 
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public String search (String payload){
		logger.logInfo("Driver Search Endpoint: "+payload);
		return driverManager.search(payload);
	}
	
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDevice (@QueryParam("uniqueKey") String uniqueKey)  {
	logger.logInfo("Lookup getDevices endpoint called."+uniqueKey);
	return driverManager.get(uniqueKey);
	}
	
}
