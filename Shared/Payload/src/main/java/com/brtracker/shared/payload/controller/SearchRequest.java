package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class SearchRequest extends AbstractSearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchFor;
	private Long accountId;
	
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
	

	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getSearchFor());
		b.append(this.getAccountId());
		b.append(super.toString());
		return b.toString();
	}
}
	