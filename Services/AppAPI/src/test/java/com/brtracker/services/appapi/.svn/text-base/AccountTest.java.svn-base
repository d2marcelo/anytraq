package com.brtracker.services.appapi;

import java.util.Date;

import com.brtracker.services.appapi.endpoint.AccountService;
import com.brtracker.shared.payload.controller.data.Account;
import com.brtracker.shared.payload.controller.lookup.EntityType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.SingleObjectResponse;

public class AccountTest extends BaseAppAPITest {

	AccountService service = new AccountService();
	
	public void testAccountAdd() throws JSONMapperException{
		Account acc = new Account();
		String out = service.add(JSONMapper.toString(acc));
		System.out.println(out);
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertEquals(true, resp.isReturnValue());
		
		
	}
	
	public void testAccountUpdate () throws JSONMapperException {
		Account acc = new Account();
		String out = service.update(JSONMapper.toString(acc));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertEquals(true, resp.isReturnValue());
	}
	
	public void testAccountGet () throws JSONMapperException{
		String out = service.get("ABCD");
		SingleObjectResponse  resp = (SingleObjectResponse) JSONMapper.toObject(out, SingleObjectResponse.class);
		super.assertTrue(resp.isReturnValue());
	}
	
	public void testAccountDelete () throws JSONMapperException {
		Account acc = new Account();
		String out = service.delete(JSONMapper.toString(acc));
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(out, ServiceResponse.class);
		super.assertEquals(true, resp.isReturnValue());
	}
	
	
}
