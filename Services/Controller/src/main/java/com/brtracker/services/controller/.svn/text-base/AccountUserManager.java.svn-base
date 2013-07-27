package com.brtracker.services.controller;

import java.util.Date;
import java.util.List;
import com.brtracker.services.controller.dao.AccountUserDAOImpl;
import com.brtracker.services.controller.dao.UserDAO;
import com.brtracker.shared.payload.controller.AccountUserRequest;
import com.brtracker.shared.payload.controller.AccountUserResponse;
import com.brtracker.shared.payload.controller.LoginRequest;
import com.brtracker.shared.payload.controller.LoginResponse;
import com.brtracker.shared.payload.controller.data.AccountUser;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;


public class AccountUserManager  {
/*
	void test (){
	User usw = this.get(user.getId());
	usw.setAccount(user.getAccount());
	usw.setAdmin(user.isAdmin());
	usw.setFirstName(user.getFirstName());
	usw.setLastName(user.getLastName());
	if (!user.getPassword().equals("") && user.getPassword() != null)
	usw.setPassword(user.getPassword());
	usw.setUsername(user.getUsername());
	usw.setPhotoURL(user.getPhotoURL());
	}
	*/
	
	private MyLogger logger = new MyLogger(AccountUserManager.class);
	private UserDAO dao = new AccountUserDAOImpl();
	
	public String add (String payload) {
		try {
			AccountUser user = (AccountUser) JSONMapper.toObject(payload, AccountUser.class);
			user.setDateCreated(new Date(System.currentTimeMillis()));
			dao.add(user);
			return  ServiceResponse.getServiceResponse(true, "User added successfuly");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	/**
	 * Update.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String update (String payload) {
		try 
		{
			AccountUser user = (AccountUser) JSONMapper.toObject(payload, AccountUser.class);
			Long accountId = user.getAccount().getId();
			if (user.getId() == null){
				if (user.getUsername() == null || accountId== null){
				  return  ServiceResponse.getServiceResponse(false, "You must send user id or accountid with username as parameters.");
				}
				else {
					AccountUser usr = (AccountUser) dao.getUser(accountId, user.getUsername());
					user.setId(usr.getId());
				}
			}
			
			if (dao.isLastAdmin(accountId) && !user.isAdmin()){
				return  ServiceResponse.getServiceResponse(false, "There must exist at least one administrator");
			}
				dao.update(user);
				return  ServiceResponse.getServiceResponse(true, "User Updated successfuly");
			 } catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
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
			AccountUser user = (AccountUser) JSONMapper.toObject(payload, AccountUser.class);
			Long id = user.getId();
			Long accountId = user.getAccount().getId();
			if (id != null ) {
				dao.delete(dao.getUser(id));
			} else {
				String username = user.getUsername();
				if (accountId == null || accountId == null) {
				 return  ServiceResponse.getServiceResponse(false, "You must send the user accountid and username parameter.");
				} else {
					user = (AccountUser) dao.getUser(accountId, username);
					if (user != null)
						dao.delete(user);
				}
			}
			return  ServiceResponse.getServiceResponse(true, "User deleted successfully");
			} catch (JSONMapperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			} catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				return ServiceResponseException.generateResponse(e);
			}
	}
	
	public String login (String payload) throws JSONMapperException {
		LoginResponse response = new LoginResponse();
		try {
			LoginRequest login = (LoginRequest) JSONMapper.toObject(payload, LoginRequest.class);
			AccountUser user = (AccountUser) dao.login(login);
			user.setPassword("XXXXXX");
			response.setUser(user);
			response.setLogin(true);
			response.setMessage("success.");
			return  JSONMapper.toString(response);
			}catch (HibernateHelperException e) {
				logger.logError(e.getMessage(), e);
				response.setLogin(true);
				response.setMessage(e.getMessage());
				return JSONMapper.toString(response);
			}
	}
	
	public String list(String payload){
		
		try {
			AccountUserRequest userRequest = (AccountUserRequest) JSONMapper.toObject(payload, AccountUserRequest.class);
			List<AccountUser> user = (List<AccountUser>) dao.listUser(userRequest);
			System.out.println(user);
			for (AccountUser u : user) {
				System.out.println(u.getFirstName());
				u.setPassword("XXXXXXX");
			}
			AccountUserResponse response = new AccountUserResponse();
			response.setUserList(user);
			response.setTotalCount(dao.listTotalUserCount(userRequest));
			return JSONMapper.toString(response);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
}