package com.brtracker.services.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brtracker.services.controller.dao.AlertConfigurationDAOImpl;
import com.brtracker.shared.payload.controller.AlertConfigurationRequest;
import com.brtracker.shared.payload.controller.ConfigurationOverride;
import com.brtracker.shared.payload.controller.GeofenceAlertConfiguration;
import com.brtracker.shared.payload.controller.GeofenceAlertConfigurationResponse;
import com.brtracker.shared.payload.controller.GeofencePoints;
import com.brtracker.shared.payload.controller.NotificationChannel;
import com.brtracker.shared.payload.controller.NotificationConfiguration;
import com.brtracker.shared.payload.controller.SingleObjectWrapper;
import com.brtracker.shared.payload.controller.data.AlertConfiguration;
import com.brtracker.shared.payload.controller.lookup.AlertSeverityType;
import com.brtracker.shared.payload.controller.lookup.AlertType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class GeofenceAlertConfigurationManager extends AlertConfigurationDAOImpl {

	public String add(String payload) {
		try {
			GeofenceAlertConfiguration config = (GeofenceAlertConfiguration) JSONMapper.toObject(payload, GeofenceAlertConfiguration.class);
			AlertConfiguration alertConfig = new AlertConfiguration();
			alertConfig.setAlertType(getAlert(config.getAlertType().getId()));
			alertConfig.setEnabled(config.isEnabled());
			alertConfig.setLastUpdated(new Timestamp(System.currentTimeMillis()));
			alertConfig.setUser(config.getUser());
			alertConfig.setValue(JSONMapper.toString(new SingleObjectWrapper(config.getPoints())));
			if (config.getOverrides() == null)
				alertConfig.setOverrideEnabled(false);
			else
			alertConfig.setOverrideEnabled(!config.getOverrides().isEmpty());
			alertConfig.setOverrides(JSONMapper.toString(new SingleObjectWrapper(config.getOverrides())));
			alertConfig.setDisplayName(config.getDisplayName());
			alertConfig.setAlertSeverityTypeId(config.getAlertSeverityType().getId());
			alertConfig.setAccountId(config.getAccountId());
			alertConfig.setNotification(JSONMapper.toString(config.getNotificationConfiguration()));
			super.saveOrUpdate(alertConfig);
			return ServiceResponse.getServiceResponse(true,"DefaultAlertConfiguration added successfully");
		} catch (HibernateHelperException e) {
			e.printStackTrace();
			return ServiceResponseException.generateResponse(e);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}

	public String update(String payload) {
		try {
			GeofenceAlertConfiguration config = (GeofenceAlertConfiguration) JSONMapper.toObject(payload, GeofenceAlertConfiguration.class);
			AlertConfiguration alertConfig = super.getAlertConfiguration(config.getId());
			alertConfig.setAlertType(getAlert(config.getAlertType().getId()));
			alertConfig.setEnabled(config.isEnabled());
			alertConfig.setLastUpdated(new Timestamp(System.currentTimeMillis()));
			alertConfig.setValue(JSONMapper.toString(new SingleObjectWrapper(config.getPoints())));
			alertConfig.setOverrideEnabled(!config.getOverrides().isEmpty());
			alertConfig.setOverrides(JSONMapper.toString(new SingleObjectWrapper(config.getOverrides())));
			alertConfig.setDisplayName(config.getDisplayName());
			alertConfig.setAlertSeverityTypeId(config.getAlertSeverityType().getId());
			alertConfig.setAccountId(config.getAccountId());
			alertConfig.setNotification(JSONMapper.toString(config.getNotificationConfiguration()));
			super.saveOrUpdate(alertConfig);
			return ServiceResponse.getServiceResponse(true,"DefaultAlertConfiguration updated successfully");
		} catch (HibernateHelperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}

	public String delete(String payload) {
		try {
			GeofenceAlertConfiguration config = (GeofenceAlertConfiguration) JSONMapper.toObject(payload, GeofenceAlertConfiguration.class);
			AlertConfiguration alertConfig = super.getAlertConfiguration(config.getId());
			super.delete(alertConfig);
			return ServiceResponse.getServiceResponse(true,"DefaultAlertConfiguration updated successfully");
		} catch (HibernateHelperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}

	public String list(String payload) {
		try {
			AlertConfigurationRequest config = (AlertConfigurationRequest) JSONMapper.toObject(payload, AlertConfigurationRequest.class);
			List<AlertConfiguration> alertConfig = super.listAlertConfiguration(config);
			GeofenceAlertConfigurationResponse resp = new GeofenceAlertConfigurationResponse();
			List<GeofenceAlertConfiguration> listResp = new ArrayList<GeofenceAlertConfiguration>();
			for (AlertConfiguration ac: alertConfig){
				GeofenceAlertConfiguration geofenceAlert = new GeofenceAlertConfiguration();
				geofenceAlert.setAccountId(ac.getAccountId());
				geofenceAlert.setAlertSeverityType(AlertSeverityType.getAlertSeverityType(ac.getAlertSeverityTypeId()));
				geofenceAlert.setAlertType(AlertType.getAlertType(ac.getAlertType().getAlertTypeId()));
				geofenceAlert.setDisplayName(ac.getDisplayName());
				geofenceAlert.setMessageKey(ac.getAlertType().getDefaultMessageKey());
				geofenceAlert.setEnabled(ac.isEnabled());
				geofenceAlert.setNameKey(ac.getAlertType().getNameKey());
				geofenceAlert.setUser(ac.getUser());
				SingleObjectWrapper points = (SingleObjectWrapper) JSONMapper.toObject(ac.getValue(), SingleObjectWrapper.class);
				geofenceAlert.setPoints((List<GeofencePoints>) points.getList());
				geofenceAlert.setId(ac.getId());
				SingleObjectWrapper wrapper = (SingleObjectWrapper) JSONMapper.toObject(ac.getOverrides(), SingleObjectWrapper.class);
				geofenceAlert.setOverrides((List<ConfigurationOverride>) wrapper.getList());
				List<NotificationConfiguration> notificationConfig = new ArrayList<NotificationConfiguration>();
				Map<String, Object> notification = (Map<String, Object>) JSONMapper.toObject(ac.getNotification(), Map.class);
				geofenceAlert.setNotificationConfiguration(notification);
				listResp.add(geofenceAlert);
			}
			resp.setGeofenceAlertConfigurationList(listResp);
			return JSONMapper.toString(resp);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
}
