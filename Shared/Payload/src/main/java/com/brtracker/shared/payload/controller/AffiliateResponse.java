package com.brtracker.shared.payload.controller;

import java.io.Serializable;
import java.util.List;
import com.brtracker.shared.payload.controller.data.Affiliate;

public class AffiliateResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Affiliate> affiliateList;
	
	public List<Affiliate> getAffiliateList() {
		return affiliateList;
	}
	public void setAffiliateList(List<Affiliate> affiliateList) {
		this.affiliateList = affiliateList;
	}

	

	
	
	
}
