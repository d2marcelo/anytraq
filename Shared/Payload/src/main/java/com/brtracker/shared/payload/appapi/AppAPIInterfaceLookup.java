package com.brtracker.shared.payload.appapi;


public enum AppAPIInterfaceLookup {
	
	
	//deviceCommand_TEMPLATE ("appapi/deviceCommand/template"),
	deviceCommand_TEMPLATE ("appapi/device/command/template"),
	deviceCommand_REQUEST ("appapi/device/command/request"),
	deviceCommand_LIST ("appapi/device/command/list"),
	deviceCommand_APPROVE ("appapi/device/command/approve"),
	deviceCommandCANCEL ("appapi/device/command/cancel"),
	deviceCommandIOConfig ("appapi/device/command/io-config"),

	referral_ADD("appapi/referral/add"),
	referral_UPDATE("appapi/referral/update"),
	referral_LIST("appapi/referral/list"),
	
	
	subscription_CREATE ("appapi/subscription/create"),
	subscription_UPDATE ("appapi/subscription/update"),
	subscription_DELETE ("appapi/subscription/delete"),
	
	
	account_ADD ("appapi/account/add"),
	account_LIST ("appapi/account/list"),
	account_GET ("appapi/account/get"),
	account_DELETE ("appapi/account/delete"),
	account_UPDATE ("appapi/account/update"),
	account_HAS_ACTIVE_DEVICES ("appapi/account/hasActiveDevices"),
	
	affiliate_ADD ("appapi/affiliate/add"),
	affiliate_LIST ("appapi/affiliate/list"),
	affiliate_GET ("appapi/affiliate/get"),
	affiliate_DELETE ("appapi/affiliate/delete"),
	affiliate_UPDATE ("appapi/affiliate/update"),
	
	city_ADD ("appapi/city/add"),
	city_LIST ("appapi/city/list"),
	city_DELETE ("appapi/city/delete"),
	
	alert_ADD ("appapi/alert/add"),
	alert_UPDATE ("appapi/alert/update"),
	alert_LIST ("appapi/alert/list"),
	alert_DELETE ("appapi/alert/delete"),
	
	alertMessage_UPDATE ("appapi/alertMessage/update"),
	alertMessage_LIST ("appapi/alertMessage/list"),
	alertMessage_DELETE ("appapi/alertMessage/delete"),
	alertMessage_COUNT ("appapi/alertMessage/count"),
	
	defaultAlertConfiguration_ADD ("appapi/defaultAlertConfiguration/add"),
	defaultAlertConfiguration_DELETE ("appapi/defaultAlertConfiguration/delete"),
	defaultAlertConfiguration_UPDATE ("appapi/defaultAlertConfiguration/update"),
	defaultAlertConfiguration_LIST ("appapi/defaultAlertConfiguration/list"),

	geofenceAlertConfiguration_ADD ("appapi/geofenceAlertConfiguration/add"),
	geofenceAlertConfiguration_DELETE ("appapi/geofenceAlertConfiguration/delete"),
	geofenceAlertConfiguration_UPDATE ("appapi/geofenceAlertConfiguration/update"),
	geofenceAlertConfiguration_LIST ("appapi/geofenceAlertConfiguration/list"),
	
	country_ADD ("appapi/country/add"),
	country_LIST ("appapi/country/list"),
	country_DELETE ("appapi/country/delete"),
	
	media_UPDATE ("appapi/media/update"),
	media_DELETE ("appapi/media/delete"),
	
	mediaComment_ADD ("appapi/mediaComment/add"),
	mediaComment_UPDATE ("appapi/mediaComment/update"),
	mediaComment_DELETE ("appapi/mediaComment/delete"),
	mediaComment_LIST ("appapi/mediaComment/list"),
	
	
	state_ADD ("appapi/state/add"),
	state_LIST ("appapi/state/list"),
	state_DELETE ("appapi/state/delete"),
	
	device_ADD ("appapi/device/add"),
	device_TRANSFER ("appapi/device/transfer"),
	device_DELETE ("appapi/device/delete"),
	device_UPDATE ("appapi/device/update"),
	device_LIST ("appapi/device/list"),
	device_HISTORY ("appapi/device/history"),
	device_STATUS ("appapi/device/status"),
	device_HISTORY_DATES ("appapi/device/historyDates"),
	device_GET ("appapi/device/get"),
	device_AVAILABLE ("appapi/device/available"),
	device_INACTIVE ("appapi/device/inactive"),
	
	deviceVehicleMap_ADD ("appapi/deviceVehicleMap/add"),
	deviceVehicleMap_DELETE ("appapi/deviceVehicleMap/delete"),
	deviceVehicleMap_LIST ("appapi/deviceVehicleMap/list"),
	deviceVehicleMap_UPDATE ("appapi/deviceVehicleMap/update"),
	deviceVehicleMap_GET ("appapi/deviceVehicleMap/get"),
	
	
	driver_ADD ("appapi/driver/add"),
	driver_DELETE ("appapi/driver/delete"),
	driver_UPDATE ("appapi/driver/update"),
	driver_LIST ("appapi/driver/list"),
	driver_SEARCH ("appapi/driver/search"),

	affiliateUser_ADD ("appapi/affiliateUser/add"),
	affiliateUser_DELETE ("appapi/affiliateUser/delete"),
	affiliateUser_UPDATE ("appapi/affiliateUser/update"),
	affiliateUser_LIST ("appapi/affiliateUser/list"),
	affiliateUser_LOGIN ("appapi/affiliateUser/login"),
	affiliateUser_EMAIL_AVAILABLE("appapi/affiliateUser/isEmailAvailable"),
	
	accountUser_ADD ("appapi/accountUser/add"),
	accountUser_DELETE ("appapi/accountUser/delete"),
	accountUser_UPDATE ("appapi/accountUser/update"),
	accountUser_LIST ("appapi/accountUser/list"),
	accountUser_LOGIN ("appapi/accountUser/login"),
	accountUser_RECOVER_PWD ("appapi/accountUser/recoverPassword"),
	accountUser_EMAIL_AVAILABLE("appapi/accountUser/isEmailAvailable"),
	
	vehicle_ADD ("appapi/vehicle/add"),
	vehicle_DELETE ("appapi/vehicle/delete"),
	vehicle_UPDATE ("appapi/vehicle/update"),
	vehicle_LIST ("appapi/vehicle/list"),
	vehicle_STATUS ("appapi/vehicle/status"),
	vehicle_ADD_VEHICLE_DEVICE_MAP("appapi/vehicle/addVehicleDeviceMap"),
	vehicle_UPDATE_VEHICLE_DEVICE_MAP("appapi/vehicle/updateVehicleDeviceMap"),

	
	driverVehicleMap_ADD ("appapi/driverVehicleMap/add"),
	driverVehicleMap_DELETE ("appapi/driverVehicleMap/delete"),
	driverVehicleMap_UPDATE ("appapi/driverVehicleMap/update"),
	driverVehicleMap_LIST ("appapi/driverVehicleMap/list"),
	driverVehicleMap_UPSERT ("appapi/driverVehicleMap/upsert"),
	driverVehicleMap_ASSETS ("appapi/driverVehicleMap/assets"),
	driverVehicleMap_GET ("appapi/driverVehicleMap/get"),
	
	driverBehavior_LIST("appapi/driverBehavior/list"),
	
	tripSummary_LIST("appapi/tripSummary/list"),
	
	graphData_LIST ("appapi/graphData/list"), 
	
	report_ACTIVITY("appapi/report/activity"),
	report_ALERT("appapi/report/alert"),
	report_IDLE("appapi/report/idle"),
	
	trip_ADD("appapi/trip/add"),
	trip_UPDATE("appapi/trip/update"),
	trip_DELETE("appapi/trip/delete"),
	trip_LIST ("appapi/trip/list"),
	trip_DETAIL ("appapi/trip/detail"),
	trip_GET ("appapi/trip/get");
	
	private final String path; 
	AppAPIInterfaceLookup(String path) {
       this.path = path;
    }
    public String getPath()   { return path; }
    
    public static String getURLPath (AppAPIInterfaceLookup type , String ip) {
    
    	    return ip + "/" + type.getPath();	    
    }
    

	
}
