package com.brtracker.services.midlink.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.brtracker.services.midlink.processing.command.CommandService;
import com.brtracker.shared.payload.controller.lookup.DeviceModel;
import com.brtracker.shared.payload.midlink.CommandRequest;
import com.brtracker.shared.payload.midlink.CommandRisk;
import com.brtracker.shared.payload.midlink.CommandState;
import com.brtracker.shared.payload.midlink.CommandTemplate;
import com.brtracker.shared.payload.midlink.DeviceCommand;
import com.brtracker.shared.payload.midlink.ListCommandTemplate;
import com.brtracker.shared.payload.midlink.ListDeviceCommand;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.wsutils.ServiceResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountService.
 */
@Path("mock/command")
public class MockDeviceCommandWsApi {

	private CommandService commandService;
	
	/** The logger. */
	private static MyLogger logger = new MyLogger(MockDeviceCommandWsApi.class);

	/**
	 * List. POST
	 * 
	 * @return list of type AccountServiceResponse
	 */
	@GET
	@Path("template")
	@Produces(MediaType.APPLICATION_JSON)
	public String template() {
		try {
			logger.logInfo("list account service : " + commandService);
			CommandTemplate ct = new CommandTemplate();
			ct.setCommand("lock");
			ct.setDeviceModel(DeviceModel.WILUS_PICCOLO_STX);
			ct.setId(new Long(1));
			ct.setLabel("OUTPUT 1");
			ct.setRisk(CommandRisk.HIGH);
			Map<String, String> map = new HashMap<String, String>();
			map.put("required", "true");
			map.put("inputType", "dropdownlist");
			map.put("validationType", "text");
			map.put("entry", "on");
			map.put("entry", "off");
			map.put("defaultValue", "0");
			ct.setInputParams(JSONMapper.toString(map));

			List<CommandTemplate> list = new ArrayList<CommandTemplate>();
			list.add(ct);
			ListCommandTemplate lct = new ListCommandTemplate();
			lct.setCommandTemplates(list);

			return JSONMapper.toString(lct);
		} catch (JSONMapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "tbd";
		}
	}

	@POST
	@Path("request")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String request(String payload) {
		try {
			CommandRequest request = (CommandRequest) JSONMapper.toObject(
					payload, CommandRequest.class);
			logger.logInfo("Received request:" + request.getDeviceAddress());
			return ServiceResponse.getServiceResponse(true,
					"Request Submited sucessfully", null);
		} catch (JSONMapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "tbd";
		}

	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String command(@QueryParam("accountUserId") Long accountUserId,
			@QueryParam("affiliateUserId") Long affiliateUserId) {
		try {
			logger.logInfo("list account service:");
			ListDeviceCommand respDeviceCommand = new ListDeviceCommand();
			List<DeviceCommand> list = new ArrayList<DeviceCommand>();
			DeviceCommand deviceCommand = new DeviceCommand();
			deviceCommand.setAccountUserId(new Long(5));
			deviceCommand.setAffiliateUserId(new Long(1));
			deviceCommand.setCommand("lock");
			deviceCommand.setCreatedOn(new Long("12313123123"));
			deviceCommand.setDeviceAdress("234243234234");
			deviceCommand.setDeviceModel(DeviceModel.WILUS_PICCOLO_STX);
			deviceCommand.setId(new Long(1));
			deviceCommand.setRetriesDelivery(3);
			deviceCommand.setRetriesVerify(5);
			deviceCommand.setRisk(CommandRisk.HIGH);
			deviceCommand.setScheduledFor(new Long("2342423423"));
			deviceCommand.setSecurityCode("Cal2f0rn1@");
			deviceCommand.setState(CommandState.APPROVAL_PENDING);
			deviceCommand.setUpdatedOn(new Long("234234234"));
			list.add(deviceCommand);
			respDeviceCommand.setCommands(list);
			return JSONMapper.toString(respDeviceCommand);
		} catch (JSONMapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "tbd";
		}
	}

	@GET
	@Path("approve")
	@Produces(MediaType.APPLICATION_JSON)
	public String approve(@QueryParam("affiliateUserId") Long affiliateUserId,
			@QueryParam("commandId") Long commandId) {
		try {
			return ServiceResponse.getServiceResponse(true,
					"Request Submited sucessfully", null);
		} catch (JSONMapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "tbd";
		}

	}

	@GET
	@Path("cancel")
	@Produces(MediaType.APPLICATION_JSON)
	public String cancel(@QueryParam("affiliateUserId") Long affiliateUserId,
			@QueryParam("commandId") Long commandId) {
		try {
			return ServiceResponse.getServiceResponse(true,
					"Request Submited sucessfully", null);
		} catch (JSONMapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "tbd";
		}

	}

	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}

}
