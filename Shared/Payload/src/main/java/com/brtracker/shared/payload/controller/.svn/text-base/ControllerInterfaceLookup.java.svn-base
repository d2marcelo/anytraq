package com.brtracker.shared.payload.controller;

public enum ControllerInterfaceLookup {

	account_ADD ("accountws/account/add"),
	account_LIST ("accountws/account/list"),
	account_GET ("accountws/account/get"),
	account_DELETE ("accountws/account/delete"),
	account_UPDATE ("accountws/account/update"),
	account_HAS_ACTIVE_DEVICES ("accountws/account/hasActiveDevices"),
	
	referral_ADD("accountws/referral/add"),
	referral_UPDATE("accountws/referral/update"),
	referral_LIST("accountws/referral/list"),
	
	subscription_CREATE ("accountws/subscription/create"),
	subscription_UPDATE ("accountws/subscription/update"),
	subscription_DELETE ("accountws/subscription/delete"),
	
	affiliate_ADD ("accountws/affiliate/add"),
	affiliate_LIST ("accountws/affiliate/list"),
	affiliate_GET ("accountws/affiliate/get"),
	affiliate_DELETE ("accountws/affiliate/delete"),
	affiliate_UPDATE ("accountws/affiliate/update"),
	
	
	city_ADD ("accountws/city/add"),
	city_LIST ("accountws/city/list"),
	city_DELETE ("accountws/city/delete"),
	
	country_ADD ("accountws/country/add"),
	country_LIST ("accountws/country/list"),
	country_DELETE ("accountws/country/delete"),
	
	
	state_ADD ("accountws/state/add"),
	state_LIST ("accountws/state/list"),
	state_DELETE ("accountws/state/delete"),
	
	device_ADD ("accountws/device/add"),
	device_DELETE ("accountws/device/delete"),
	device_UPDATE ("accountws/device/update"),
	device_LIST ("accountws/device/list"),
	device_GET ("accountws/device/get"),
	device_TRANSFER ("accountws/device/transfer"),
	device_AVAILABLE ("accountws/device/available"),
	device_INACTIVE ("accountws/device/inactive"),
	
	deviceVehicleMap_ADD ("accountws/deviceVehicleMap/add"),
	deviceVehicleMap_DELETE ("accountws/deviceVehicleMap/delete"),
	deviceVehicleMap_LIST ("accountws/deviceVehicleMap/list"),
	deviceVehicleMap_UPDATE ("accountws/deviceVehicleMap/update"),
	deviceVehicleMap_GET ("accountws/deviceVehicleMap/get"),
	deviceVehicleMap_GET_DEVICE("accountws/deviceVehicleMap/getDevice"),
	
	driver_ADD ("accountws/driver/add"),
	driver_DELETE ("accountws/driver/delete"),
	driver_UPDATE ("accountws/driver/update"),
	driver_LIST ("accountws/driver/list"),
	driver_SEARCH ("accountws/driver/search"),
	driver_GET ("accountws/driver/get"),
	
	affiliateUser_ADD ("accountws/affiliateUser/add"),
	affiliateUser_DELETE ("accountws/affiliateUser/delete"),
	affiliateUser_UPDATE ("accountws/affiliateUser/update"),
	affiliateUser_LIST ("accountws/affiliateUser/list"),
	affiliateUser_LOGIN ("accountws/affiliateUser/login"),
	affiliateUser_EMAIL_AVAILABLE("accountws/affiliateUser/isEmailAvailable"),

	accountUser_ADD ("accountws/accountUser/add"),
	accountUser_DELETE ("accountws/accountUser/delete"),
	accountUser_UPDATE ("accountws/accountUser/update"),
	accountUser_LIST ("accountws/accountUser/list"),
	accountUser_LOGIN ("accountws/accountUser/login"),
	accountUser_RECOVER_PWD ("accountws/accountUser/recoverPassword"),
	accountUser_EMAIL_AVAILABLE("accountws/accountUser/isEmailAvailable"),
	
	vehicle_ADD ("accountws/vehicle/add"),
	vehicle_DELETE ("accountws/vehicle/delete"),
	vehicle_UPDATE ("accountws/vehicle/update"),
	vehicle_LIST ("accountws/vehicle/list"),
	vehicle_GET ("accountws/vehicle/get"),
	vehicle_ADD_VEHICLE_DEVICE_MAP("accountws/vehicle/addVehicleDeviceMap"),
	vehicle_UPDATE_VEHICLE_DEVICE_MAP("accountws/vehicle/updateVehicleDeviceMap"),
	
	alert_ADD ("accountws/alert/add"),
	alert_DELETE ("accountws/alert/delete"),
	alert_UPDATE ("accountws/alert/update"),
	alert_LIST ("accountws/alert/list"),

	defaultAlertConfiguration_ADD ("accountws/defaultAlertConfiguration/add"),
	defaultAlertConfiguration_DELETE ("accountws/defaultAlertConfiguration/delete"),
	defaultAlertConfiguration_UPDATE ("accountws/defaultAlertConfiguration/update"),
	defaultAlertConfiguration_LIST ("accountws/defaultAlertConfiguration/list"),
	
	
	
	geofenceAlertConfiguration_ADD ("accountws/geofenceAlertConfiguration/add"),
	geofenceAlertConfiguration_DELETE ("accountws/geofenceAlertConfiguration/delete"),
	geofenceAlertConfiguration_UPDATE ("accountws/geofenceAlertConfiguration/update"),
	geofenceAlertConfiguration_LIST ("accountws/geofenceAlertConfiguration/list"),
	
	driverVehicleMap_ADD ("accountws/driverVehicleMap/add"),
	driverVehicleMap_DELETE ("accountws/driverVehicleMap/delete"),
	driverVehicleMap_UPDATE ("accountws/driverVehicleMap/update"),
	driverVehicleMap_LIST ("accountws/driverVehicleMap/list"),
	driverVehicleMap_ASSETS ("accountws/driverVehicleMap/assets"),
	driverVehicleMap_GET ("accountws/driverVehicleMap/get"),
	driverVehicleMap_UPSERT ("accountws/driverVehicleMap/upsert"),
	driverVehicleMap_GET_VEHICLE("accountws/driverVehicleMap/getVehicle");
	
	private final String path; 
    ControllerInterfaceLookup(String path) {
       this.path = path;
    }
    public String getPath()   { return path; }
    
    public static String getURLPath (ControllerInterfaceLookup type , String ip) {
    	return ip +"/"+type.getPath();
    }
    
}
