package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.controller.VehicleHandler;
import com.brtracker.shared.utils.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class VehicleService.
 */
@Path("vehicle")
public class VehicleService  {

		
		/** The logger. */
		private static MyLogger logger = new MyLogger(VehicleService.class);
		/** The vehicle manager. */
		private VehicleHandler vehicleHandler = new VehicleHandler();
		
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
		logger.logInfo("add vehicle service"+payload);
		return vehicleHandler.add(payload);
		}
		
		@POST
		@Path("addVehicleDeviceMap")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String addVDMap (String payload)  {
		logger.logInfo("add vehicle service"+payload);
		return vehicleHandler.addVehicleDeviceMap(payload);
		}
		
		@POST
		@Path("updateVehicleDeviceMap")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String updateVDMap (String payload)  {
		logger.logInfo("add vehicle service"+payload);
		return vehicleHandler.updateVehicleDeviceMap(payload);
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
		logger.logInfo("delete vehicle service:"+payload);
		return vehicleHandler.delete(payload);
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
		return vehicleHandler.update(payload);
		}

		@GET
		@Path("get")
		@Produces(MediaType.APPLICATION_JSON)
		public String getVehicle (@QueryParam("uniqueKey") String uniqueKey)  {
		logger.logInfo("Lookup getDevices endpoint called."+uniqueKey);
		return vehicleHandler.get(uniqueKey);
		}
		
		@GET
		@Path("clearCache")
		@Produces(MediaType.APPLICATION_JSON)
		public String clearCache ()  {
		logger.logInfo("clearCache endpoint called.");
		vehicleHandler.clearCache();
		return "done";
		}
		
		@POST
		@Path("list")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String listVehicle (String payload)  {
			logger.logInfo("Lookup listVehicle");
			return vehicleHandler.list(payload);
		}

		public VehicleHandler getVehicleHandler() {
			return vehicleHandler;
		}

		public void setVehicleHandler(VehicleHandler vehicleHandler) {
			this.vehicleHandler = vehicleHandler;
		}

		
		
}


