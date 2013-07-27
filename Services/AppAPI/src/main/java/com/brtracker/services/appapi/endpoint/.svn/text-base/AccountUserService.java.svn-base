package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.AccountUserManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("accountUser")
public class AccountUserService {

	private static MyLogger logger = new MyLogger(AccountUserService.class);
	private AccountUserManager userManager = new AccountUserManager();
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String add (String payload)  {
		logger.logInfo("Device Add Endpoint: "+payload);
		return userManager.add(payload);
		
	}
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login (String payload)  {
		logger.logInfo("Login Add Endpoint: "+payload);
		return userManager.login(payload);
		
	}
	
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String update (String payload)  {
		logger.logInfo("Device Update Endpoint: "+payload);
		return userManager.update(payload);
		
	}
	
	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete (String payload)  {
		logger.logInfo("Device Delete Endpoint: "+payload);
		return userManager.delete(payload);
		
	}
	
	@POST
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String list (String payload)  {
		logger.logInfo("Device List Endpoint: "+payload);
		return userManager.list(payload);
		
	}
	
	
}
