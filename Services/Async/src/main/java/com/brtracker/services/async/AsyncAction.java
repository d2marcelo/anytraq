package com.brtracker.services.async;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.brtracker.shared.payload.controller.lookup.DeviceState;
import com.brtracker.shared.payload.midlink.MediaEntry;
import com.brtracker.shared.payload.midlink.MediaType;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.payload.tracking.data.DeviceHistory;
import com.brtracker.shared.payload.tracking.lookup.DeviceMessageAttributeType;
import com.brtracker.shared.payload.tracking.lookup.DriverBehaviorAttributeType;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.StringUtil;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.math.Geofence;
import com.javadocmd.simplelatlng.util.LengthUnit;

public abstract class AsyncAction  implements Runnable, AsyncConstants  {
	
	public String message;
	public String latitude;
	public String longitude;
	public String address;
	public String course;
	public String speed;
	public String distance;
	public String overSpeeding;
	public String sharpBreaking;
	public String sharpLaneCross; 
	public String sharpTurns;
	public String fuel;
	public String quickAccel;
	public Long deviceTime;
	public Date systemTime;
	public DeviceState deviceState; 
	public DeviceMessageAttribute deviceMessageAttribute;
	public Map<String,String> deviceAttributes;
	public String mediaFileName;
	public String mediaFileComment;
	public boolean hasMedia = false;
	 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public void cleanup () {
		this.latitude = null;
		this.longitude = null;
		this.address = null;
		this.course = null;
		this.speed = null;
		this.distance = null;
		this.overSpeeding = null;
		this.sharpBreaking = null;
		this.sharpLaneCross = null; 
		this.sharpTurns = null;
		this.fuel = null;
		this.quickAccel = null;
		this.deviceTime = null;
		this.systemTime = null;
		this.deviceState = null; 
		this.deviceMessageAttribute = null;
		this.deviceAttributes=null;
		this.mediaFileName = null;
		this.mediaFileComment = null;
		this.hasMedia = false;
		
	}
	public void parseMessage () throws InvalidMessageException{
		try {
			cleanup();
			DeviceMessageAttribute deviceMessageAttribute = (DeviceMessageAttribute) JSONMapper.toObject(message, DeviceMessageAttribute.class);
			address = deviceMessageAttribute.getUnitAddress();
			if (address == null)throw new InvalidMessageException("Message has no device address. "+message);
			Map<String, String> attributes = deviceMessageAttribute.getAttributes();
			deviceAttributes = attributes;
			if (attributes.containsKey(DeviceMessageAttributeType.MEDIA_FILE_NAME)){
				hasMedia = true;
				mediaFileName = attributes.get(DeviceMessageAttributeType.MEDIA_FILE_NAME);
				mediaFileComment = attributes.get(DeviceMessageAttributeType.MEDIA_COMMENT);
			} else {
				hasMedia = false;
				mediaFileName = null;
				mediaFileComment = null;
			}
			latitude = attributes.get(DeviceMessageAttributeType.LATITUDE);
			longitude = attributes.get(DeviceMessageAttributeType.LONGITUDE);
			deviceTime = Long.parseLong(deviceMessageAttribute.getMessageDate());
			systemTime = DateUtil.getUTCDateAndTime();
			deviceState = DeviceState.getDeviceState(attributes.get(DeviceMessageAttributeType.STATE));
			course = attributes.get(DeviceMessageAttributeType.COURSE);
			if (deviceState == null) throw new InvalidMessageException("Message has no device state. "+message);
			distance = StringUtil.getZeroIfNull(attributes.get(DeviceMessageAttributeType.DISTANCE));
			speed = StringUtil.getZeroIfNull(attributes.get(DeviceMessageAttributeType.SPEED));
			overSpeeding = attributes.get(DriverBehaviorAttributeType.OVER_SPEEDING);
			sharpBreaking = attributes.get(DriverBehaviorAttributeType.SHARP_BREAKING);
			sharpLaneCross = attributes.get(DriverBehaviorAttributeType.SHARP_LANE_CROSS);
			sharpTurns = attributes.get(DriverBehaviorAttributeType.SHARP_TURNS);
			quickAccel = attributes.get(DriverBehaviorAttributeType.QUICK_ACCEL);
			fuel = attributes.get(DeviceMessageAttributeType.FUEL_PRI_LEVEL);
			
		} catch (JSONMapperException e) {
			throw new InvalidMessageException("Invalid Message: "+message);
		}
	}
	
	public void updateAttribute (String key, String value) {
		deviceAttributes.put(key, value);
	}
	
	public void updateMediaAttribute (DeviceHistory deviceHistory) throws JSONMapperException {
		String attributes = deviceHistory.getAttributes();
		Map<String, String> map = (Map<String, String>) JSONMapper.toObject(attributes, Map.class);
		List<MediaEntry> mediaEntry = new ArrayList<MediaEntry>();
		if (map.containsKey(DeviceMessageAttributeType.MEDIA)){
		mediaEntry  = (List<MediaEntry>) JSONMapper.toObject(map.get(DeviceMessageAttributeType.MEDIA), List.class);
		}
		MediaEntry entry = new MediaEntry();
		entry.setDateCreated(deviceTime);
		entry.setThumbnail("thumb_" +mediaFileName);
		if (mediaFileName.contains("jpg"))
		entry.setType(MediaType.PHOTO);
		else 
		entry.setType(MediaType.VIDEO);
		entry.setName(mediaFileName);
		entry.setDescription(mediaFileComment);
		entry.setId(new Long(mediaEntry.size() + 1));
		mediaEntry.add(entry);
		
		map.put(DeviceMessageAttributeType.MEDIA, JSONMapper.toString(mediaEntry));
		deviceHistory.setAttributes(JSONMapper.toString(map));
	}
	
	
	public void updateAttribute (DeviceHistory deviceHistory, String key, String value) throws JSONMapperException {
		Map<String,String> map = (Map<String, String>) JSONMapper.toObject(deviceHistory.getAttributes(), Map.class);
		map.put(key, value);
		deviceHistory.setAttributes(JSONMapper.toString(map));
	}
	public boolean hasDeviceMoved(String previousLat, String previousLong){
		double distanceInMeters = Geofence.distFrom(Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(previousLat), Double.parseDouble(previousLong), LengthUnit.METER);
		boolean hasNotMoved =  ((previousLat.equals(latitude) && previousLong.equals(longitude)) || (distanceInMeters < AsyncConstants.MAX_GPS_DISTANCE));
		return !hasNotMoved;
	}
	
	public DeviceState getState () {
		return deviceState;
	}
	public DeviceState getState (String attributes ) throws JSONMapperException, InvalidMessageException{
		Map<String, String> map = (Map<String, String>) JSONMapper.toObject(attributes, Map.class);
		String state =  map.get(DeviceMessageAttributeType.STATE);
		DeviceState dState =  DeviceState.getDeviceState(state);
		if (dState == null) throw new InvalidMessageException("Device State not Supported. "+state);
		return dState;
	}
	public boolean isDeviceMoving () {
		return deviceState.equals(DeviceState.MOVING);
	}
	
	public boolean isDeviceStoppedOrTripFinished () {
		return deviceState.equals(DeviceState.STOPPED) || deviceState.equals(DeviceState.TRIP_ENDED);
	}
	
	public boolean isDeviceStopped () {
		return deviceState.equals(DeviceState.STOPPED);
	}
	
	public boolean hasTripStarted () {
		return deviceState.equals(DeviceState.TRIP_STARTED);
	}
	
	public boolean hasTripEnded () {
		return deviceState.equals(DeviceState.TRIP_ENDED);
	}
	
	public boolean isNoGPS () {
		return deviceState.equals(DeviceState.NO_GPS);
	}
	public boolean hasTripEndedStarted () {
		return deviceState.equals(DeviceState.TRIP_END_START);
	}
	public boolean isDeviceStopped (DeviceState state) {
		return state.equals(DeviceState.STOPPED);
	}
	public boolean isDeviceMoving (DeviceState state) {
		return state.equals(DeviceState.MOVING);
	}
	public boolean hasTripEndedStarted (DeviceState state) {
		return state.equals(DeviceState.TRIP_END_START);
	}
	
	public boolean hasTripStarted (DeviceState state) {
		return state.equals(DeviceState.TRIP_STARTED);
	}
	
	public boolean hasTripEnded (DeviceState state) {
		return state.equals(DeviceState.TRIP_ENDED);
	}
	
	
	
}
