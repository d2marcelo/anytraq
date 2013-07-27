package com.brtracker.shared.payload.tracking;

import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class DeviceStatusRequest extends AbstractSearchCriteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long affiliateId;

	public Long getAffiliateId() {
		return affiliateId;
	}

	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}
	
	
	
	

}
