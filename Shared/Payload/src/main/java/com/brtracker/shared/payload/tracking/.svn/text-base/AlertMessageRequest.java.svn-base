package com.brtracker.shared.payload.tracking;

import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;
import com.brtracker.shared.payload.tracking.data.AlertMessage;

public class AlertMessageRequest extends AbstractSearchCriteria {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private AlertMessage alertMessage;

	public AlertMessage getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(AlertMessage alertMessage) {
		this.alertMessage = alertMessage;
	}
	

	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		if (alertMessage != null)
		b.append(this.getAlertMessage().toString());
		b.append(super.toString());
		return b.toString();
	}
	
}
