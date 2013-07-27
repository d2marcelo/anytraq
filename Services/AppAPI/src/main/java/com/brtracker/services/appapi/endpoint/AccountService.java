package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.appapi.AccountManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("account")
public class AccountService {

	private static MyLogger logger = new MyLogger(DeviceService.class);
	private AccountManager accountManager = new AccountManager();
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String add (String payload)  {
		logger.logInfo("Account Add Endpoint: "+payload);
		return accountManager.add(payload);
		
	}
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String update (String payload)  {
		logger.logInfo("Account Update Endpoint: "+payload);
		return accountManager.update(payload);
		
	}
	
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public String get (@QueryParam("uniqueKey") String code)  {
		logger.logInfo("Account Get Endpoint: "+code);
		return accountManager.get(code);
		
	}
	
	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete (String payload)  {
		logger.logInfo("Account Delete Endpoint: "+payload);
		return accountManager.delete(payload);
		
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list (@QueryParam("affiliateId") String affiliateId)  {
		logger.logInfo("Account List Endpoint: ");
		return accountManager.list(affiliateId);
		
	}
	
	
	
}
