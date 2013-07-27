package com.brtracker.shared.payload.reporting;

import java.util.List;

import com.brtracker.shared.payload.controller.ConfigurationOverride;
import com.brtracker.shared.payload.reporting.lookup.ReportType;


public class ReportRequest extends AbstractSearchCriteria  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startDate;
	private String endDate;
	private ReportType reportType;
	private List<ConfigurationOverride> overrides;
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public ReportType getReportType() {
		return reportType;
	}
	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
	public List<ConfigurationOverride> getOverrides() {
		return overrides;
	}
	public void setOverrides(List<ConfigurationOverride> overrides) {
		this.overrides = overrides;
	}
	
	
	

	
}
