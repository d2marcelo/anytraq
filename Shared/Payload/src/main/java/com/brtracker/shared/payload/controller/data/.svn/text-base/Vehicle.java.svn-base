package com.brtracker.shared.payload.controller.data;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name="vehicle")
public class Vehicle extends ServiceAuth implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private int type;
	private Account account;
	private String uniqueKey;
	private String attributes;
	private Date dateCreated;
	private boolean deleted;
	
	@SuppressWarnings("unused")
	private Timestamp lastUpdated;
	
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
    @JoinColumn(name="account_id", nullable=false)
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Column(name="type", nullable=false)
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	@Column(name="date_created", nullable=false)
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Column(name="last_updated", nullable=false)
	public Timestamp getLastUpdated() {
		return new Timestamp(new Date().getTime());
	}
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	@Column(name="attributes", length=4000)
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	@Column(name="unique_key")
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	@Column(name="is_deleted")
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean delete) {
		this.deleted = delete;
	}
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getAttributes());
		b.append(this.getType());
		b.append(this.isDeleted());
		Account acc = this.getAccount();
		if (acc != null) 
		b.append(acc.toString());
		b.append(this.getId());
		return b.toString();
	}
	
}
