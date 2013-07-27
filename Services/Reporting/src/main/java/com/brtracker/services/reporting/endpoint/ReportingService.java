package com.brtracker.services.reporting.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.reporting.ReportingManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("reportingService")
public class ReportingService extends ReportingManager {

	
	/** The logger. */
	private static MyLogger logger = new MyLogger(ReportingService.class);
	
	/**
	 * Adds the.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	@POST
	@Path("list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String list (String payload)  {
	logger.logInfo("list report service"+payload);
		return listReport(payload);
	}
	
	@GET
	@Path("process")
	@Produces(MediaType.APPLICATION_JSON)
	public String process ()  {
	logger.logInfo("process report endpoint service");
		return process();
	}
}
