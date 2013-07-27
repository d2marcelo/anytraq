package com.anytraq.notification.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="notification_template", 
		uniqueConstraints= {@UniqueConstraint(columnNames={"template_key","locale","type"})})
public class NotificationTemplateEntity {

	private Long id;
	private String templateKey;
	private String locale;
	private String type;
	private String template;
	private String subject;
	// this might not work if multiple sender could be used for the same email template
	private String sender;
	private String senderName;
	private String mimeType;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="template_key", nullable=false, length=64)
	public String getTemplateKey() {
		return templateKey;
	}
	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}
	
	@Column(name="locale", nullable=false, length=16)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	@Column(name="type", nullable=false, length=32)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Lob
	@Column(name="template", nullable=true)
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	@Column(name="sender", nullable=false, unique=false)
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	@Column(name="subject", nullable=true, unique=false)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSenderName() {
		return senderName;
	}
	@Column(name="sender_name", nullable=true, unique=false)
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	@Column(name="mime_type", nullable=true, unique=false)
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	

}
