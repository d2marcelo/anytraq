package com.brtracker.services.midlink.processing.application;

import java.util.HashMap;
import java.util.Map;

import com.brtracker.services.midlink.processing.BaseMessageAction;
import com.brtracker.services.midlink.processing.command.CommandEventDao;
import com.brtracker.services.midlink.processing.command.CommandEventEntity;
import com.brtracker.services.midlink.processing.command.DeviceCommandHandler;
import com.brtracker.shared.payload.midlink.CommandState;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class IOServiceConfirmAction extends BaseMessageAction{

	private CommandEventDao commandEventDao;
	
	private Map<String, DeviceCommandHandler> commands = new HashMap<String, DeviceCommandHandler>();
	
	@Override
	public ActionName getName() {
		return ActionName.IoServiceConfirmAction;
	}

	@Override
	public void execute(TrackingMessage trackingMessage) {
		
		Integer messageSeq = trackingMessage.getProperty(Integer.class, "appMessage.ioService.sequence");
		
		try {
			
			logger.logInfo("Checking message sequence " + messageSeq);
			
			CommandEventEntity commandEvent = commandEventDao.findByMessageSeq(messageSeq);
			
			if (commandEvent != null) {
				
				String deviceModel = commandEvent.getDeviceModel();
				
				DeviceCommandHandler command = commands.get(deviceModel);
				if (command != null) {
					logger.logInfo("About to verify command for device model " + deviceModel);
					boolean verifiedCommand = command.verifyCommand(commandEvent, trackingMessage);
					
					if (verifiedCommand) {
						if (CommandState.DELIVERED.name().equals(commandEvent.getState())) {
							logger.logInfo("Verifying message sequence " + messageSeq);
							commandEvent.setState(CommandState.VERIFIED.name());
							commandEventDao.save(commandEvent);
						} else {
							logger.logInfo("Expected event state mismatch " + commandEvent.getState());
						}
					} else {
						logger.logInfo("Expected output configuration mismatch " + trackingMessage.serialize());
					}
				} else {
					logger.logError("Unable to find a command handler for device " + deviceModel);
				}
			} else {
				logger.logError("Could not find command event by message sequence " + messageSeq);
			}
			
		} catch (Exception e) {
			logger.logError("Unexpected Exception", e);
		}
	}

	public void setCommandEventDao(CommandEventDao commandEventDao) {
		this.commandEventDao = commandEventDao;
	}

	public void setCommands(Map<String, DeviceCommandHandler> commands) {
		this.commands = commands;
	}

}
