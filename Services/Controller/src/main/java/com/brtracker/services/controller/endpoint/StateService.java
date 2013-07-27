package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.controller.StateManager;
import com.brtracker.shared.utils.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class StateService.
 */
@Path("state")
public class StateService {

			/** The logger. */
			private static MyLogger logger = new MyLogger(StateService.class);
			
			/** The state manager. */
			private StateManager stateManager = new StateManager();
			
			/**
			 * List.
			 *
			 * @param countryId the country id
			 * @return the string
			 */
			@GET
			@Path("list")
			@Produces(MediaType.APPLICATION_JSON)
			public String list (@QueryParam("countryId") Long countryId)  {
			logger.logInfo("list state with country:"+countryId);
			if (countryId == null) return "Invalid CountryId";
			return stateManager.list(countryId);
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
			logger.logInfo("add state service"+payload);
			return stateManager.add(payload);
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
			logger.logInfo("delete state service:"+payload);
			return stateManager.delete(payload);
			}
			
		
			
}