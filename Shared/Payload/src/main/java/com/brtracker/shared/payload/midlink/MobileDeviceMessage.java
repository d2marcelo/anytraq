package com.brtracker.shared.payload.midlink;

import com.brtracker.shared.payload.controller.lookup.DeviceModel;
import com.brtracker.shared.payload.controller.lookup.DeviceType;

public class MobileDeviceMessage {

	private DeviceType deviceType;
	private DeviceModel deviceModel;
	private MobileDeviceEventType mobileDeviceEventType;

	private String deviceAddress;
	private double latitude;
	private double longitude;
	private double altitude;
	private double accuracy;
	private double altitudeAccuracy;
	private double heading;
	private double speed;
	private long timeStamp;
	private String mediaFileName;
	private String mediaFileComment;
	
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public DeviceModel getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(DeviceModel deviceModel) {
		this.deviceModel = deviceModel;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	public double getAltitudeAccuracy() {
		return altitudeAccuracy;
	}
	public void setAltitudeAccuracy(double altitudeAccuracy) {
		this.altitudeAccuracy = altitudeAccuracy;
	}
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public String getDeviceAddress() {
		return deviceAddress;
	}
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}
	public MobileDeviceEventType getMobileDeviceEventType() {
		return mobileDeviceEventType;
	}
	public void setMobileDeviceEventType(MobileDeviceEventType mobileDeviceEventType) {
		this.mobileDeviceEventType = mobileDeviceEventType;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public void setMediaFileName(String mediaFileName){
		this.mediaFileName = mediaFileName;
	}
	
	public String getMediaFileName(){
		return this.mediaFileName;
	}
	
	public void setMediaFileComment(String mediaFileComment){
		this.mediaFileComment = mediaFileComment;
	}
	
	public String getMediaFileComment(){
		return this.mediaFileComment;
	}
	
}
