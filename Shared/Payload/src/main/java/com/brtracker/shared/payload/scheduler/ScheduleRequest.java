package com.brtracker.shared.payload.scheduler;



public class ScheduleRequest  {

	private Schedule schedule;
	private String callback;
	private ServiceCallType callbackType;
	private String payload;
	private String userId;
	
	
	public ScheduleRequest(){};
	/**
	 * @param jobId
	 * @param schedule
	 * @param callback
	 * @param callbackType
	 * @param payload
	 */
	public ScheduleRequest(String jobId, Schedule schedule, String callback, ServiceCallType callbackType, String payload) {
		this.schedule = schedule;
		this.callback = callback;
		this.callbackType = callbackType;
		this.payload = payload;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public ServiceCallType getCallbackType() {
		return callbackType;
	}
	public void setCallbackType(ServiceCallType callbackType) {
		this.callbackType = callbackType;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
