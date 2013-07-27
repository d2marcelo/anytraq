package com.brtracker.services.tracking.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.brtracker.shared.payload.tracking.data.DriverBehavior;
import com.brtracker.shared.utils.json.JSONMapperException;

public class DriverBehaviorDAOImpl extends TrackingDAOImpl {
	
	
	public List<DriverBehavior> getDriverBehavior (Long driverId, Date date) throws JSONMapperException {
		Criteria criteria = createCriteria(DriverBehavior.class);
		criteria.add(Restrictions.eq("driverId", driverId));
		criteria.add(Restrictions.ge("dateCreated", date));
		return criteria.list();
	}
	
	
}
