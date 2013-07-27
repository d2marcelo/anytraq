package com.brtracker.services.tracking.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractReportDAOImpl.
 */
public class AbstractReportDAOImpl extends HibernateTemplate {

	/**
	 * Instantiates a new abstract report dao impl.
	 *
	 * @param sessionFactory the session factory
	 */
	public AbstractReportDAOImpl(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	

	/**
	 * Criteria example.
	 */
	public void criteriaExample() {
		/*
		Criteria criteria = super.getSession().createCriteria(TestLevelMapper.getDAOfromTestLevel(testLevel));
		criteria.setProjection(Projections.rowCount()); 
		criteria.add(Restrictions.like(TestLevelMapper.getColumnName(testLevel), testName));
		criteria.createCriteria("execution_status").add(Restrictions.like("name",executionType.name()));
		return ((Integer)criteria.list().get(0)).intValue();
		*/
	}
	
}
