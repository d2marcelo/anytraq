package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.appapi.CountryManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("country")
public class CountryService {

	private static MyLogger logger = new MyLogger(DeviceService.class);
	private CountryManager countryManager = new CountryManager();
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String add (String payload)  {
		logger.logInfo("Country Add Endpoint: "+payload);
		return countryManager.add(payload);
		
	}
	
	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete (String payload)  {
		logger.logInfo("Country Add Endpoint: "+payload);
		return countryManager.delete(payload);
		
	}
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String update (String payload)  {
		logger.logInfo("Country List Endpoint: "+payload);
		return countryManager.list(payload);
		
	}
	
}
