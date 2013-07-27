package com.brtracker.services.controller;

import java.util.Date;

import com.brtracker.shared.payload.controller.AccountResponse;
import com.brtracker.shared.payload.controller.data.Account;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.SingleObjectResponse;

public class AccountTest extends BaseControllerTest {

	AccountManager account = new AccountManager();
	
	public void testAccountAdd() throws JSONMapperException{
		Account acc = new Account();
		String out = account.add(JSONMapper.toString(acc));
		System.out.println(out);
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertEquals(true, resp.isReturnValue());
	}
	
	public void testAccountUpdate () throws JSONMapperException {
		Account acc = new Account();
		String out = account.update(JSONMapper.toString(acc));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertEquals(true, resp.isReturnValue());
	}
	
	public void testAccountGet () throws JSONMapperException{
		String out = account.get("ABCD");
		SingleObjectResponse  resp = (SingleObjectResponse) JSONMapper.toObject(out, SingleObjectResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	public void testAccountDelete () throws JSONMapperException {
		Account acc = new Account();
		String out = account.delete(JSONMapper.toString(acc));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertEquals(true, resp.isReturnValue());
	}
	
	
}
