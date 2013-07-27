package com.brtracker.shared.payload.reporting;

public class OrderCriteria {

	private String propertyName;
	private OrderType order;
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public OrderType getOrder() {
		return order;
	}
	public void setOrder(OrderType order) {
		this.order = order;
	}
	
}
