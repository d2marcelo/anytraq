package com.brtracker.services.midlink.processing.command;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brtracker.shared.utils.logging.MyLogger;

public class CommandRetryMonitor {

	private MyLogger logger = new MyLogger(CommandRetryMonitor.class);
	
	public CommandEventDao commandEventDao;
	
	private Map<String, DeviceCommandHandler> commands = new HashMap<String, DeviceCommandHandler>();
	
	public void handleCommandRetry() {

		logger.logInfo("Retrying pending command events ...");
		
		String deviceId = null;
		Long commandId = null;
		try {
			Calendar now = GregorianCalendar.getInstance();
			
			List<CommandEventEntity> pendingCommands = 
				commandEventDao.findPendingRetryCommands(now.getTime());
			
			for (CommandEventEntity command : pendingCommands) {
				
				commandId = command.getId();
				deviceId = command.getDeviceId();
				String deviceModel = command.getDeviceModel();
				
				logger.logInfo("Retrying pending command event for device mode " + deviceModel);
				
				DeviceCommandHandler commandHandler = commands.get(deviceModel);
				
				if (commandHandler != null) {
					logger.logInfo("About to resend command for device model " + deviceModel + " device id " + deviceId);
					commandHandler.resendCommand(command);
				} else {
					logger.logError("Unable to find a command for device model " + deviceModel);
				}

			}
			
			logger.logInfo("Finished retrying pending command events ..." + pendingCommands.size());
			
		} catch (Exception e) {
			logger.logError("Unexpected exception occurred while retrying to send command id/device id" + commandId + "/" + deviceId);
		}
	}

	public void setCommandEventDao(CommandEventDao commandEventDao) {
		this.commandEventDao = commandEventDao;
	}

	public void setCommands(Map<String, DeviceCommandHandler> commands) {
		this.commands = commands;
	}
}
