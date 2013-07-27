package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.data.AccountUser;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class AccountUserRequest extends AbstractSearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountUser user;

	public AccountUser getUser() {
		return user;
	}

	public void setUser(AccountUser user) {
		this.user = user;
	}
	
	
	
}
