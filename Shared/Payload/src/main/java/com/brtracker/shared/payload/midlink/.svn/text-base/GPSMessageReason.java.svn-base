package com.brtracker.shared.payload.midlink;

import java.io.Serializable;

public enum GPSMessageReason implements Serializable {

	RESP_GPS_REQUEST (1),
	GPS_SERVICE_START (2),
	ENTRY_NO_MOVE_STATE (3),
	NO_MOVE_TIMEOUT_EXPIRED(4),
	VEHICLE_START_MOVING(5),
	TIME_INTERVAL_FIRST_EXPIRATION(6),
	TIME_INTERVAL_SECOND_EXPIRATION(7),
	TIME_INTERVAL_THIRD_EXPIRATION(8),
	PRECONFIGURED_DISTANCE_FIRST_PASSED (9),
	PRECONFIGURED_DISTANCE_SECOND_PASSED (10),
	PRECONFIGURED_DISTANCE_THIRD_PASSED (11),
	ENTRY_BOUNDARIES_REGION (12),
	EXIT_BOUNDARIES_REGION (13),
	SPEEDING_DETECTED (14);
		
	    private final int id; 
	    GPSMessageReason(int id) {
	       this.id = id;
	    }
	    public int id()   { return id; }
	    
	    public static int getSize () {
	    	return GPSMessageReason.values().length;
	    }
	    public static GPSMessageReason getGPSMessageReason (int id) {
	    	for (GPSMessageReason a: GPSMessageReason.values()) {
	    		if (a.id() == id) return a;
	    	}
	    	return null;
	    }

}
