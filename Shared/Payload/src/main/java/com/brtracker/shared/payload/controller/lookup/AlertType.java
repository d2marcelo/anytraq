package com.brtracker.shared.payload.controller.lookup;

public enum AlertType {

	DRIVER_OVER_SPEEDING (1),
	DRIVER_EMERGENCY (2),
	DRIVER_SHARP_LANE_CROSS (3),
	DRIVER_SHARP_BREAKING (4),
	DRIVER_SHARP_TURNS (5),
	DRIVER_QUICK_ACCEL (6),
	GEO_FENCING_IN (7),
	GEO_FENCING_OUT (8),
	VEHICLE_OUT_OF_GAS (9),
	VEHICLE_ENGINE_TEMPERATURE (10),
	VEHICLE_OIL_PRESSURE (11),
	DEVICE_BATTERY (12);
	
	private final int id; 
	AlertType (int id) {
       this.id = id;
    }
    public int getId()   { return id; }
    
    public static AlertType getAlertType(int id)  {
    	for (AlertType vt : AlertType.values()){
    		if (vt.getId() == id) return vt;
    	}
    	return null;
    }
}
