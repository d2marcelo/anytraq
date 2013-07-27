package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.DeviceMessageManager;
import com.brtracker.shared.utils.logging.MyLogger;


@Path("deviceMessage")
public class DeviceMessageService {

	private static MyLogger logger = new MyLogger(DeviceMessageService.class);
	private DeviceMessageManager manager = new DeviceMessageManager();
	
	@POST
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String list (String payload)  {
		logger.logInfo("list deviceMessage endpoint:"+payload);
		return manager.list(payload);
		
	}
}
