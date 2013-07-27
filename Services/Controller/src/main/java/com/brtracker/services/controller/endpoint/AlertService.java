package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.controller.AlertManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("alert")
public class AlertService {

	private MyLogger logger = new MyLogger(AlertService.class);
	private AlertManager manager = new AlertManager();
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String add (String payload)  {
		logger.logInfo("add city service"+payload);
		return manager.add(payload);
	}
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update (String payload)  {
		logger.logInfo("update city service"+payload);
		return manager.update(payload);
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listAlertType ()  {
		logger.logInfo("list alert type");
		return manager.list();
	}
	
}
