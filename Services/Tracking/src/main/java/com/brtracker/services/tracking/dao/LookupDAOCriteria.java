package com.brtracker.services.tracking.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;
import com.brtracker.shared.payload.reporting.OrderCriteria;
import com.brtracker.shared.payload.reporting.OrderType;


// TODO: Auto-generated Javadoc
/**
 * The Class LookupDAOCriteria.
 */
public class LookupDAOCriteria   {

	/**
	 * Sets the abstract search criteria.
	 *
	 * @param search the search
	 * @param criteria the criteria
	 */
	public static void setAbstractSearchCriteria(AbstractSearchCriteria search, Criteria criteria){
		criteria.setFirstResult(search.getStartIndex());
		criteria.setMaxResults(search.getFetchSize());
		OrderCriteria order = search.getOrder();
		if (order != null){
		OrderType orderType = order.getOrder();
		String propertyName = order.getPropertyName();
		if (orderType != null  && propertyName != null){
			if (orderType.equals(OrderType.asc))
				criteria.addOrder(Order.asc(propertyName));
			else 
				criteria.addOrder(Order.desc(propertyName));
		}
		}
	}
	
	public static long getRowCount(Criteria criteria){
		  return (Long) criteria.setProjection(Projections.rowCount()).list().get(0);
	}
	
	
}
