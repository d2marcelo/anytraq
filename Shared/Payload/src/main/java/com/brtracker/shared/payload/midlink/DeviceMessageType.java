package com.brtracker.shared.payload.midlink;

public enum DeviceMessageType {
	
	GPS_MSG     (0xC9),
	NO_GPS_MSG  (0xE4),
	LOC_BOUND   (0xCD),
	IO_STATE    (0xCB),
	LOGIN       (0xFE),
	LOGOUT      (0xFD),
	LOW_BATTERY     (0xFB),
	EMERGENCY   (0x0a),
	IN_COVERAGE (0xF8),
	ACKNOWLEDGMENT (0xFC),
	IP_CHANGED  (0x17),
	JBUS (0x1E);
	
	
	private final int code;
	DeviceMessageType (int code) {
		this.code = code;
	}
	public int getCode () {
		return code;
	}
}
