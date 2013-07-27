package com.brtracker.shared.payload.reporting;

import java.io.Serializable;

import com.brtracker.shared.payload.controller.ServiceAuth;

public abstract class AbstractSearchCriteria extends ServiceAuth implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderCriteria order;
	private int fetchSize = 100;
	private int startIndex = 0;
	private boolean pagingEnable=true;
	private FilterCriteria filterCriteria;
	
	public OrderCriteria getOrder() {
		return order;
	}
	public void setOrder(OrderCriteria order) {
		this.order = order;
	}
	public int getFetchSize() {
		return fetchSize;
	}
	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public boolean isPagingEnable() {
		return pagingEnable;
	}
	public void setPagingEnable(boolean pagingEnable) {
		this.pagingEnable = pagingEnable;
	}
	
	public FilterCriteria getFilterCriteria() {
		return filterCriteria;
	}
	public void setFilterCriteria(FilterCriteria filterCriteria) {
		this.filterCriteria = filterCriteria;
	}
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getFetchSize());
		b.append(this.getStartIndex());
		b.append(this.getOrder());
		return b.toString();
	}
	
}
