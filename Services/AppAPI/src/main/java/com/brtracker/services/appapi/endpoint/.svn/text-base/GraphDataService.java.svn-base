package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.GraphDataManager;
import com.brtracker.shared.utils.logging.MyLogger;


@Path("graphData")
public class GraphDataService {

	private static MyLogger logger = new MyLogger(GraphDataService.class);
	private GraphDataManager graphDataManager = new GraphDataManager();
	
	@POST
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String list (String payload)  {
		logger.logInfo("list graph data endpoint:"+payload);
		return graphDataManager.list(payload);
		
	}
}
