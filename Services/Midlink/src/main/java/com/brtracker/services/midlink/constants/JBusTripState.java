package com.brtracker.services.midlink.constants;

public enum JBusTripState {

	UNKNOWN           (0),
	ENGINE_OFF   	  (1),
	ENGINE_STARTED_ON (2),
	ENGINE_ON         (3),
	ON_ROUTE          (4);
	
	
		
	private final int code;
	JBusTripState (int code) {
		this.code = code;
	}
	public  int getCode () {
		return code;
	}
	
	public static JBusTripState getGPSFieldType (int code){
		for (JBusTripState gps : JBusTripState.values()){
			if (gps.getCode() == code) return gps;
		}
		return JBusTripState.UNKNOWN;
	}
	
}
