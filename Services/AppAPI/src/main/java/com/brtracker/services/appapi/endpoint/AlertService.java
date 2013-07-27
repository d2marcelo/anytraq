package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.AlertManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("alert")
public class AlertService extends AlertManager {

	private static MyLogger logger = new MyLogger(AlertService.class);
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addAlert (String payload)  {
		logger.logInfo("Alert Add Endpoint: "+payload);
		return add(payload);
		
	}
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateAlert (String payload)  {
		logger.logInfo("Alert Update Endpoint: "+payload);
		return update(payload);
		
	}
	
	
	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteAlert (String payload)  {
		logger.logInfo("Alert Delete Endpoint: "+payload);
		return delete(payload);
		
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listAlert ()  {
		logger.logInfo("Alert List Endpoint: ");
		return list();
		
	}
}
