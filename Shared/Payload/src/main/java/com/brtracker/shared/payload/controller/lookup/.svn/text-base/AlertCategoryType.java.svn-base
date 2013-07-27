package com.brtracker.shared.payload.controller.lookup;

public enum AlertCategoryType {

	GEOFENCING (1),
	DRIVER (2),
	VEHICLE (3),
	DEVICE (4);
	
	private final int id; 
	AlertCategoryType (int id) {
       this.id = id;
    }
    public int getId()   { return id; }
    
    public static AlertCategoryType getAlertCategoryType(int id)  {
    	for (AlertCategoryType vt : AlertCategoryType.values()){
    		if (vt.getId() == id) return vt;
    	}
    	return null;
    }

}
