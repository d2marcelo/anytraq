package com.brtracker.services.midlink.processing.command;

import com.brtracker.shared.payload.midlink.CommandRequest;

public interface CommandDispatcher {
	public void dispatchCommand(CommandRequest request) throws Exception;
}
