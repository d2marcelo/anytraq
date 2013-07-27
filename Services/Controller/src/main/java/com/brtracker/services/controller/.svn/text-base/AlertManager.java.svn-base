package com.brtracker.services.controller;

import com.brtracker.services.controller.dao.AlertDAOImpl;
import com.brtracker.shared.payload.controller.AlertTypeResponse;
import com.brtracker.shared.payload.controller.data.Alert;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class AlertManager extends AlertDAOImpl {

	private MyLogger logger = new MyLogger(AlertManager.class);
	public String add (String payload){
		try {
			Alert alertType = (Alert) JSONMapper.toObject(payload, Alert.class);
			super.add(alertType);
			return  ServiceResponse.getServiceResponse(true, "AlertType added successfuly");
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			logger.logError(e.getMessage(), e);
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	
	public String update (String payload){
		try {
			Alert alertType = (Alert) JSONMapper.toObject(payload, Alert.class);
			super.update(alertType);
			return  ServiceResponse.getServiceResponse(true, "AlertType updated successfuly");
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			logger.logError(e.getMessage(), e);
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	public String list (){
		try {
			AlertTypeResponse resp = new AlertTypeResponse();
			resp.setAlertList(super.listAlertType());
			return JSONMapper.toString(resp);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
}
