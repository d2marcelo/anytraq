package com.brtracker.services.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.brtracker.services.controller.dao.AlertConfigurationDAOImpl;
import com.brtracker.shared.payload.controller.AlertConfigurationRequest;
import com.brtracker.shared.payload.controller.ConfigurationOverride;
import com.brtracker.shared.payload.controller.DefaultAlertConfiguration;
import com.brtracker.shared.payload.controller.DefaultAlertConfigurationResponse;
import com.brtracker.shared.payload.controller.SingleObjectWrapper;
import com.brtracker.shared.payload.controller.data.AlertConfiguration;
import com.brtracker.shared.payload.controller.lookup.AlertCategoryType;
import com.brtracker.shared.payload.controller.lookup.AlertSeverityType;
import com.brtracker.shared.payload.controller.lookup.AlertType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class DefaultAlertConfigurationManager extends AlertConfigurationDAOImpl {

	public String add(String payload) {
		try {
			DefaultAlertConfiguration config = (DefaultAlertConfiguration) JSONMapper.toObject(payload, DefaultAlertConfiguration.class);
			AlertConfiguration alertConfig = new AlertConfiguration();
			alertConfig.setAlertType(getAlert(config.getAlertType().getId()));
			alertConfig.setUser(config.getUser());
			alertConfig.setEnabled(config.isEnabled());
			alertConfig.setLastUpdated(new Timestamp(System.currentTimeMillis()));
			alertConfig.setValue(config.getValue());
			if (config.getOverrides() == null)
				alertConfig.setOverrideEnabled(false);
			else
			alertConfig.setOverrideEnabled(!config.getOverrides().isEmpty());
			alertConfig.setOverrides(JSONMapper.toString(new SingleObjectWrapper(config.getOverrides())));
			alertConfig.setDisplayName(config.getDisplayName());
			alertConfig.setAlertSeverityTypeId(config.getAlertSeverityType().getId());
			alertConfig.setNotification(JSONMapper.toString(config.getNotificationConfiguration()));
			alertConfig.setAccountId(config.getAccountId());
			alertConfig.setUser(config.getUser());
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
			DefaultAlertConfiguration config = (DefaultAlertConfiguration) JSONMapper.toObject(payload, DefaultAlertConfiguration.class);
			AlertConfiguration alertConfig = super.getAlertConfiguration(config.getId());
			alertConfig.setAlertType(getAlert(config.getAlertType().getId()));
			alertConfig.setEnabled(config.isEnabled());
			alertConfig.setLastUpdated(new Timestamp(System.currentTimeMillis()));
			alertConfig.setDisplayName(config.getDisplayName());
			alertConfig.setValue(config.getValue());
			if (config.getOverrides() == null || config.getOverrides().isEmpty())
			alertConfig.setOverrideEnabled(false);
			else 
			alertConfig.setOverrideEnabled(true);	
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
			DefaultAlertConfiguration config = (DefaultAlertConfiguration) JSONMapper.toObject(payload, DefaultAlertConfiguration.class);
			AlertConfiguration alertConfig = super.getAlertConfiguration(config.getId());
			if (alertConfig == null) return ServiceResponse.getServiceResponse(true,"Record does not exist.");
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
			DefaultAlertConfigurationResponse resp = new DefaultAlertConfigurationResponse();
			List<DefaultAlertConfiguration> listResp = new ArrayList<DefaultAlertConfiguration>();
			for (AlertConfiguration ac: alertConfig){
				DefaultAlertConfiguration defaultAlert = new DefaultAlertConfiguration();
				defaultAlert.setAccountId(ac.getAccountId());
				defaultAlert.setNameKey(ac.getAlertType().getNameKey());
				defaultAlert.setId(ac.getId());
				defaultAlert.setUser(ac.getUser());
				defaultAlert.setAlertSeverityType(AlertSeverityType.getAlertSeverityType(ac.getAlertSeverityTypeId()));
				defaultAlert.setAlertType(AlertType.getAlertType(ac.getAlertType().getAlertTypeId()));
				defaultAlert.setAlertCategoryType(AlertCategoryType.getAlertCategoryType(ac.getAlertType().getAlertCategoryId()));
				defaultAlert.setDisplayName(ac.getDisplayName());
				defaultAlert.setEnabled(ac.isEnabled());
				defaultAlert.setValue(ac.getValue());
				Map<String, Object> notification = (Map<String, Object>) JSONMapper.toObject(ac.getNotification(), Map.class);
				defaultAlert.setNotificationConfiguration(notification);
				defaultAlert.setMessageKey(ac.getAlertType().getDefaultMessageKey());
				SingleObjectWrapper wrapper = (SingleObjectWrapper) JSONMapper.toObject(ac.getOverrides(), SingleObjectWrapper.class);
				defaultAlert.setOverrides((List<ConfigurationOverride>) wrapper.getList());
				listResp.add(defaultAlert);
			}
			resp.setTotalCount(super.listAlertConfigurationTotal(config));
			resp.setDefaultAlertConfiguration(listResp);
			return JSONMapper.toString(resp);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
}
