package com.brtracker.services.midlink.processing;

import com.brtracker.shared.utils.packet.TrackingMessage;

public interface DeviceStateAwareMessageAction {

	void execute(TrackingMessage trackingMessage, DeviceStateEntity deviceState);
}
