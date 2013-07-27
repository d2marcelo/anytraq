package com.brtracker.shared.payload.tracking;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.reporting.AbstractSearchResponse;
import com.brtracker.shared.payload.tracking.data.ActivityReport;

public class ActivityReportResponse extends AbstractSearchResponse implements Serializable{
	
	private List<ActivityReport> activityReportList;

	public List<ActivityReport> getActivityReportList() {
		return activityReportList;
	}

	public void setActivityReportList(List<ActivityReport> activityReportList) {
		this.activityReportList = activityReportList;
	}
	
	

}
