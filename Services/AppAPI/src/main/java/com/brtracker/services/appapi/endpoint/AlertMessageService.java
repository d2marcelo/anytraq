package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.appapi.AlertMessageManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("alertMessage")
public class AlertMessageService extends AlertMessageManager {

private static MyLogger logger = new MyLogger(AlertService.class);
	
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addAlert (String payload)  {
		logger.logInfo("Alert Add Endpoint: "+payload);
		return update(payload);
		
	}

	@POST
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String listAlert (String payload)  {
		logger.logInfo("Alert List Endpoint: "+payload);
		return list(payload);
		
	}
	
	@POST
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAlert (String payload)  {
	logger.logInfo("delete graph service"+payload);
	return delete(payload);
	}
}
