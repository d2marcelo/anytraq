package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.controller.AccountManager;
import com.brtracker.shared.utils.logging.MyLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountService.
 */
@Path("account")
public class AccountService {

			/** The logger. */
			private static MyLogger logger = new MyLogger(AccountService.class);
			
			/** The account manager. */
			private AccountManager accountManager = new AccountManager();
			
			/**
			 * List.
			 * POST
			 * @return list of type AccountServiceResponse
			 */
			@GET
			@Path("list")
			@Produces(MediaType.APPLICATION_JSON)
			public String list (@QueryParam("affiliateId") String affiliateId)  {
			logger.logInfo("list account service:");
			return accountManager.list(affiliateId);
			}
			
			/**
			 * add.
			 * POST
			 * @param  payload AccountService
			 * @return ServiceResponse
			 */
			@POST
			@Path("add")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.APPLICATION_JSON)
			public String add (String payload)  {
			logger.logInfo("add account service"+payload);
			return accountManager.add(payload);
			}
			
			/**
			 * delete.
			 * POST
			 * @param  payload AccountService
			 * @return ServiceResponse
			 */
			@POST
			@Path("delete")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.APPLICATION_JSON)
			public String delete (String payload)  {
			logger.logInfo("delete account service:"+payload);
			return accountManager.delete(payload);
			}
			
			/**
			 * update.
			 * POST
			 * @param  payload AccountService
			 * @return ServiceResponse
			 */
			@POST
			@Path("update")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.APPLICATION_JSON)
			public String udpate (String payload)  {
			logger.logInfo("update account service :"+payload);
			return accountManager.update(payload);
			}
			
			@GET
			@Path("get")
			@Produces(MediaType.APPLICATION_JSON)
			public String get (@QueryParam("uniqueKey") String uniqueKey)  {
			logger.logInfo("update account service :"+uniqueKey);
			return accountManager.get(uniqueKey);
			}
			
			
			
}
