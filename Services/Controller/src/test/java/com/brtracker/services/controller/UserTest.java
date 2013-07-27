package com.brtracker.services.controller;

import java.util.Date;
import com.brtracker.shared.payload.controller.AccountUserRequest;
import com.brtracker.shared.payload.controller.AccountUserResponse;
import com.brtracker.shared.payload.controller.data.User;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;

public class UserTest  extends BaseControllerTest {

	AccountUserManager userManager = new AccountUserManager();
	
	public void testUserAdd() throws JSONMapperException {
		User user = new User();
		//user.setAccountId(new Long(4));
		user.setDateCreated(new Date(System.currentTimeMillis()));
		user.setFirstName("kerry");
		user.setLastName("queen");
		user.setUsername("kerry");
		user.setPassword("kerry");
		user.setAdmin(true);
		String out = userManager.add(JSONMapper.toString(user));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}

	public void testUserUpdate() throws JSONMapperException {
		User user = new User();
		user.setUsername("d2marcelo");
		//user.setAccountId(new Long(1));
		String out = userManager.update(JSONMapper.toString(user));
		System.out.println(out);
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	
	
	public void testUserDelete() throws JSONMapperException {
		User user = new User();
		//user.setAccountId(new Long(1));
		user.setUsername("d2marcelo");
		user.setAdmin(true);
		//String out = userManager.delete(JSONMapper.toString(user));
		//ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		//super.assertTrue(resp.isReturnValue());
	}

}
