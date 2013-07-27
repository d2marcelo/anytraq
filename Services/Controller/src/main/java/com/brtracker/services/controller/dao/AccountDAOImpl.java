package com.brtracker.services.controller.dao;

import java.util.List;
import com.brtracker.shared.payload.controller.data.Account;
import com.brtracker.shared.utils.spring.Context;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountDAOImpl.
 */
public class AccountDAOImpl extends ControllerDAOImpl {
	
	/**
	 * Instantiates a new account dao impl.
	 */
	public AccountDAOImpl () {
		super.setSessionFactory(Context.getSessionFactory());
	}
	
	public Account getAccount (String code){
		return (Account) super.findUniqueResult("from Account where code='"+code+"'");
	}
	
	public Account getAccount (Long id){
		return (Account) super.findUniqueResult("from Account where id="+id);
	}

	public boolean isAvailable (String code){
		Account account = getAccount(code);
		return account == null;
	}
	/**
	 * List.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Account> listAccount (String affiliateId) {
		return (List<Account>) super.find("from Account where affiliate.id="+affiliateId);
		
	}
	
	
	
}
