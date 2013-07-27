package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.data.AffiliateUser;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class AffiliateUserRequest extends AbstractSearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AffiliateUser user;
	
	public AffiliateUser getUser() {
		return user;
	}
	public void setUser(AffiliateUser user) {
		this.user = user;
	}

		
}
