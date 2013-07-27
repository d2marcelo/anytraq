package com.brtracker.shared.utils.packet.vendors;

import com.brtracker.shared.utils.packet.TrackingMessage;

public class XirgoPacketReaderController {

	private DefaultXirgoEventReader defaultXirgoEventReader;
	
	public TrackingMessage readPacket(short[] packet) {
		return defaultXirgoEventReader.readPacket(packet);
	}

	public void setDefaultXirgoEventReader(
			DefaultXirgoEventReader defaultXirgoEventReader) {
		this.defaultXirgoEventReader = defaultXirgoEventReader;
	}
}
