package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.appapi.DefaultAlertConfigurationManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("defaultAlertConfiguration")
public class DefaultAlertConfigurationService extends DefaultAlertConfigurationManager {

	private MyLogger logger = new MyLogger(DefaultAlertConfigurationService.class);
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addAlertConfig (String payload)  {
		logger.logInfo("add AlertConfig service"+payload);
		return add(payload);
	}
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateAlertConfig (String payload)  {
		logger.logInfo("update alert Config "+payload);
		return update(payload);
	}
	
	@POST
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAlertConfig (String payload)  {
		logger.logInfo("update alert Config "+payload);
		return delete(payload);
	}
	
	@POST
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listAlertConfig (String payload)  {
		logger.logInfo("list alert config");
		return list(payload);
	}
	
}
