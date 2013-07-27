package com.brtracker.shared.payload.scheduler;

import java.util.List;
import com.brtracker.shared.payload.scheduler.data.JobSchedule;


public class JobScheduleResponse {

	private List<JobSchedule> jobScheduleResponse;

	public JobScheduleResponse () {}
	public JobScheduleResponse(List<JobSchedule> jobScheduleResponse) {
		this.jobScheduleResponse = jobScheduleResponse;
	}

	public List<JobSchedule> getJobScheduleResponse() {
		return jobScheduleResponse;
	}

	public void setJobScheduleResponse(List<JobSchedule> jobScheduleResponse) {
		this.jobScheduleResponse = jobScheduleResponse;
	}

	
	
	
}
