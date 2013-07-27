package com.brtracker.shared.payload.controller.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.brtracker.shared.payload.controller.ServiceAuth;


@Entity
@Table(name="device")
public class Device extends ServiceAuth implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Account account;
	private String address;
	private int deviceTypeId;
	private Boolean assigned;
	private String attributes;
	private String comments;
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
	@Column(name="address",  nullable=false, unique=true, length=100)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="comments", length=100)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@ManyToOne
    @JoinColumn(name="account_id", nullable=false)
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Column(name="device_type_id" , nullable=true)
	public int getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(int deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	
	@Column(name="is_assigned" , nullable=false)
	public Boolean isAssigned() {
		return assigned;
	}
	public void setAssigned(Boolean assigned) {
		this.assigned = assigned;
	}
	
	@Column(name="date_created", nullable=false)
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Column(name="attributes", length=4000)
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	@Column(name="last_updated", nullable=false)
	public Timestamp getLastUpdated() {
		return new Timestamp(new Date().getTime());
	}
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	@Column(name="is_deleted")
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getId());
		b.append(this.getAddress());
		b.append(this.getComments());
		b.append(this.getAttributes());
		b.append(this.getDeviceTypeId());
		b.append(this.getDateCreated());
		b.append(this.isAssigned());
		b.append(this.isDeleted());
		if (account != null)
		b.append(getAccount().toString());
		return b.toString();
	}
}