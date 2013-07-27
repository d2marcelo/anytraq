package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.TripSummaryManager;
import com.brtracker.shared.utils.logging.MyLogger;


@Path("tripSummary")
public class TripSummaryService {

	private static MyLogger logger = new MyLogger(TripSummaryService.class);
	private TripSummaryManager tripSummaryManager = new TripSummaryManager();
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list (@QueryParam("driverId") Long driverId, @QueryParam("duration") int duration)  {
		logger.logInfo("list driver behavior service");
		return tripSummaryManager.list(driverId, duration);
	}
}
