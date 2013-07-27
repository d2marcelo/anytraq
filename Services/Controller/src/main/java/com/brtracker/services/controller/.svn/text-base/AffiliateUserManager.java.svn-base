package com.brtracker.services.controller;

import java.util.Date;
import java.util.List;
import com.brtracker.services.controller.dao.AffiliateUserDAOImpl;
import com.brtracker.services.controller.dao.UserDAO;
import com.brtracker.shared.payload.controller.AffiliateUserRequest;
import com.brtracker.shared.payload.controller.AffiliateUserResponse;
import com.brtracker.shared.payload.controller.LoginRequest;
import com.brtracker.shared.payload.controller.LoginResponse;
import com.brtracker.shared.payload.controller.data.AffiliateUser;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class AffiliateUserManager {

	private MyLogger logger = new MyLogger(AffiliateUserManager.class);
	private UserDAO dao = new AffiliateUserDAOImpl();
	
	public String add (String payload) {
		try {
			AffiliateUser user = (AffiliateUser) JSONMapper.toObject(payload, AffiliateUser.class);
			user.setDateCreated(new Date(System.currentTimeMillis()));
			dao.add(user);
			return  ServiceResponse.getServiceResponse(true, "AffiliateUser added successfuly");
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
			AffiliateUser user = (AffiliateUser) JSONMapper.toObject(payload, AffiliateUser.class);
			Long accountId = user.getAffiliate().getId();
			if (user.getId() == null){
				if (user.getUsername() == null || accountId== null){
				  return  ServiceResponse.getServiceResponse(false, "You must send user id or accountid with username as parameters.");
				}
				else {
					AffiliateUser usr = (AffiliateUser) dao.getUser(accountId, user.getUsername());
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
			AffiliateUser user = (AffiliateUser) JSONMapper.toObject(payload, AffiliateUser.class);
			Long id = user.getId();
			Long accountId = user.getAffiliate().getId();
			if (id != null ) {
				dao.delete(dao.getUser(id));
			} else {
				String username = user.getUsername();
				if (accountId == null || accountId == null) {
				 return  ServiceResponse.getServiceResponse(false, "You must send the user accountid and username parameter.");
				} else {
					user = (AffiliateUser) dao.getUser(accountId, username);
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
			AffiliateUser user = (AffiliateUser) dao.login(login);
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
			AffiliateUserRequest userRequest = (AffiliateUserRequest) JSONMapper.toObject(payload, AffiliateUserRequest.class);
			List<AffiliateUser> user = (List<AffiliateUser>) dao.listUser(userRequest);
			for (AffiliateUser u : user) u.setPassword("XXXXXXX");
			AffiliateUserResponse response = new AffiliateUserResponse();
			response.setUserList(user);
			response.setTotalCount(dao.listTotalUserCount(userRequest));
			return JSONMapper.toString(response);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
}
