package com.brtracker.services.midlink.constants;

public enum PacketCommunicationType {
	
	APP_CLASS (0x31),
	PRESENTATION_CLASS  (0x32),
	KEEP_ALIVE_CLASS  (0x34),
	UNKNOWN_PACKET_CLASS (9999);

	private final int code;
	PacketCommunicationType (int code) {
		this.code = code;
	}
	public int getCode () {
		return code;
	}
	
}
