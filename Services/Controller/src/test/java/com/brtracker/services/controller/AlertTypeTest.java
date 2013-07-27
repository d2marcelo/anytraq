package com.brtracker.services.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.brtracker.services.controller.dao.AlertConfigurationDAOImpl;
import com.brtracker.shared.payload.controller.AbstractAlertConfiguration;
import com.brtracker.shared.payload.controller.DefaultAlertConfiguration;
import com.brtracker.shared.payload.controller.GeofenceAlertConfiguration;
import com.brtracker.shared.payload.controller.data.Alert;
import com.brtracker.shared.payload.controller.data.AlertConfiguration;
import com.brtracker.shared.payload.controller.data.User;
import com.brtracker.shared.payload.controller.lookup.AlertCategoryType;
import com.brtracker.shared.payload.controller.lookup.AlertSeverityType;
import com.brtracker.shared.payload.controller.lookup.AlertType;
import com.brtracker.shared.payload.tracking.data.AlertMessage;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class AlertTypeTest extends BaseControllerTest {

	
	
	public static void main(String[] args) throws JSONMapperException, HibernateHelperException, JSONException {
		
		AlertConfigurationDAOImpl dao = new AlertConfigurationDAOImpl();
		
		List<AlertConfiguration> config =  (List<AlertConfiguration>) dao.find("from AlertConfiguration");
		//14087442176
		for (AlertConfiguration ac : config){
			String name = ac.getUser().getFirstName();
			Map<String,Object> map = (Map<String, Object>) JSONMapper.toObject(ac.getNotification(), Map.class);
			for (Map.Entry<String, Object> m : map.entrySet()){
				System.out.println(m.getValue());
				List<String> list =  (List<String>) m.getValue();
				System.out.println(list.get(0));
			}
		//System.out.println(ac.getNotification());
		}
		System.exit(1);
		
		
		/*
		DefaultAlertConfiguration d = new DefaultAlertConfiguration();
		d.setValue("Asdfasdf");
		d.setAlertType(AlertType.DEVICE_BATTERY);
		String g = JSONMapper.toString(d);
		AlertType type = AlertType.valueOf(JSONMapper.getString(g, "alertType"));
		System.out.println(type);
		System.exit(1);
		AbstractAlertConfiguration a = (AbstractAlertConfiguration) JSONMapper.toObject(g, AbstractAlertConfiguration.class);
		
	System.out.println(a.getAlertType());
	System.exit(1);
		*/
		
		AlertManager am = new AlertManager();
		Alert alert = new Alert();
		alert.setAlertCategoryId(AlertCategoryType.GEOFENCING.getId());
		alert.setAlertTypeId(AlertType.GEO_FENCING_OUT.getId());
		alert.setDefaultMessageKey("ALERT.DRIVER.GEOFENCE.OUT.MESSAGE");
		alert.setDefaultSeverityType(AlertSeverityType.CRITICAL.getId());
		alert.setDescriptionKey("ALERT.DRIVER.GEOFENCE.OUT.DESCRIPTION");
		alert.setNameKey("ALERT.DRIVER.GEOFENCE.OUT.NAME");
		am.add(alert);
		
			
	}
	
	public static String getValue (String str, String key) throws JSONException {
		JSONObject json = new JSONObject(str);        
        String coolness = json.getString( key);
        return coolness;
	}

	
	


}
