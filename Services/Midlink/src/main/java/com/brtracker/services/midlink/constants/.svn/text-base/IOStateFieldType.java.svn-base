package com.brtracker.services.midlink.constants;

public enum IOStateFieldType {

	SEQUENCE_NUMBER      (1),
	DRY_INPUT_1 	 (2),
	DRY_INPUT_2       (3),
	DRY_INPUT_3        (4),
	DRY_INPUT_4        (5),
	DRY_INPUT_5   (6),
	DRY_INPUT_6   (7),
	DRY_INPUT_7   (8),
	DRY_INPUT_8   (9),
	A2D_VALUE      (10),
	RESERVED_1     (11),
	NOT_USED        (12),
	OUTPUT_1    (13),
	OUTPUT_2   (14),
	OUTPUT_3         (15),
	RESERVED_2          (16),
	OUTPUT_4         (17),
	GPS_DATA_FORMAT (32),
	GPS_DATA  (33),
	GPS_TOTAL_DISTANCE (34),
	INPUT_9 (41),
	INPUT_10 (42),
	OUTPUT_9 (43),
	POWER_STATE_1 (44),
	INPUT_24_A (69),
	INPUT_24_B (70),
	OUTPUT_16 (71),
	POWER_STATE_2 (72),
	UNKNOWN (999);
		
	private final int code;
	IOStateFieldType (int code) {
		this.code = code;
	}
	public  int getCode () {
		return code;
	}
	
	public static IOStateFieldType getIOStateFieldType (int code){
		for (IOStateFieldType io : IOStateFieldType.values()){
			if (io.getCode() == code) return io;
		}
		return IOStateFieldType.UNKNOWN;
	}
	
}

