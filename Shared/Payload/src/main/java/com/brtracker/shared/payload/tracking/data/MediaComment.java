package com.brtracker.shared.payload.tracking.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="media_comment")
public class MediaComment {
	
	private Long id;
	private Long userId;
	private String author;
	private Long deviceHistoryId;
	private Long mediaId;
	private String comment;
	private Long dateCreated;
	private Long lastUpdated;
	private Date systemTime;
	private boolean deleted;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="user_id" , nullable=false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name="author" , nullable=false)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column(name="device_history_id" , nullable=false)
	public Long getDeviceHistoryId() {
		return deviceHistoryId;
	}
	public void setDeviceHistoryId(Long deviceHistoryId) {
		this.deviceHistoryId = deviceHistoryId;
	}
	
	@Column(name="media_id" , nullable=false)
	public Long getMediaId() {
		return mediaId;
	}
	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}
	
	@Column(name="comment" , nullable=false, length=4000)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name="date_created" , nullable=false)
	public Long getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Column(name="last_updated" , nullable=false)
	public Long getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	@Column(name="system_time" , nullable=false)
	public Date getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}
	@Column(name="is_deleted" , nullable=false)
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
		b.append(this.getAuthor());
		b.append(this.getComment());
		b.append(this.getMediaId());
		b.append(this.getDateCreated());
		b.append(this.getDeviceHistoryId());
		b.append(this.getLastUpdated());
		b.append(this.getSystemTime());
		b.append(this.getUserId());
		return b.toString();
	}
}
