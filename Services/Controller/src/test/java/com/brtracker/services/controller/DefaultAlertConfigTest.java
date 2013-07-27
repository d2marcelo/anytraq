package com.brtracker.services.controller;

import com.brtracker.shared.payload.controller.AlertConfigurationRequest;
import com.brtracker.shared.payload.controller.DefaultAlertConfiguration;
import com.brtracker.shared.payload.controller.lookup.AlertSeverityType;
import com.brtracker.shared.payload.controller.lookup.AlertType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class DefaultAlertConfigTest extends BaseControllerTest {
	
	DefaultAlertConfigurationManager manager = new DefaultAlertConfigurationManager();
	
	public void testAlertAdd () throws HibernateHelperException, JSONMapperException {
		DefaultAlertConfiguration alert = new DefaultAlertConfiguration();
		alert.setAccountId(new Long(1));
		alert.setAlertSeverityType(AlertSeverityType.CRITICAL);
		alert.setAlertType(AlertType.DEVICE_BATTERY);
		alert.setDisplayName("device battery");
		alert.setEnabled(true);
		alert.setValue("true");
		System.out.println(manager.add(JSONMapper.toString(alert)));
	}
	
	public void testAlertList () throws HibernateHelperException, JSONMapperException {
		AlertConfigurationRequest alert = new AlertConfigurationRequest();
		alert.setAccountId(new Long(1));
		System.out.println(manager.list(JSONMapper.toString(alert)));
	}

}
