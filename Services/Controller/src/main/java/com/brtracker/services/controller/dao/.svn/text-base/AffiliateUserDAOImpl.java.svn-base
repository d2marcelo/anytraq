package com.brtracker.services.controller.dao;

import java.util.List;
import org.hibernate.Criteria;

import com.brtracker.shared.payload.controller.AffiliateUserRequest;
import com.brtracker.shared.payload.controller.LoginRequest;
import com.brtracker.shared.payload.controller.data.AffiliateUser;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class AffiliateUserDAOImpl extends ControllerDAOImpl implements UserDAO {

	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the user
	 * @throws HibernateHelperException the hibernate helper exception
	 */
	public AffiliateUser get (Long id) throws HibernateHelperException {
		List<AffiliateUser> user = (List<AffiliateUser>) find("from AffiliateUser where id="+id);
		if (user.isEmpty()) return null;
		return user.get(0);
	}
	
	public AffiliateUser getUser (Long affiliateId, String username) {
		return (AffiliateUser) super.findUniqueResult("from AffiliateUser where username='"+username+"' and affiliate.id="+affiliateId);
	}
	
	public AffiliateUser getUser (Long id) {
		return (AffiliateUser) super.findUniqueResult("from AffiliateUser where id="+id);
	}
	public AffiliateUser login (LoginRequest login) throws HibernateHelperException {
		return (AffiliateUser) super.findUniqueResult("from AffiliateUser where username='"+login.getUsername()+"' and password='"+login.getPassword()+"'");
	}
	
	public boolean isLastAdmin (Long id){
		List<AffiliateUser> user = (List<AffiliateUser>) find("from AffiliateUser where affiliate.id="+id + " and admin="+true);
		return (user.size() <= 1); 
	}
	/**
	 * Update.
	 *
	 * @param user the user
	 * @throws HibernateHelperException the hibernate helper exception
	 */
	
	public long listTotalUserCount(Object ur) {
		AffiliateUserRequest userRequest = (AffiliateUserRequest) ur;
		Criteria criteria = createCriteria(AffiliateUser.class);
		LookupDAOCriteria.setAffiliateUserCriteria(userRequest, criteria);
		return LookupDAOCriteria.getRowCount(criteria);
	}
	
	public void update (Object u) throws HibernateHelperException {
		AffiliateUser user = (AffiliateUser) u;
		AffiliateUser usw = this.get(user.getId());
		usw.setAffiliate(user.getAffiliate());
		usw.setAdmin(user.isAdmin());
		usw.setFirstName(user.getFirstName());
		usw.setLastName(user.getLastName());
		if (!user.getPassword().equals("") && user.getPassword() != null)
		usw.setPassword(user.getPassword());
		usw.setUsername(user.getUsername());
		usw.setPhotoURL(user.getPhotoURL());
		super.saveOrUpdate(user);
	}
	
	public List<AffiliateUser> listUser(Object ur) {
		AffiliateUserRequest userRequest = (AffiliateUserRequest) ur;
		Criteria criteria = createCriteria(AffiliateUser.class);
		LookupDAOCriteria.setAbstractSearchCriteria(userRequest, criteria);
		LookupDAOCriteria.setAffiliateUserCriteria(userRequest, criteria);
		List<AffiliateUser> user = (List<AffiliateUser>) criteria.list();
		return user;
	}
	


}
