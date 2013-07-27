package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.controller.DeviceVehicleMapManager;
import com.brtracker.shared.utils.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class DeviceVehicleMapService.
 */
@Path("deviceVehicleMap")
public class DeviceVehicleMapService  {

		
		/** The logger. */
		private static MyLogger logger = new MyLogger(DeviceVehicleMapService.class);
		
		/** The driver manager. */
		private DeviceVehicleMapManager deviceManager = new DeviceVehicleMapManager();
		
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
		return deviceManager.add(payload);
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
		return deviceManager.delete(payload);
		}
	
		
		@POST
		@Path("list")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String list (String payload)  {
		logger.logInfo("list driverVehicleMap service:"+payload);
		return deviceManager.list(payload);
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
		return deviceManager.update(payload);
		}
		

		@GET
		@Path("get")
		@Produces(MediaType.APPLICATION_JSON)
		public String get (@QueryParam("unitAddress") String unitAddress)  {
		logger.logInfo("Lookup getDevices endpoint called."+unitAddress);
		return deviceManager.get(unitAddress);
		}
		
		@GET
		@Path("getDevice")
		@Produces(MediaType.APPLICATION_JSON)
		public String getDevice (@QueryParam("vehicleId") Long vehicleId)  {
		logger.logInfo("Lookup getDevices endpoint called."+vehicleId);
		return deviceManager.getDevicefromVehicleId(vehicleId);
		}
		
		@GET
		@Path("getDeviceVehicleMap")
		@Produces(MediaType.APPLICATION_JSON)
		public String getDeviceVehicleMap (@QueryParam("uniqueKey") String licensePlate)  {
		logger.logInfo("Lookup getDevices endpoint called."+licensePlate);
		return deviceManager.getDeviceVehicleMap(licensePlate);
		}
		
		@GET
		@Path("clearCache")
		@Produces(MediaType.APPLICATION_JSON)
		public String clearCache ()  {
		logger.logInfo("clearCache endpoint called.");
		deviceManager.clearCache();
		return "done";
		}
		
}


