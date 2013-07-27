package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.appapi.GeofenceAlertConfigurationManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("geofenceAlertConfiguration")
public class GeofenceAlertConfigurationService extends GeofenceAlertConfigurationManager {

	private MyLogger logger = new MyLogger(DefaultAlertConfigurationService.class);
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addAlertConfig (String payload)  {
		logger.logInfo("add AlertConfig service"+payload);
		return super.add(payload);
	}
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateAlertConfig (String payload)  {
		logger.logInfo("update alert Config "+payload);
		return super.update(payload);
	}
	
	@POST
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listAlertConfig (String payload)  {
		logger.logInfo("list alert config");
		return super.list(payload);
	}
	
}