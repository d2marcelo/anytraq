package com.brtracker.services.midlink.constants;

public enum JBusFieldType {

	MSG_NUMBER       (1),
	REPORT_TYPE 	 (2),
	TRIP_STATE       (3),
	CURR_DATE        (4),
	CURR_TIME        (5),
	SEC_DATE_FIELD   (6),
	SEC_TIME_FIELD   (7),
	TOTAL_DISTANCE   (8),
	FUEL_LEVEL_PRI   (9),
	FUEL_LEVEL_SEC    (10),
	AVG_FUEL_RATE     (11),
	PTO_STATUS        (12),
	BATT_POTENTIAL    (13),
	CURR_ROAD_SPEED   (14),
	JBUS_TYPE         (15),
	RESERVED          (16),
	CURR_ENGINE_SPEED (17),
	CURR_ENGINE_LOAD  (18),
	ACCELERATOR_PEDAL (19),
	ENGINE_SPEED_TRIP_STATS (20),
	TRIP_TOTAL_DISTANCE (21),
	TRIP_FUEL_LEVEL_PRI (22),
	TRIP_FUEL_LEVEL_SEC (23),
	UNKNOWN (999);
	
		
	private final int code;
	JBusFieldType (int code) {
		this.code = code;
	}
	public  int getCode () {
		return code;
	}
	
	public static JBusFieldType getJBusFieldType (int code){
		for (JBusFieldType jbus : JBusFieldType.values()){
			if (jbus.getCode() == code) return jbus;
		}
		return JBusFieldType.UNKNOWN;
	}
	
}
