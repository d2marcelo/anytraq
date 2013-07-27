package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class DriverSearchRequest  extends AbstractSearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchFor;
	private Long accountId;
	private boolean mappedDrivers;
	
	public String getSearchFor() {
		return searchFor;
	}
	public void setSearchFor(String searchFor) {
		this.searchFor = searchFor;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public boolean isMappedDrivers() {
		return mappedDrivers;
	}
	public void setMappedDrivers(boolean mappedDrivers) {
		this.mappedDrivers = mappedDrivers;
	}

	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getSearchFor());
		b.append(this.getAccountId());
		b.append(this.isMappedDrivers());
		b.append(super.toString());
		return b.toString();
	}
	
	
	
	
}
