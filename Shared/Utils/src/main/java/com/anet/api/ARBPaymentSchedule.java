package com.anet.api;


public class ARBPaymentSchedule{
	public static String SCHEDULE_DATE_FORMAT = "yyyy-MM-dd";
	
	private int interval_length = 0;
	private String subscription_unit = "days"; // days | months
	private String start_date = null;
	private int total_occurrences = 0;
	private int trial_occurrences = 0;

	public ARBPaymentSchedule(){
		
	}

	

	public int getInterval_length() {
		return interval_length;
	}



	public void setInterval_length(int interval_length) {
		this.interval_length = interval_length;
	}



	public String getStartDate() {
		return start_date;
	}
	
	public void setStartDate(String start_date) {
		this.start_date = start_date;
	}

	public String getSubscriptionUnit() {
		return subscription_unit;
	}

	public void setSubscriptionUnit(String subscription_unit) {
		this.subscription_unit = subscription_unit;
	}

	public int getTotalOccurrences() {
		return total_occurrences;
	}

	public void setTotalOccurrences(int total_occurrences) {
		this.total_occurrences = total_occurrences;
	}

	public int getTrialOccurrences() {
		return trial_occurrences;
	}

	public void setTrialOccurrences(int trial_occurrences) {
		this.trial_occurrences = trial_occurrences;
	}
}