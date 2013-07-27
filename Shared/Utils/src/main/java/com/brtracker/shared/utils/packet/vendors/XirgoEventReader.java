package com.brtracker.shared.utils.packet.vendors;

import com.brtracker.shared.utils.packet.TrackingMessage;

public interface XirgoEventReader {

	public TrackingMessage readPacket(short[] packet);
}
