package com.brtracker.services.tracking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.brtracker.services.tracking.dao.AlertMessageDAOImpl;
import com.brtracker.shared.payload.controller.lookup.AlertSeverityType;
import com.brtracker.shared.payload.controller.lookup.AlertType;
import com.brtracker.shared.payload.tracking.AlertMessageRequest;
import com.brtracker.shared.payload.tracking.data.AlertMessage;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class AlertMessageTest {

	public static void main(String[] args) throws HibernateHelperException, JSONMapperException {
		
		AlertMessageRequest r= new AlertMessageRequest();
		AlertMessageManager m = new AlertMessageManager();
		AlertMessage a = new AlertMessage();
		a.setAccountId(new Long(1));
		//a.setId(new Long(1));
		a.setAcknowledged(false);
		a.setAcknUserId(null);
		a.setAlertSeverityId(AlertSeverityType.INFORMATIONAL.getId());
		a.setAlertTypeId(AlertType.DRIVER_QUICK_ACCEL.getId());
		a.setDriverId(new Long(4));
		a.setDateCreated(new Date(System.currentTimeMillis()));
		a.setMessageKey("ALERT.DRIVER.QUICKACCEL.MESSAGE");
		List<String> list = new ArrayList<String>();
		list.add("Marcelo Oliveira");
		list.add("-18.808504");
		list.add("-49.08444");
		a.setPlaceHolders(JSONMapper.toString(list));
		a.setRead(false);
		a.setVehicleId(new Long(6));
		r.setAlertMessage(a);
		System.out.println(JSONMapper.toString(r));
		//m.add(a);
		//System.out.println(m.update(JSONMapper.toString(a)));
		//System.out.println(m.list(JSONMapper.toString(r)));
		
	}
	
}
