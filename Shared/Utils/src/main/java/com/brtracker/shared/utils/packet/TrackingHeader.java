package com.brtracker.shared.utils.packet;

import java.util.Map;

public class TrackingHeader extends DefaultTrackingMessage {
	private int unitPacketStartIndex;

	public TrackingHeader() {
		super();
	}

	public TrackingHeader(Map<String, Object> messagePropertyMap,
			String messageName) {
		super(messagePropertyMap, messageName);
	}

	public int getUnitPacketStartIndex() {
		return unitPacketStartIndex;
	}

	public void setUnitPacketStartIndex(int unitPacketStartIndex) {
		this.unitPacketStartIndex = unitPacketStartIndex;
	}

}
