package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.appapi.DriverBehaviorManager;
import com.brtracker.shared.utils.logging.MyLogger;


@Path("driverBehavior")
public class DriverBehaviorService {

	private static MyLogger logger = new MyLogger(DriverBehaviorService.class);
	private DriverBehaviorManager driverBehaviorManager = new DriverBehaviorManager();
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list (@QueryParam("driverId") Long driverId, @QueryParam("duration") int duration)  {
		logger.logInfo("list driver behavior service");
		return driverBehaviorManager.list(driverId, duration);
		
	}
}
