package com.brtracker.services.tracking.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.tracking.DeviceHistoryManager;
import com.brtracker.shared.utils.cache.CacheClient;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;


@Path("deviceHistory")
public class DeviceHistoryService {

	
	/** The logger. */
	private MyLogger logger = new MyLogger(DeviceMessageService.class);
	private DeviceHistoryManager manager = new DeviceHistoryManager();
	private CacheClient cacheClient = CacheClient.get();
	
	
	@GET
	@Path("clearCache")
	@Produces(MediaType.APPLICATION_JSON)
	public String clearCache ()  {
	logger.logInfo("clearCache endpoint called.");
	manager.clearCache();
	return "done";
	}
	
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public String get (@QueryParam("deviceId") Long deviceId, @QueryParam("date") String date)  {
		logger.logInfo("Get device history");
		return  manager.get(deviceId, date);
	}
	
	@GET
	@Path("dates")
	@Produces(MediaType.APPLICATION_JSON)
	public String get (@QueryParam("deviceId") Long deviceId)  {
		logger.logInfo("Get device history");
		return  manager.getDates(deviceId);
	}
}
