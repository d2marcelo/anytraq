package com.brtracker.services.tracking;

import java.util.List;

import com.brtracker.services.tracking.dao.AlertMessageDAOImpl;
import com.brtracker.shared.payload.tracking.AlertMessageRequest;
import com.brtracker.shared.payload.tracking.AlertMessageResponse;
import com.brtracker.shared.payload.tracking.data.AlertMessage;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class AlertMessageManager extends AlertMessageDAOImpl {

	
	private MyLogger logger = new MyLogger(AlertMessageManager.class);
	
	public String list (String payload) {
		try{
			AlertMessageRequest req = (AlertMessageRequest) JSONMapper.toObject(payload, AlertMessageRequest.class);
			AlertMessageResponse resp = new AlertMessageResponse();
			resp.setAlertMessageList(super.listAlertMessage(req));
			resp.setTotalCount(super.listAlertMessageTotal(req));
			return JSONMapper.toString(resp);
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
			return ServiceResponseException.generateResponse(e);
		} 
	}
	
	
	public String delete (String payload) {
		try{
		List<String> list = (List<String>) JSONMapper.toObject(payload, List.class);
		for (String l : list){
			AlertMessage m = getAlertMessage(l);
			if (m == null) continue;
			m.setDeleted(true);
			super.saveOrUpdate(m);
		}
		return  ServiceResponse.getServiceResponse(true, "Alert Messages deleted successfully");
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			logger.logError(e.getMessage(), e);
			return ServiceResponseException.generateResponse(e);
		}
	}
	public String update (String payload) {
		try{
			AlertMessage msg = (AlertMessage) JSONMapper.toObject(payload, AlertMessage.class);
			AlertMessage current = getAlertMessage(msg.getId());
			if (current == null) return  ServiceResponse.getServiceResponse(true, "Alert Message does not exist");
			if (msg.getAccountId() != null) current.setAccountId(msg.getAccountId());
			if (msg.isAcknowledged() != null) current.setAcknowledged(msg.isAcknowledged());
			if (msg.isCleared() != null) current.setCleared(msg.isCleared());
			if (msg.isRead() != null) current.setRead(msg.isRead());
			if (msg.getAcknUserId() != null) current.setAcknUserId(msg.getAcknUserId());
			if (msg.getAlertSeverityId() != 0) current.setAlertSeverityId(msg.getAlertSeverityId());
			if (msg.getAlertTypeId() != 0) current.setAlertTypeId(msg.getAlertTypeId());
			if (msg.getDisplayName() != null) current.setDisplayName(msg.getDisplayName());
			if (msg.getDriverId() != null) current.setDriverId(msg.getDriverId());
			if (msg.getMessageKey() != null) current.setMessageKey(msg.getMessageKey());
			if (msg.getNameKey() != null) current.setNameKey(msg.getNameKey());
			if (msg.getPlaceHolders() != null) current.setPlaceHolders(msg.getPlaceHolders());
			if (msg.getVehicleId() != null) current.setVehicleId(msg.getVehicleId());
			
			msg.setDateCreated(current.getDateCreated());
			super.saveOrUpdate(current);
			return  ServiceResponse.getServiceResponse(true, "Alert Message saved successfully");
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			logger.logError(e.getMessage(), e);
			return ServiceResponseException.generateResponse(e);
		}
	}
}
