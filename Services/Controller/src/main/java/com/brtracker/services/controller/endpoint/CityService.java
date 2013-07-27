package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.controller.CityManager;
import com.brtracker.shared.utils.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class CityService.
 */
@Path("city")
public class CityService {

			/** The logger. */
			private static MyLogger logger = new MyLogger(CityService.class);
			
			/** The city manager. */
			private CityManager cityManager = new CityManager();
			
			/**
			 * List.
			 *
			 * @param stateId the state id
			 * @return the string
			 */
			@GET
			@Path("list")
			@Produces(MediaType.APPLICATION_JSON)
			public String list (@QueryParam("stateId") Long stateId)  {
			logger.logInfo("list cities with state:"+stateId);
			if (stateId == null) return "Invalid StateId";
			return cityManager.list(stateId);
			}
			
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
			logger.logInfo("add city service"+payload);
			return cityManager.add(payload);
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
			logger.logInfo("delete city service:"+payload);
			return cityManager.delete(payload);
			}
			
		
			
}