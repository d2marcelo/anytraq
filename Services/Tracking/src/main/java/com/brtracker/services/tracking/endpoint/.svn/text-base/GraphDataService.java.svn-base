package com.brtracker.services.tracking.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.tracking.GraphDataManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("graphData")
public class GraphDataService {

	
	/** The logger. */
	private static MyLogger logger = new MyLogger(GraphDataService.class);
	
	/** The device manager. */
	private GraphDataManager graphManager = new GraphDataManager();
	
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
	logger.logInfo("list graph service"+payload);
	return graphManager.list(payload);
	}
}
