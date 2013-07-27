package com.brtracker.shared.payload.tracking;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.reporting.AbstractSearchResponse;
import com.brtracker.shared.payload.tracking.data.IdleReport;

public class IdleReportResponse  extends AbstractSearchResponse implements Serializable {
	
	private List<IdleReport> idleReportResponse;

	public List<IdleReport> getIdleReportResponse() {
		return idleReportResponse;
	}

	public void setIdleReportResponse(List<IdleReport> idleReportResponse) {
		this.idleReportResponse = idleReportResponse;
	}
	
	

}
