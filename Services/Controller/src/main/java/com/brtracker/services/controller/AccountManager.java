package com.brtracker.services.controller;

import java.util.Date;
import java.util.List;
import com.brtracker.services.controller.dao.AccountDAOImpl;
import com.brtracker.shared.payload.controller.AccountResponse;
import com.brtracker.shared.payload.controller.data.Account;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountManager.
 */
public class AccountManager extends AccountDAOImpl {

	
	/**
	 * List.
	 *
	 * @return the string
	 */
	public String list (String uniqueKey){
		try {
			List<Account> account = super.listAccount(uniqueKey);
			AccountResponse response = new AccountResponse();
			response.setAccountList(account);
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
			Account account = (Account) JSONMapper.toObject(payload, Account.class);
			account.setDateCreated(new Date(System.currentTimeMillis()));
			add(account);
			return ServiceResponse.getSingleObjectResponse(true, "Account added successfuly ", JSONMapper.toString(account));
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
			Account account = (Account) JSONMapper.toObject(payload, Account.class);
			update(account);
			return  ServiceResponse.getServiceResponse(true, "Account updated successfuly");
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
			Account account = (Account) JSONMapper.toObject(payload, Account.class);
			Long id = account.getId();
			if (id != null) {
				delete(getAccount(id));	
			if (account != null)
			delete(account);
			}
			return  ServiceResponse.getServiceResponse(true, "Account deleted successfuly");
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
	public String get (String uniqueKey){
		try {
			Account account = getAccount(uniqueKey);
			if (account == null){
				return ServiceResponse.getSingleObjectResponse(false, "Account Not Found", null);
			} else {
				return ServiceResponse.getSingleObjectResponse(true, "Account Found", JSONMapper.toString(account));
			}
			} catch (JSONMapperException e) {
				return ServiceResponseException.generateResponse(e);
			} 
	}
	
}
