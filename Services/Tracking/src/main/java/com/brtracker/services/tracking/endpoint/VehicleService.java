package com.brtracker.services.tracking.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.tracking.VehicleManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("vehicleStatus")
public class VehicleService {

		/** The logger. */
		private static MyLogger logger = new MyLogger(VehicleService.class);
		
		/** The device manager. */
		private VehicleManager vehicleManager = new VehicleManager();
		
		/**
		 * Adds the.
		 *
		 * @param payload the payload
		 * @return the string
		 */
		@POST
		@Path("upsert")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String update (String payload)  {
		logger.logInfo("vehicle service upsert endpoint called: "+payload);
		return vehicleManager.upsert(payload);
		}
		
		@GET
		@Path("list")
		@Produces(MediaType.APPLICATION_JSON)
		public String listCurrentDeviceInfo (@QueryParam("accountId") Long accountId)  {
		logger.logInfo("Lookup listCurrentDeviceInfo endpoint called."+accountId);
		return vehicleManager.list(accountId);
		}
		
}
