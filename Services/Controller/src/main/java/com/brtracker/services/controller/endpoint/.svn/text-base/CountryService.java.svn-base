package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.controller.CountryManager;
import com.brtracker.shared.utils.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class CountryService.
 */
@Path("country")
public class CountryService {

			/** The logger. */
			private static MyLogger logger = new MyLogger(CountryService.class);
			
			/** The country manager. */
			private CountryManager countryManager = new CountryManager();
			
			/**
			 * List.
			 *
			 * @return the string
			 */
			@GET
			@Path("list")
			@Produces(MediaType.APPLICATION_JSON)
			public String list ()  {
			logger.logInfo("list countries with state:");
			return countryManager.list();
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
			logger.logInfo("add country service"+payload);
			return countryManager.add(payload);
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
			logger.logInfo("delete country service:"+payload);
			return countryManager.delete(payload);
			}
			
		
			
}