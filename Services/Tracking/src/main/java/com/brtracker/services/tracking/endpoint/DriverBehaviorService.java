package com.brtracker.services.tracking.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.tracking.DriverBehaviorManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("driverBehavior")
public class DriverBehaviorService {

	/** The logger. */
	private static MyLogger logger = new MyLogger(DriverBehaviorService.class);
	
	/** The device manager. */
	private DriverBehaviorManager driverBehaviorManager = new DriverBehaviorManager();
	
	/**
	 * Adds the.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list (@QueryParam("driverId") Long driverId, @QueryParam("duration") int duration)  {
		logger.logInfo("list driver behavior service");
		return driverBehaviorManager.list(driverId, duration);
	}
}
