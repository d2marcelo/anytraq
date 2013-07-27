package com.brtracker.shared.payload.controller.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import com.brtracker.shared.payload.controller.ServiceAuth;

@Indexed
@MappedSuperclass
public class Person extends ServiceAuth implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String firstName;
	private String lastName;
	private String photoURL;
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
	
	@Field(index=Index.TOKENIZED, store=Store.NO)
	@Column(name="first_name", nullable=false, length=20)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Field(index=Index.TOKENIZED, store=Store.NO)
	@Column(name="last_name", nullable=false, length=20)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	@Column(name="unique_key", nullable=false)
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	@Column(name="photoURL")
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
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
		b.append(this.getFirstName());
		b.append(this.getLastName());
		b.append(this.getPhotoURL());
		b.append(this.getUniqueKey());
		b.append(this.getId());
		return b.toString();
	}
	
}
