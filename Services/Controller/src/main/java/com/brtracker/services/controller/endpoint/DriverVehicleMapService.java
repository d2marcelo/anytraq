package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.controller.DriverVehicleMapManager;
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
		@GET
		@Path("clearCache")
		@Produces(MediaType.APPLICATION_JSON)
		public String clearCache ()  {
		logger.logInfo("clearCache endpoint called.");
		driverManager.clearCache();
		return "done";
		}
		
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
		@Path("upsert")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String upsert (String payload)  {
		logger.logInfo("upsert driverVehicleMap service :"+payload);
		return driverManager.upsert(payload);
		}
		

		@POST
		@Path("list")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String listDriverVehicleMap (String payload)  {
			logger.logInfo("Lookup listStates endpoint called");
			return driverManager.list(payload);
		}
		
		@GET
		@Path("get")
		@Produces(MediaType.APPLICATION_JSON)
		public String getDriver (@QueryParam("vehicleId") Long vehicleID)  {
		logger.logInfo("Lookup getDriver endpoint called."+vehicleID);
		return driverManager.get(vehicleID);
		}
		
		@POST
		@Path("assets")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String getAssets (String payload)  {
		logger.logInfo("Lookup assets endpoint called."+payload);
		return driverManager.asset(payload);
		}
		
		@GET
		@Path("getVehicle")
		@Produces(MediaType.APPLICATION_JSON)
		public String getVehicle (@QueryParam("driverId") Long driverId)  {
		logger.logInfo("Lookup getVehicle endpoint called."+driverId);
		return driverManager.getVehicleFromDriverId(driverId);
		}
		
		
}


