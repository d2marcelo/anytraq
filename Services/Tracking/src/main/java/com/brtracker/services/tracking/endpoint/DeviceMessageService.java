package com.brtracker.services.tracking.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.tracking.DeviceMessageManager;
import com.brtracker.shared.utils.logging.MyLogger;


@Path("deviceMessage")
public class DeviceMessageService {

	
	/** The logger. */
	private MyLogger logger = new MyLogger(DeviceMessageService.class);
	private DeviceMessageManager manager = new DeviceMessageManager();
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String add (String payload)  {
	logger.logInfo("add device message. "+payload);
	return manager.add(payload);
	}
}
