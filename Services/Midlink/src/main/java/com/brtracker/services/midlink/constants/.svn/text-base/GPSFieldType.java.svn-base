package com.brtracker.services.midlink.constants;

public enum GPSFieldType {

	FORMAT        (1),
	LONG 	      (2),
	SHORT         (5),
	REASON        (6),
	STOP_LEN      (7),
	STOP_TIME     (8),
	DISTANCE      (9),
	START_STATE   (10),
	MSG_NUM       (19),
	ACC_ALRM_CNT  (20),
	A2D           (22),
	ENGINE_STATE  (26),
	NUM_SAT       (27),
	SV_SNR        (28),
	SAT_USED      (29),
	HDOP          (30),
	CELL_MCC      (35),
	CELL_MNC      (36),
	CELL_LAC      (37),
	CELL_ID       (38),
	GSM_RSSI      (39),
	RESERVED_248 (248),
	RESERVED_40  (40),
	PWR          (246),
	SERIAL_NUM   (247),
	BAT_CONV     (249),
	INTERN_TEMP  (250),
	BAT_AT_STRT  (251),
	BAT_NOW      (252),
	TIME         (255),
	UNKNOWN  (99);
	
	private final int code;
	GPSFieldType (int code) {
		this.code = code;
	}
	public  int getCode () {
		return code;
	}
	
	public static GPSFieldType getGPSFieldType (int code){
		for (GPSFieldType gps : GPSFieldType.values()){
			if (gps.getCode() == code) return gps;
		}
		return GPSFieldType.UNKNOWN;
	}
	
}
