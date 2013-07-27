package com.brtracker.shared.payload.tracking;


import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class AbstractReportRequest extends AbstractSearchCriteria {

	private Long deviceId;
	private Long startDate;
	private Long endDate;
	
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	
	
}
