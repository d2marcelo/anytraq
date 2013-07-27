package com.brtracker.services.controller.dao;

import java.util.List;
import com.brtracker.shared.payload.controller.data.Affiliate;
import com.brtracker.shared.utils.spring.Context;

// TODO: Auto-generated Javadoc
/**
 * The Class AffiliateDAOImpl.
 */
public class AffiliateDAOImpl extends ControllerDAOImpl {
	
	/**
	 * Instantiates a new Affiliate dao impl.
	 */
	public AffiliateDAOImpl () {
		super.setSessionFactory(Context.getSessionFactory());
	}
	
	public Affiliate getAffiliate (String code){
		return (Affiliate) super.findUniqueResult("from Affiliate where code='"+code+"'");
	}
	
	public Affiliate getAffiliate (Long id){
		return (Affiliate) super.findUniqueResult("from Affiliate where id="+id);
	}

	public boolean isAvailable (String code){
		Affiliate Affiliate = getAffiliate(code);
		return Affiliate == null;
	}
	/**
	 * List.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Affiliate> listAffiliate () {
		return (List<Affiliate>) super.find("from Affiliate");
		
	}
	
	
	
}
