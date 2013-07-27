package com.brtracker.services.controller.dao;

import java.util.List;
import org.hibernate.Criteria;
import com.brtracker.shared.payload.controller.LoginRequest;
import com.brtracker.shared.payload.controller.AccountUserRequest;
import com.brtracker.shared.payload.controller.data.AccountUser;
import com.brtracker.shared.utils.spring.HibernateHelperException;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAOImpl.
 */
public class AccountUserDAOImpl extends ControllerDAOImpl implements UserDAO {
	
	
	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the user
	 * @throws HibernateHelperException the hibernate helper exception
	 */
	public AccountUser get (Long id) throws HibernateHelperException {
		List<AccountUser> user = (List<AccountUser>) find("from AccountUser where id="+id);
		if (user.isEmpty()) return null;
		return user.get(0);
	}
	
	public AccountUser getUser (Long accountId, String username) {
		return (AccountUser) super.findUniqueResult("from AccountUser where username='"+username+"' and account.id="+accountId);
	}
	
	public AccountUser getUser (Long id) {
		return (AccountUser) super.findUniqueResult("from AccountUser where id="+id);
	}
	public AccountUser login (LoginRequest login) throws HibernateHelperException {
		return (AccountUser) super.findUniqueResult("from AccountUser where  username='"+login.getUsername()+"' and password='"+login.getPassword()+"'");
	}
	
	public boolean isLastAdmin (Long id){
		List<AccountUser> user = (List<AccountUser>) find("from AccountUser where account.id="+id + " and admin="+true);
		return (user.size() <= 1); 
	}
	/**
	 * Update.
	 *
	 * @param user the user
	 * @throws HibernateHelperException the hibernate helper exception
	 */
	
	public long listTotalUserCount(Object ur) {
		AccountUserRequest userRequest = (AccountUserRequest) ur;
		Criteria criteria = createCriteria(AccountUser.class);
		LookupDAOCriteria.setAccountUserCriteria(userRequest, criteria);
		return LookupDAOCriteria.getRowCount(criteria);
	}
	
	public void update (Object u) throws HibernateHelperException {
		AccountUser user = (AccountUser) u;
		AccountUser usw = this.get(user.getId());
		usw.setAccount(user.getAccount());
		usw.setAdmin(user.isAdmin());
		usw.setFirstName(user.getFirstName());
		usw.setLastName(user.getLastName());
		if (!user.getPassword().equals("") && user.getPassword() != null)
		usw.setPassword(user.getPassword());
		usw.setUsername(user.getUsername());
		usw.setPhotoURL(user.getPhotoURL());
		super.saveOrUpdate(user);
	}
	@Override
	public List<?> listUser(Object ur) {
		AccountUserRequest userRequest = (AccountUserRequest) ur;
		Criteria criteria = createCriteria(AccountUser.class);
		LookupDAOCriteria.setAbstractSearchCriteria(userRequest, criteria);
		LookupDAOCriteria.setAccountUserCriteria(userRequest, criteria);
		List<AccountUser> user = (List<AccountUser>) criteria.list();
		
		return user;
	}

	


}
