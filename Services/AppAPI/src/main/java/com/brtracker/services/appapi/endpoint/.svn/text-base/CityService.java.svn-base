package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.CityManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("city")
public class CityService {

	private static MyLogger logger = new MyLogger(DeviceService.class);
	private CityManager cityManager = new CityManager();
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String add (String payload)  {
		logger.logInfo("City Add Endpoint: "+payload);
		return cityManager.add(payload);
		
	}
	
	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete (String payload)  {
		logger.logInfo("City Add Endpoint: "+payload);
		return cityManager.delete(payload);
		
	}
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list (@QueryParam("stateId") Long stateId)  {
	logger.logInfo("list cities with state:"+stateId);
	if (stateId == null) return "Invalid StateId";
	return cityManager.list(stateId);
	}
	
}
