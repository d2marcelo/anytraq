package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.controller.AffiliateManager;
import com.brtracker.shared.utils.logging.MyLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountService.
 */
@Path("affiliate")
public class AffiliateService {

			/** The logger. */
			private static MyLogger logger = new MyLogger(AccountService.class);
			
			/** The account manager. */
			private AffiliateManager affiliateManager = new AffiliateManager();
			
			/**
			 * List.
			 * POST
			 * @return list of type AccountServiceResponse
			 */
			@GET
			@Path("list")
			@Produces(MediaType.APPLICATION_JSON)
			public String list ()  {
			logger.logInfo("list affiliate service:");
			return affiliateManager.list();
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
			logger.logInfo("add affiliate service"+payload);
			return affiliateManager.add(payload);
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
			logger.logInfo("delete affiliate service:"+payload);
			return affiliateManager.delete(payload);
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
			logger.logInfo("update affiliate service :"+payload);
			return affiliateManager.update(payload);
			}
			
			@GET
			@Path("get")
			@Produces(MediaType.APPLICATION_JSON)
			public String get (@QueryParam("uniqueKey") String code)  {
			logger.logInfo("update affiliate service :"+code);
			return affiliateManager.get(code);
			}
			
			
			
}
