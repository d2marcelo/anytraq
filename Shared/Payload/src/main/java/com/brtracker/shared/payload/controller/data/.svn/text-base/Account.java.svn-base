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
@Table(name="account")
public class Account extends ServiceAuth implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String uniqueKey;
	private boolean deleted;
	private String attributes;
	private Date dateCreated;
	private String locale = "en_US";
	private String language_key = "en";
	private String region_key = "US";
	private Affiliate affiliate;
	@SuppressWarnings("unused")
	private Timestamp lastUpdated;
	private Long trialExpiration;
	
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
	@Column(name="attributes", length=1000)
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	@ManyToOne
    @JoinColumn(name="affiliate_id", nullable=false)
	public Affiliate getAffiliate() {
		return affiliate;
	}
	public void setAffiliate(Affiliate affiliate) {
		this.affiliate = affiliate;
	}
	
	@Column(name="unique_key")
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
	
	@Column(name="trial_expiration")
	public Long getTrialExpiration() {
		return trialExpiration;
	}
	public void setTrialExpiration(Long trialExpiration) {
		this.trialExpiration = trialExpiration;
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
		b.append(this.getAttributes());
		b.append(this.getLanguage_key());
		b.append(this.getLocale());
		b.append(this.getName());
		b.append(this.getRegion_key());
		b.append(this.getId());
		b.append(this.isDeleted());
		b.append(this.getTrialExpiration());
		if (affiliate != null)
		b.append(this.getAffiliate().toString());
		return b.toString();
	}
	
}
