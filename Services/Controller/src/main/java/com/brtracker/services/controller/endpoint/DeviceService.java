package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.controller.DeviceManager;
import com.brtracker.shared.utils.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class DeviceService.
 */
@Path("device")
public class DeviceService {

		/** The logger. */
		private static MyLogger logger = new MyLogger(DeviceService.class);
		
		/** The device manager. */
		private DeviceManager deviceManager = new DeviceManager();
		
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
		logger.logInfo("add device service"+payload);
		return deviceManager.add(payload);
		}
		
		@GET
		@Path("clearCache")
		@Produces(MediaType.APPLICATION_JSON)
		public String clearCache ()  {
		logger.logInfo("clearCache endpoint called.");
		deviceManager.clearCache();
		return "done";
		}
		
		/**
		 * Delete.
		 *
		 * @param payload the payload
		 * @return the string
		 */
		@POST
		@Path("delete")
		@Produces(MediaType.APPLICATION_JSON)
		public String delete (String payload)  {
		logger.logInfo("delete device service with address:"+payload);
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
		public String udpate (String payload)  {
		logger.logInfo("update device service :"+payload);
		return deviceManager.update(payload);
		}
		
		@POST
		@Path("list")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String listDevice (String payload)  {
		logger.logInfo("Lookup listDevices endpoint called."+payload);
		return deviceManager.list(payload);
		}
		
		@GET
		@Path("get")
		@Produces(MediaType.APPLICATION_JSON)
		public String getDevice (@QueryParam("unitAddress") String unitAddress)  {
		logger.logInfo("Lookup getDevices endpoint called."+unitAddress);
		return deviceManager.get(unitAddress);
		}
		
		

}


