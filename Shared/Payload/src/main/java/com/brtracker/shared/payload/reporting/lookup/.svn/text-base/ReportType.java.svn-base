package com.brtracker.shared.payload.reporting.lookup;


public enum ReportType {
	
	DISTANCE_TRAVELED (1),
	HOURS_DRIVEN (2),
	IDLE_TIME (3),
	ALERT_SPEEDING (4);
	
	private final int id; 
	
	ReportType(int id) {
       this.id = id;
    }
	
    public int getId()   { return id; }
    
    public static ReportType getReportType(int id)  {
    	for (ReportType vt : ReportType.values()){
    		if (vt.getId() == id) return vt;
    	}
    	return null;
    }
}
