package com.brtracker.shared.payload.tracking;


public enum TrackingInterfaceLookup {

	// lookup
	
	media_UPDATE("tracking/media/update"),
	media_DELETE("tracking/media/delete"),
	
	mediaComment_ADD ("tracking/mediaComment/add"),
	mediaComment_UPDATE ("tracking/mediaComment/update"),
	mediaComment_DELETE ("tracking/mediaComment/delete"),
	mediaComment_LIST ("tracking/mediaComment/list"),
	
	vehicleStatus_UPSERT ("tracking/vehicleStatus/upsert"),
	vehicleStatus_LIST ("tracking/vehicleStatus/list"),
	
	deviceMessage_ADD ("tracking/deviceMessage/add"),
	deviceMessage_LIST ("tracking/deviceMessage/list"),
	
	driverBehavior_LIST("tracking/driverBehavior/list"),
	
	tripSummary_LIST("tracking/tripSummary/list"),
	
	alertMessage_UPDATE ("tracking/alertMessage/update"),
	alertMessage_LIST ("tracking/alertMessage/list"),
	alertMessage_DELETE ("tracking/alertMessage/delete"),
	alertMessage_COUNT ("tracking/alertMessage/count"),
	
	graphData_LIST ("tracking/graphData/list"),
	deviceHistory_DATES ("tracking/deviceHistory/dates"),
	
	deviceStatus_LIST ("tracking/deviceStatus/list"),
	
	report_ACTIVITY("tracking/report/activity"),
	report_IDLE("tracking/report/idle"),
	report_ALERT("tracking/report/alert"),
	
	trip_ADD("tracking/trip/add"),
	trip_UPDATE("tracking/trip/update"),
	trip_DELETE("tracking/trip/delete"),
	trip_LIST ("tracking/trip/list"),
	trip_DETAIL ("tracking/trip/detail"),
	trip_GET ("tracking/trip/get"),
	
	deviceHistory_GET ("tracking/deviceHistory/get");
	
	private final String path; 
    TrackingInterfaceLookup(String path) {
       this.path = path;
    }
    public String getPath()   { return path; }
    
    public static String getURLPath (TrackingInterfaceLookup type , String ip) {
    	return ip +"/"+type.getPath();
    }
    
	
}
