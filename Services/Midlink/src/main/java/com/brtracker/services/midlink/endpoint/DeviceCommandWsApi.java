package com.brtracker.services.midlink.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.brtracker.services.midlink.processing.command.CommandService;
import com.brtracker.shared.payload.midlink.CommandRequest;
import com.brtracker.shared.payload.midlink.ListCommandIoConfig;
import com.brtracker.shared.payload.midlink.ListCommandTemplate;
import com.brtracker.shared.payload.midlink.ListDeviceCommand;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.wsutils.ServiceResponse;

/**
 * The Class AccountService.
 */
@Path("command")
public class DeviceCommandWsApi {

	private CommandService commandService;
	
	/** The logger. */
	private static MyLogger logger = new MyLogger(DeviceCommandWsApi.class);

	@GET
	@Path("io-config/{ioKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listCommandIoConfigsByioKey(@PathParam("ioKey") String ioKey) {
		
		String apiResponse = "";
		try {
			logger.logInfo("List command io configs by io key " + ioKey);
			ListCommandIoConfig commandIoConfigs = commandService.listCommandIoConfigByIoKey(ioKey);
			
			apiResponse = JSONMapper.toString(commandIoConfigs);
			
			logger.logInfo("List command io configs by io key" + apiResponse);
			
		} catch (Exception e) {
			apiResponse = handleExceptionGetResponse(e, " while processing list io configs");
		}
		return apiResponse;
	}

	@GET
	@Path("io-config")
	@Produces(MediaType.APPLICATION_JSON)
	public String listCommandIoConfigs() {
		
		String apiResponse = "";
		try {
			logger.logInfo("List command io configs ");
			ListCommandIoConfig commandIoConfigs = commandService.listCommandIoConfig();
			
			apiResponse = JSONMapper.toString(commandIoConfigs);
			logger.logInfo("List command io configs " + apiResponse);
			
		} catch (Exception e) {
			apiResponse = handleExceptionGetResponse(e, " while processing list io configs");
		}
		return apiResponse;
	}

	/**
	 * List. POST
	 * 
	 * @return list of type AccountServiceResponse
	 */
	@GET
	@Path("template")
	@Produces(MediaType.APPLICATION_JSON)
	public String template() {
		
		String apiResponse = "";
		try {
			
			ListCommandTemplate templates = commandService.listCommanTemplates();
			
			apiResponse = JSONMapper.toString(templates);
			
		} catch (Exception e) {
			apiResponse = handleExceptionGetResponse(e, " while processing list templates");
		}
		return apiResponse;
	}
	
	/**
	 * List. POST
	 * 
	 * @return list of type AccountServiceResponse
	 */
	@GET
	@Path("template/{nameKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public String templateByNameKey(@PathParam("nameKey") String nameKey) {
		
		String apiResponse = "";
		try {
			
			ListCommandTemplate templates = commandService.listCommandTemplateByNameKey(nameKey);
			
			apiResponse = JSONMapper.toString(templates);
			
		} catch (Exception e) {
			apiResponse = handleExceptionGetResponse(e, " while processing list templates");
		}
		return apiResponse;
	}

	@POST
	@Path("request")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String request(String payload) {
		
		String apiResponse = "";
		try {
			
			CommandRequest request = (CommandRequest) JSONMapper.toObject(
					payload, CommandRequest.class);
			
			logger.logInfo("Received request:" + request.getDeviceAddress());
			
			return commandService.processCommand(request);
			
		} catch (Exception e) {
			apiResponse = handleExceptionGetResponse(e, " while processing process request ");
		}
		return apiResponse;

	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String command(@QueryParam("accountUserId") Long accountUserId,
			@QueryParam("affiliateUserId") Long affiliateUserId) {
		
		String apiResponse = "";
		try {
			
			logger.logInfo("List commads :" + accountUserId + " " + affiliateUserId);

			ListDeviceCommand commands = commandService.listCommands(accountUserId, affiliateUserId);
			
			apiResponse = JSONMapper.toString(commands);
			
		} catch (Exception e) {
			apiResponse = handleExceptionGetResponse(e, " while processing list commands");
		}
		return apiResponse;

	}

	@GET
	@Path("approve")
	@Produces(MediaType.APPLICATION_JSON)
	public String approve(@QueryParam("affiliateUserId") Long affiliateUserId,
			@QueryParam("commandId") Long commandId) {
		
		String apiResponse = "";
		try {
			
			logger.logInfo("Approve command " + commandId);

			apiResponse = commandService.approveCommand(affiliateUserId, commandId);
			
		} catch (Exception e) {
			apiResponse = handleExceptionGetResponse(e, " while processing approve command ");
		}
		return apiResponse;
	}

	@GET
	@Path("cancel")
	@Produces(MediaType.APPLICATION_JSON)
	public String cancel(@QueryParam("affiliateUserId") Long affiliateUserId,
			@QueryParam("commandId") Long commandId) {
		String response = "";
		try {
			
			logger.logInfo("Cancel command " + commandId);

			response = commandService.cancelCommand(affiliateUserId, commandId);
			
		} catch (Exception e) {
			response = handleExceptionGetResponse(e, " while processing cancel request");
		}
		return response;

	}

	private String handleExceptionGetResponse(Exception e, String message) {
		
		logger.logError("Unexpected exception: " + message, e);
		try {
			
			return ServiceResponse.getServiceResponse(
					true, "Unpexted exception: " + message, e.getMessage());
			
		} catch (JSONMapperException e1) {
			e1.printStackTrace();
		}
		return "Server Error";
	}

	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}

}
