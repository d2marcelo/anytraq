package com.brtracker.shared.payload.controller.lookup;

public enum AlertSeverityType {

	CRITICAL (1),
	WARNING (2),
	INFORMATIONAL(3);
	
	private final int id; 
	AlertSeverityType (int id) {
       this.id = id;
    }
    public int getId()   { return id; }
    
    public static AlertSeverityType getAlertSeverityType(int id)  {
    	for (AlertSeverityType vt : AlertSeverityType.values()){
    		if (vt.getId() == id) return vt;
    	}
    	return null;
    }
}
