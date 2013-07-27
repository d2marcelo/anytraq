package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.appapi.DriverVehicleMapManager;
import com.brtracker.shared.utils.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class DriverVehicleMapService.
 */
@Path("driverVehicleMap")
public class DriverVehicleMapService  {

		
		/** The logger. */
		private static MyLogger logger = new MyLogger(DriverVehicleMapService.class);
		
		/** The driver manager. */
		private DriverVehicleMapManager driverManager = new DriverVehicleMapManager();
		
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
		logger.logInfo("add driverVehicleMap service"+payload);
		return driverManager.add(payload);
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
		logger.logInfo("delete driverVehicleMap service:"+payload);
		return driverManager.delete(payload);
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
		logger.logInfo("update driverVehicleMap service :"+payload);
		return driverManager.update(payload);
		}
		

		@POST
		@Path("list")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String listDriverVehicleMap (String payload)  {
			logger.logInfo("Lookup listStates endpoint called");
			return driverManager.list(payload);
		}
		@POST
		@Path("upsert")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String upsert (String payload)  {
			logger.logInfo("upsert endpoint called");
			return driverManager.upsert(payload);
		}
		
		@POST
		@Path("assets")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String assets (String payload)  {
			logger.logInfo("assets endpoint called");
			return driverManager.assets(payload);
		}
		
		@GET
		@Path("get")
		@Produces(MediaType.APPLICATION_JSON)
		public String getDriver (@QueryParam("vehicleId") String vehicleId)  {
		logger.logInfo("Lookup getDevices endpoint called."+vehicleId);
		return driverManager.get(vehicleId);
		}
		
		
}


