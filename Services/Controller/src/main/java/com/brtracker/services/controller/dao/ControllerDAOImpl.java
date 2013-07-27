package com.brtracker.services.controller.dao;

import com.brtracker.shared.utils.spring.Context;
import com.brtracker.shared.utils.spring.HibernateHelper;
import com.brtracker.shared.utils.spring.HibernateHelperException;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerDAOImpl.
 */
public class ControllerDAOImpl extends HibernateHelper {

	
	/**
	 * Instantiates a new controller dao impl.
	 */
	public ControllerDAOImpl() {
		super.setSessionFactory(Context.getSessionFactory());
	}

	/**
	 * Adds the.
	 *
	 * @param object the object
	 * @throws HibernateHelperException the hibernate helper exception
	 */
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
