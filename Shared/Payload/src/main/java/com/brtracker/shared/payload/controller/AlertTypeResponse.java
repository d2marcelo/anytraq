package com.brtracker.shared.payload.controller;

import java.util.List;

import com.brtracker.shared.payload.controller.data.Alert;

public class AlertTypeResponse {

	private List<Alert> alertList;

	public List<Alert> getAlertList() {
		return alertList;
	}

	public void setAlertList(List<Alert> alertList) {
		this.alertList = alertList;
	}

	
	
}
