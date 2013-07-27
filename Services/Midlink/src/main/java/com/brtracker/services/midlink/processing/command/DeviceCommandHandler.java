package com.brtracker.services.midlink.processing.command;

import com.brtracker.shared.payload.midlink.CommandRequest;
import com.brtracker.shared.utils.packet.TrackingMessage;

public interface DeviceCommandHandler {
	public void sendCommand(CommandTemplateEntity template, CommandRequest request);
	public void resendCommand(CommandEventEntity event);
	public boolean verifyCommand(CommandEventEntity event, TrackingMessage trackingMessage);
}
