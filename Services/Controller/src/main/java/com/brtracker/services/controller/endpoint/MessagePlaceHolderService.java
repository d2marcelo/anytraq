package com.brtracker.services.controller.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.controller.MessagePlaceHolderManager;
import com.brtracker.shared.utils.logging.MyLogger;

@Path("messagePlaceHolder")
public class MessagePlaceHolderService extends MessagePlaceHolderManager {

	private MyLogger logger = new MyLogger(MessagePlaceHolderService.class);
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String add (String payload){
		logger.logInfo("add placeholder: "+ payload);
		return super.addPlaceHolder(payload);
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list (@QueryParam("messageKey") String messagekey) {
		logger.logInfo("list placeholder: "+ messagekey);
		return super.listPlaceHolder(messagekey);
		
	}
}
