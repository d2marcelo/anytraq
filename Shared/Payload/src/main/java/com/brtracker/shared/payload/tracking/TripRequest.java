package com.brtracker.shared.payload.tracking;

import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class TripRequest extends AbstractSearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	
	

}
