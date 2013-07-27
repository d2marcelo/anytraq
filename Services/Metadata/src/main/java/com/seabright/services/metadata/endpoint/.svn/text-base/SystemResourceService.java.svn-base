package com.seabright.services.metadata.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.shared.utils.logging.MyLogger;
import com.seabright.services.metadata.SystemResourceManager;

@Path("system")
public class SystemResourceService {
	
	private static MyLogger logger = new MyLogger(SystemResourceService.class);
	private SystemResourceManager manager = new SystemResourceManager();
	
	@GET
	@Path("resources")
	@Produces(MediaType.APPLICATION_JSON)
	public String list ()  {
		logger.logInfo("list resources service:");
		return manager.list();
	}
	
	@GET
	@Path("upsert")
	@Produces(MediaType.APPLICATION_JSON)
	public String put (@QueryParam("key") String key, @QueryParam("attribute") String att, @QueryParam("value") String value)  {
		logger.logInfo("list resources service:");
		return manager.put(key, att, value);
	}
	
	
	
}
