package com.brtracker.shared.payload.controller.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.brtracker.shared.payload.controller.ServiceAuth;

@Entity
@Table(name="affiliate")
public class Affiliate extends ServiceAuth implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private boolean active;
	private boolean billingEnabled;
	private String uniqueKey;
	private Date dateCreated;
	private String locale = "en_US";
	private String language_key = "en";
	private String region_key = "US";
	private String attributes;
	@SuppressWarnings("unused")
	private Timestamp lastUpdated;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="name", nullable=false, length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="active", nullable=false)
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Column(name="billing_enabled", nullable=false)
	public boolean isBillingEnabled() {
		return billingEnabled;
	}
	public void setBillingEnabled(boolean billingEnabled) {
		this.billingEnabled = billingEnabled;
	}
	@Column(name="date_created", nullable=false)
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Column(name="attibutes",  length=1000)
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
	
	@Column(name="uniqueKey")
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	

	@Column(name="language_key", nullable=false)
	public String getLanguage_key() {
		return language_key;
	}
	public void setLanguage_key(String language_key) {
		this.language_key = language_key;
	}
	@Column(name="region_key", nullable=false)
	public String getRegion_key() {
		return region_key;
	}
	public void setRegion_key(String region_key) {
		this.region_key = region_key;
	}
	@Column(name="locale", nullable=false)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getAttributes());
		b.append(this.getLanguage_key());
		b.append(this.getLocale());
		b.append(this.getName());
		b.append(this.getRegion_key());
		b.append(this.getId());
		return b.toString();
	}
}
