package com.brtracker.services.midlink.processing.command;

import java.util.HashMap;
import java.util.Map;

import com.brtracker.shared.payload.midlink.CommandRequest;
import com.brtracker.shared.utils.logging.MyLogger;

public class CommandDispatcherImpl implements CommandDispatcher {

	private MyLogger logger = new MyLogger(CommandDispatcherImpl.class);
	
	private Map<String, DeviceCommandHandler> commands = new HashMap<String, DeviceCommandHandler>();
	
	private CommandTemplateDao commandTemplateDao;
	
	@Override
	public void dispatchCommand(CommandRequest request) throws Exception {
		
		CommandTemplateEntity template = commandTemplateDao
			.getCommandTemplate(request.getCommandTemplateId());

		String deviceModel = template.getDeviceModel();
		
		DeviceCommandHandler command = commands.get(deviceModel);
		if (command != null) {
			logger.logInfo("About to call command for device model " + deviceModel);
			command.sendCommand(template, request);
		} else {
			logger.logError("Unable to find a command for device " + deviceModel);
		}
	}

	public Map<String, DeviceCommandHandler> getCommands() {
		return commands;
	}

	public void setCommands(Map<String, DeviceCommandHandler> commands) {
		this.commands = commands;
	}

	public void setCommandTemplateDao(CommandTemplateDao commandTemplateDao) {
		this.commandTemplateDao = commandTemplateDao;
	}

}
