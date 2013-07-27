package com.brtracker.services.tracking.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.tracking.AlertMessageManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("alertMessage")
public class AlertMessageService {
	
	private MyLogger logger = new MyLogger(AlertMessageService.class);
	private AlertMessageManager manager = new AlertMessageManager();
	
	@POST
	@Path("list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String list (String payload)  {
	logger.logInfo("list graph service"+payload);
	return manager.list(payload);
	}
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update (String payload)  {
	logger.logInfo("update graph service"+payload);
	return manager.update(payload);
	}
	
	@POST
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delete (String payload)  {
	logger.logInfo("delete graph service"+payload);
	return manager.delete(payload);
	}

}
