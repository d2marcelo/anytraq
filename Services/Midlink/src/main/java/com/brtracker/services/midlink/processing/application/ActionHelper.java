package com.brtracker.services.midlink.processing.application;

import static com.brtracker.services.midlink.processing.application.TrackingPayloadFields.*;
import static com.brtracker.shared.utils.SystemConfiguration.MIDLINK;
import static com.brtracker.shared.utils.SystemConfiguration.MIDLINK_DIST_THRESHOLD;
import static com.brtracker.shared.utils.SystemConfiguration.MIDLINK_TIME_THRESHOLD;

import com.brtracker.services.midlink.processing.DeviceStateDao;
import com.brtracker.services.midlink.processing.DeviceStateEntity;
import com.brtracker.services.midlink.util.MessageUtil;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.payload.controller.lookup.DeviceState;

public class ActionHelper {

	protected MyLogger logger = new MyLogger(ActionHelper.class);
	private DeviceStateDao deviceStateDao;
	private SystemConfiguration systemConfiguration;

	public boolean appendCurrentDeviceLocation(
			DeviceMessageAttribute serviceRequest, String unitAddress) throws Exception {
		
		DeviceStateEntity dbDeviceState = deviceStateDao.getDeviceState(unitAddress);
		
		if (dbDeviceState != null) {
			String dbLat = String.valueOf(dbDeviceState.getLat());
			String dbLng = String.valueOf(dbDeviceState.getLng());

			serviceRequest.put(TRK_LATITUDE, dbLat);
			serviceRequest.put(TRK_LONGITUDE, dbLng);
			
			return true;
		} else {
			return false;
		}
	}

	public void setMovingStateFromDb(DeviceStateEntity currentDeviceState,
			DeviceMessageAttribute serviceRequest) throws Exception {
		
		String unitAddress = currentDeviceState.getUnitAddress();
		DeviceStateEntity dbDeviceState = deviceStateDao.getDeviceState(unitAddress);	
		if (dbDeviceState != null) {
			serviceRequest.put(TRK_STATE, dbDeviceState.getState());
			currentDeviceState.setState(dbDeviceState.getState());
		} else {
			setDeviceState(serviceRequest, currentDeviceState, DeviceState.MOVING.name());
		}
	}
	
	public void setRequestVehicleStatev2(DeviceStateEntity currentDeviceState,
			DeviceMessageAttribute serviceRequest) throws Exception {
		
		String unitAddress = currentDeviceState.getUnitAddress();

		if (currentDeviceState.getSatellites() == 0) {
			logger.logInfo("Num satellites = 0, setting no gps status " + unitAddress);
			setDeviceNoGps(serviceRequest, currentDeviceState);
		} else {
			if (currentDeviceState.getSpeed() > 0.0) {
				logger.logInfo("current device speed > 0, setting device moving" + unitAddress);
				setDeviceMoving(serviceRequest, currentDeviceState);
			} else {
				logger.logInfo("current device speed < 0, setting device stopped" + unitAddress);
				setDeviceStopped(serviceRequest, currentDeviceState);
			}
		}
		
		logger.logInfo("pulling device by address [" + unitAddress + "]");
		DeviceStateEntity dbDeviceState = deviceStateDao.getDeviceState(unitAddress);
		if (dbDeviceState != null) {
			upsertDeviceState(currentDeviceState, dbDeviceState);
		} else {
			saveCurrentDeviceState(currentDeviceState);
		}

	}
	
	public void setRequestVehicleState(DeviceStateEntity currentDeviceState,
			DeviceMessageAttribute serviceRequest) throws Exception {
		
		String unitAddress = currentDeviceState.getUnitAddress();
		DeviceStateEntity dbDeviceState = deviceStateDao.getDeviceState(unitAddress);	
		
		if (currentDeviceState.getSatellites() == 0) {
			setDeviceNoGps(serviceRequest, currentDeviceState);
		} else {
			if (currentDeviceState.getSpeed() == 0.0) {
				setDeviceStopped(serviceRequest, currentDeviceState);
			} else {
				if (dbDeviceState != null) {
					
					boolean isMoving = isVehicleMoving(currentDeviceState, dbDeviceState);
					
					if (isMoving) {
						setDeviceMoving(serviceRequest, currentDeviceState);
					} else {
						setDeviceStopped(serviceRequest, currentDeviceState);
					}

				} else {
					// if no present in the db, assumed it is moving
					setDeviceMoving(serviceRequest, currentDeviceState);
					dbDeviceState = new DeviceStateEntity();
					dbDeviceState.setUnitAddress(unitAddress);
				}
			}
		}
		if (dbDeviceState != null) {
			upsertDeviceState(currentDeviceState, dbDeviceState);
		}
	}

	public boolean isVehicleMoving(DeviceStateEntity currentDeviceState, DeviceStateEntity dbDeviceState) {
		
		Double currentLat = currentDeviceState.getLat();
		Double currentLng = currentDeviceState.getLng();

		Double dbLat = dbDeviceState.getLat();
		Double dbLng = dbDeviceState.getLng();
		
		// Setting minimum distance as 100 feet (30.48 meters) as indicated in default device config documentation
		String distanceThrConfig = systemConfiguration
			.getConfigElement(MIDLINK, MIDLINK_DIST_THRESHOLD, "30.48");
		
		double distanceThr = Double.valueOf(distanceThrConfig);
		double deviceDistance = MessageUtil.distanceInMeters(currentLat, currentLng, dbLat, dbLng);
		// a short move is anything less than the threshold
		boolean shortMove = deviceDistance <= distanceThr;
		
		boolean compareTimeWindow = true;
		if (currentDeviceState.getLastActivityTs() == null ||
				dbDeviceState.getLastActivityTs() == null) {
			compareTimeWindow = false;
		}
		
		boolean givenEnoughTime = false;
		if (compareTimeWindow) {
			// three minutes in milliseconds as default
			String timeWindowThrConfig = systemConfiguration
				.getConfigElement(MIDLINK, MIDLINK_TIME_THRESHOLD, "180000");
			
			long timeWindowThr = Long.valueOf(timeWindowThrConfig);
			long currentTs = currentDeviceState.getLastActivityTs().getTime();;
			long dbTs = dbDeviceState.getLastActivityTs().getTime();
			// by device documentation three minutes are enough  
			givenEnoughTime = Math.abs(currentTs - dbTs) >= timeWindowThr;
		}
		
		boolean isMoving = true;
		// Enter no move: if it moved less than 100 feet for a period of at least 3 minutes
		if (shortMove && givenEnoughTime) {
			isMoving = false;
		}
		return isMoving;
	}

	public void loadAndUpsertDeviceState(DeviceStateEntity currentDeviceState) throws Exception {
		DeviceStateEntity dbDeviceState = deviceStateDao.getDeviceState(currentDeviceState.getUnitAddress());	
		if (dbDeviceState == null) {
			dbDeviceState = new DeviceStateEntity();
		}
		upsertDeviceState(currentDeviceState, dbDeviceState);
	}
	
	public void upsertDeviceState(DeviceStateEntity currentDeviceState, DeviceStateEntity dbDeviceState) {
		
		try {
			dbDeviceState.setJsonDetails(currentDeviceState.getJsonDetails());
			dbDeviceState.setLat(currentDeviceState.getLat());
			dbDeviceState.setLng(currentDeviceState.getLng());
			dbDeviceState.setSpeed(currentDeviceState.getSpeed());
			dbDeviceState.setState(currentDeviceState.getState());
			dbDeviceState.setLastActivityTs(currentDeviceState.getLastActivityTs());
			
			deviceStateDao.saveDeviceState(dbDeviceState);
			
		} catch (Exception e) {
			logger.logError("Unexpected exception upserting DeviceState", e);
		}
	}

	public void saveCurrentDeviceState(DeviceStateEntity currentDeviceState) {
		
		try {
			
			deviceStateDao.saveDeviceState(currentDeviceState);
			
		} catch (Exception e) {
			logger.logError("Unexpected exception saveCurrentDeviceState", e);
		}
	}

	public void setDeviceState(DeviceMessageAttribute serviceRequest, DeviceStateEntity deviceState, String state) {
		serviceRequest.put(TRK_STATE, state);
		if (deviceState != null) {
			deviceState.setState(state);
		}
	}

	public void setDeviceMoving(DeviceMessageAttribute serviceRequest, DeviceStateEntity deviceState) {
		serviceRequest.put(TRK_STATE, DeviceState.MOVING.name());
		if (deviceState != null) {
			deviceState.setState(DeviceState.MOVING.name());
		}
	}

	public void setDeviceTripStarted(DeviceMessageAttribute serviceRequest, DeviceStateEntity deviceState) {
		serviceRequest.put(TRK_STATE, DeviceState.TRIP_STARTED.name());
		if (deviceState != null) {
			deviceState.setState(DeviceState.TRIP_STARTED.name());
		}
	}

	public void setDeviceTripEnded(DeviceMessageAttribute serviceRequest, DeviceStateEntity deviceState) {
		serviceRequest.put(TRK_STATE, DeviceState.TRIP_ENDED.name());
		if (deviceState != null) {
			deviceState.setState(DeviceState.TRIP_ENDED.name());
		}
	}

	public void setDeviceStopped(DeviceMessageAttribute serviceRequest, DeviceStateEntity deviceState) {
		serviceRequest.put(TRK_STATE, DeviceState.STOPPED.name());
		if (deviceState != null) {
			deviceState.setState(DeviceState.STOPPED.name());
		}
	}

	public void setDeviceNoGps(DeviceMessageAttribute serviceRequest, DeviceStateEntity deviceState) {
		serviceRequest.put(TRK_STATE, DeviceState.NO_GPS.name());
		if (deviceState != null) {
			deviceState.setState(DeviceState.NO_GPS.name());
		}
	}

	public void setDeviceMovingAndUpdate(DeviceMessageAttribute serviceRequest, 
			DeviceStateEntity deviceState) throws Exception {
		serviceRequest.put(TRK_STATE, DeviceState.MOVING.name());
		if (deviceState != null) {
			deviceState.setState(DeviceState.MOVING.name());
			deviceStateDao.saveDeviceState(deviceState);
		}
		
	}

	public void setDeviceStoppedAndUpdate(DeviceMessageAttribute serviceRequest, 
			DeviceStateEntity deviceState) throws Exception {
		serviceRequest.put(TRK_STATE, DeviceState.STOPPED.name());
		if (deviceState != null) {
			deviceState.setState(DeviceState.STOPPED.name());
			deviceStateDao.saveDeviceState(deviceState);
		}
	}

	public void setDeviceStateDao(DeviceStateDao deviceStateDao) {
		this.deviceStateDao = deviceStateDao;
	}

	public void setSystemConfiguration(SystemConfiguration systemConfiguration) {
		this.systemConfiguration = systemConfiguration;
	}
}
