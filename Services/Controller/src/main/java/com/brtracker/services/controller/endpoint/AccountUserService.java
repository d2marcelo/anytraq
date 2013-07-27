package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.controller.AccountUserManager;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Path("accountUser")
public class AccountUserService  {

		
		/** The logger. */
		private static MyLogger logger = new MyLogger(AffiliateUserService.class);
		
		/** The user manager. */
		private AccountUserManager userHandler = new AccountUserManager();
		
		/**
		 * Adds the.
		 *
		 * @param payload the payload
		 * @return the string
		 */
		@POST
		@Path("add")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String add (String payload)  {
		logger.logInfo("add user service"+payload);
		return userHandler.add(payload);
		}
		
		/**
		 * Delete.
		 *
		 * @param payload the payload
		 * @return the string
		 */
		@POST
		@Path("delete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String delete (String payload)  {
		logger.logInfo("delete user service:"+payload);
		return userHandler.delete(payload);
		}
	
		
		/**
		 * Udpate.
		 *
		 * @param payload the payload
		 * @return the string
		 */
		@POST
		@Path("update")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String udpate (String payload)  {
		logger.logInfo("update user service :"+payload);
		return userHandler.update(payload);
		}
		
		
		
		@POST
		@Path("login")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String login (String payload)  {
		logger.logInfo("login user service :"+payload);
		try {
			return userHandler.login(payload);
		} catch (JSONMapperException e) {
			return e.getMessage();
		}
		}
		
		@POST
		@Path("list")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String listUser (String payload)  {
			logger.logInfo("Lookup listUser");
			return userHandler.list(payload);
		}

		public AccountUserManager getUserHandler() {
			return userHandler;
		}

		public void setUserHandler(AccountUserManager userHandler) {
			this.userHandler = userHandler;
		}

		
		
}


