package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.controller.DriverHandler;
import com.brtracker.shared.utils.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class DriverService.
 */
@Path("driver")
public class DriverService  {

		
		/** The logger. */
		private static MyLogger logger = new MyLogger(DriverService.class);
		
		/** The driver manager. */
		private DriverHandler driverHandler = new DriverHandler();
		
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
		logger.logInfo("add driver service"+payload);
		return driverHandler.add(payload);
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
		logger.logInfo("delete driver service:"+payload);
		return driverHandler.delete(payload);
		}
	
		@GET
		@Path("clearCache")
		@Produces(MediaType.APPLICATION_JSON)
		public String clearCache ()  {
		logger.logInfo("clearCache endpoint called.");
		driverHandler.clearCache();
		return "done";
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
		logger.logInfo("update update service :"+payload);
		return driverHandler.update(payload);
		}
		
		@POST
		@Path("search")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String listDriverSearch (String payload)  {
		logger.logInfo("Lookup driver search endpoint called."+payload);
		return driverHandler.search(payload);
		}
		
		@POST
		@Path("list")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String list (String payload)  {
			logger.logInfo("Lookup listDriver endpoint called");
			return driverHandler.list(payload);
		}
		
		@GET
		@Path("get")
		@Produces(MediaType.APPLICATION_JSON)
		public String getDevice (@QueryParam("uniqueKey") String uniqueKey)  {
		logger.logInfo("Lookup getDriver endpoint called."+uniqueKey);
		return driverHandler.get(uniqueKey);
		}

		public DriverHandler getDriverHandler() {
			return driverHandler;
		}

		public void setDriverHandler(DriverHandler driverHandler) {
			this.driverHandler = driverHandler;
		}

		
		

}


