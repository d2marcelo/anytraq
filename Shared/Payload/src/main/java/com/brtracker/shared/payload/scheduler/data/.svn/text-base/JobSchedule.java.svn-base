package com.brtracker.shared.payload.scheduler.data;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="job_schedule")
public class JobSchedule {
	
	private Long id;
	private String name;
	private boolean active;
	private String expression;
	private String schedule_type;
	private Date start_date;
	private String payload;
	private String userId;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="active")
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Column(name="expression")
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@Column(name="schedule_type")
	public String getSchedule_type() {
		return schedule_type;
	}
	public void setSchedule_type(String scheduleType) {
		schedule_type = scheduleType;
	}
	@Column(name="start_date")
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date startDate) {
		start_date = startDate;
	}
	@Column(name="payload")
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
