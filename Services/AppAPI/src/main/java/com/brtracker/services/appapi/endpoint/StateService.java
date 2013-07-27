package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.StateManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("state")
public class StateService {

	private static MyLogger logger = new MyLogger(DeviceService.class);
	private StateManager stateManager = new StateManager();
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String add (String payload)  {
		logger.logInfo("State Add Endpoint: "+payload);
		return stateManager.add(payload);
		
	}
	
	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete (String payload)  {
		logger.logInfo("State Add Endpoint: "+payload);
		return stateManager.delete(payload);
		
	}
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list (@QueryParam("countryId") Long countryId)  {
	logger.logInfo("list state with country:"+countryId);
	if (countryId == null) return "Invalid CountryId";
	return stateManager.list(countryId);
	}
	
	
}
