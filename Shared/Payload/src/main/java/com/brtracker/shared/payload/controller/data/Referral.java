package com.brtracker.shared.payload.controller.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.brtracker.shared.payload.controller.ServiceAuth;

@Entity
@Table(name="referral")
public class Referral extends ServiceAuth implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private Long accountId;
	private String message;
	private boolean fulfilled;
	private Long lastUpdated;
	private Date systemTime;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="email", nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="account_id", nullable=false)
	public Long getAccount() {
		return accountId;
	}
	public void setAccount(Long accountId) {
		this.accountId = accountId;
	}
	@Column(name="message", length=4000)
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Column(name="is_fulfilled")
	public boolean isFulfilled() {
		return fulfilled;
	}
	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}
	@Column(name="last_updated", nullable=false)
	public Long getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	@Column(name="system_time", nullable=false)
	public Date getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getEmail());
		b.append(this.getKey());
		b.append(this.getMessage());
		b.append(this.getId());
		b.append(this.getLastUpdated());
		b.append(this.getSystemTime());
		b.append(this.isFulfilled());
		if (accountId != null)
		b.append(this.getAccount().toString());
		return b.toString();
	}
	
}
