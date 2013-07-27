package com.brtracker.shared.payload.scheduler;



public class Schedule implements Cloneable {

	private String name;
	private ScheduleType type;
	private String startTime;
	private String cronjobExp;
	
	public Schedule(){};
	/**
	 * @param name
	 * @param type
	 * @param startTime
	 * @param cronjobExp
	 */
	public Schedule(String name, ScheduleType type, String startTime, String cronjobExp) {
		this.name = name;
		this.type = type;
		this.startTime = startTime;
		this.cronjobExp = cronjobExp;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ScheduleType getType() {
		return type;
	}
	public void setType(ScheduleType type) {
		this.type = type;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getCronjobExp() {
		return cronjobExp;
	}
	public void setCronjobExp(String cronjobExp) {
		this.cronjobExp = cronjobExp;
	}
	
	public Object clone() throws CloneNotSupportedException {
		Schedule clone= (Schedule)super.clone();
	    return clone;

	  }

}
