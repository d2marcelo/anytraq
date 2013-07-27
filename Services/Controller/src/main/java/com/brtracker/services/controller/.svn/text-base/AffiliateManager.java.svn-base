package com.brtracker.services.controller;

import java.util.Date;
import java.util.List;
import com.brtracker.services.controller.dao.AffiliateDAOImpl;
import com.brtracker.shared.payload.controller.AffiliateResponse;
import com.brtracker.shared.payload.controller.data.Affiliate;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountManager.
 */
public class AffiliateManager extends AffiliateDAOImpl {

	
	/**
	 * List.
	 *
	 * @return the string
	 */
	public String list (){
		try {
			List<Affiliate> account = super.listAffiliate();
			AffiliateResponse response = new AffiliateResponse();
			response.setAffiliateList(account);
			return JSONMapper.toString(response);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	/**
	 * Adds the.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String add (String payload) {
		try {
			Affiliate affiliate = (Affiliate) JSONMapper.toObject(payload, Affiliate.class);
			affiliate.setDateCreated(new Date(System.currentTimeMillis()));
			add(affiliate);
			return ServiceResponse.getSingleObjectResponse(true, "Affiliated added successfuly ", JSONMapper.toString(affiliate));
			} catch (JSONMapperException e) {
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	/**
	 * Update.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String update (String payload){
		try {
			Affiliate affiliate = (Affiliate) JSONMapper.toObject(payload, Affiliate.class);
			update(affiliate);
			return  ServiceResponse.getServiceResponse(true, "Affiliate updated successfuly");
			} catch (JSONMapperException e) {
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	/**
	 * Delete.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String delete (String payload) {
		try {
			Affiliate affiliate = (Affiliate) JSONMapper.toObject(payload, Affiliate.class);
			Long id = affiliate.getId();
			if (id != null) {
				delete(getAffiliate(id));	
			if (affiliate != null)
			delete(affiliate);
			}
			return  ServiceResponse.getServiceResponse(true, "Affiliate deleted successfuly");
			} catch (JSONMapperException e) {
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	/**
	 * List services.
	 *
	 * @param code the code
	 * @return the string
	 */
	public String get (String code){
		try {
			Affiliate affiliate = getAffiliate(code);
			if (affiliate == null){
				return ServiceResponse.getSingleObjectResponse(false, "Affiliate Not Found", null);
			} else {
				return ServiceResponse.getSingleObjectResponse(true, "Affiliate Found", JSONMapper.toString(affiliate));
			}
			} catch (JSONMapperException e) {
				return ServiceResponseException.generateResponse(e);
			} 
	}
	
}
