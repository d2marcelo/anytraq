package com.brtracker.services.appapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.appapi.DeviceVehicleMapManager;
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
		public String update (String payload)  {
		logger.logInfo("update driverVehicleMap service :"+payload);
		return deviceManager.update(payload);
		}
		
		@POST
		@Path("list")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String list (String payload)  {
		logger.logInfo("list driverVehicleMap service :"+payload);
		return deviceManager.list(payload);
		}
		

		@GET
		@Path("get")
		@Produces(MediaType.APPLICATION_JSON)
		public String getDevice (@QueryParam("uniqueKey") String unitAddress)  {
		logger.logInfo("Lookup getDevices endpoint called."+unitAddress);
		return deviceManager.get(unitAddress);
		}
		
}


