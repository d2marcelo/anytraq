package com.brtracker.services.controller.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.brtracker.shared.payload.controller.AlertConfigurationRequest;
import com.brtracker.shared.payload.controller.data.Alert;
import com.brtracker.shared.payload.controller.data.AlertConfiguration;

public class AlertConfigurationDAOImpl  extends ControllerDAOImpl  {

	
	public AlertConfiguration getAlertConfiguration (Long id) {
		List<AlertConfiguration> list = (List<AlertConfiguration>) find("from AlertConfiguration where id="+id);
		if (list.isEmpty()) return null;
		return list.get(0);
	}
	
	public List<AlertConfiguration> listAlertConfiguration (AlertConfigurationRequest alertConfigRequest) {
		Criteria criteria = createCriteria(AlertConfiguration.class);
		LookupDAOCriteria.setAbstractSearchCriteria(alertConfigRequest, criteria);
		LookupDAOCriteria.setAlertConfigurationCriteria(alertConfigRequest, criteria);
		return criteria.list();
	}
	
	public long listAlertConfigurationTotal (AlertConfigurationRequest alertConfigRequest) {
		Criteria criteria = createCriteria(AlertConfiguration.class);
		LookupDAOCriteria.setAlertConfigurationCriteria(alertConfigRequest, criteria);
		return LookupDAOCriteria.getRowCount(criteria);
	}
	
	public List<AlertConfiguration> listTotalAlertConfiguration (AlertConfigurationRequest alertConfigRequest) {
		Criteria criteria = createCriteria(AlertConfiguration.class);
		return criteria.list();
	}
	
	public Alert getAlert(int id){
		List<Alert> list = (List<Alert>) find("from Alert where alertTypeId="+id);
		if (list.isEmpty()) return null;
		return list.get(0);
	}
}
