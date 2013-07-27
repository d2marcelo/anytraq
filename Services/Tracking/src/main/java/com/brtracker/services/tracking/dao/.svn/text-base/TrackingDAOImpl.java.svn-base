package com.brtracker.services.tracking.dao;

import com.brtracker.shared.utils.spring.Context;
import com.brtracker.shared.utils.spring.HibernateHelper;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public abstract class TrackingDAOImpl extends HibernateHelper implements TrackingDAO {
	
	
	public TrackingDAOImpl () {
		super.setSessionFactory(Context.getSessionFactory());
	}
	
	public void add (Object object) throws HibernateHelperException {
		super.saveOrUpdate(object);
	}
	
	public void delete (Object object) throws HibernateHelperException {
		super.delete(object);
	}
	
	public void update (Object object) throws HibernateHelperException {
		super.saveOrUpdate(object);
	}


}
