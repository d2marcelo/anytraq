package com.brtracker.services.midlink.constants;

public enum JBusReportType {

	TRIP_START       (0),
	CURRENT_STATE 	 (1),
	TRIP_END         (2),
	ENGINE_IDLE      (3),
	DIAGNOSTIC_REPORT(4),
	HOST_RESPONSE    (6),
	UNKNOWN (999);
		
	private final int code;
	JBusReportType (int code) {
		this.code = code;
	}
	public  int getCode () {
		return code;
	}
	
	public static JBusReportType getGPSFieldType (int code){
		for (JBusReportType gps : JBusReportType.values()){
			if (gps.getCode() == code) return gps;
		}
		return JBusReportType.UNKNOWN;
	}
	
}
