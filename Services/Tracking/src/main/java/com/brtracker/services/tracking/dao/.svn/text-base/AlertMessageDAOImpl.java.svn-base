package com.brtracker.services.tracking.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.brtracker.shared.payload.tracking.AlertMessageRequest;
import com.brtracker.shared.payload.tracking.data.AlertMessage;

public class AlertMessageDAOImpl extends TrackingDAOImpl {
	
	
	public List<AlertMessage> listAlertMessage(AlertMessageRequest request){
		Criteria criteria = createCriteria(AlertMessage.class);
		LookupDAOCriteria.setAbstractSearchCriteria(request, criteria);
		AlertMessage msg = (request.getAlertMessage() == null) ? new AlertMessage() : request.getAlertMessage();
		Long accountId = msg.getAccountId(); 
		Boolean read = msg.isRead();
		Long userId = msg.getUserId();
		Boolean cleared = msg.isCleared();
		int alertTypeId = msg.getAlertTypeId();
		Long alertConfigId = msg.getAlertConfigId();
		int severityId = msg.getAlertSeverityId();
		Boolean acknowledged = msg.isAcknowledged();
		Long driverId = msg.getDriverId();
		
		if (userId != null) criteria.add(Restrictions.eq("userId", userId));
		if (driverId != null) criteria.add(Restrictions.eq("driverId", driverId));
		if (alertConfigId != null) criteria.add(Restrictions.eq("alertConfigId", alertConfigId));
		if (alertTypeId != 0) criteria.add(Restrictions.eq("alertTypeId", alertTypeId));
		if (accountId != null) criteria.add(Restrictions.eq("accountId", accountId));
		if (severityId != 0) criteria.add(Restrictions.eq("alertSeverityId", severityId));
		if (read != null) criteria.add(Restrictions.eq("read", read));
		if (cleared != null) criteria.add(Restrictions.eq("cleared", cleared));
		if (acknowledged != null) criteria.add(Restrictions.eq("acknowledged", acknowledged));
		return criteria.list();
	}
	
	public AlertMessage getAlertMessage(Long id){
		List<AlertMessage> list =(List<AlertMessage>) find("from AlertMessage where id="+id);
		if (list.isEmpty())return null;
		return list.get(0);
	}
	
	public AlertMessage getAlertMessage(String id){
		List<AlertMessage> list =(List<AlertMessage>) find("from AlertMessage where id="+id);
		if (list.isEmpty())return null;
		return list.get(0);
	}
	
	public long listAlertMessageTotal (AlertMessageRequest request){
		Criteria criteria = createCriteria(AlertMessage.class);
		AlertMessage msg = (request.getAlertMessage() == null) ? new AlertMessage() : request.getAlertMessage();
		Long accountId = msg.getAccountId(); 
		Boolean read = msg.isRead();
		int severityId = msg.getAlertSeverityId();
		Boolean acknowledged = msg.isAcknowledged();
		if (accountId != null) criteria.add(Restrictions.eq("accountId", accountId));
		if (severityId != 0) criteria.add(Restrictions.eq("alertSeverityId", severityId));
		if (read != null) criteria.add(Restrictions.eq("read", read));
		if (acknowledged != null) criteria.add(Restrictions.eq("acknowledged", acknowledged));
		return criteria.list().size();
	}

}
