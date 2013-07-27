package com.brtracker.shared.payload.controller.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message_place_holder_map")
public class MessagePlaceHolderMap {

	private Long id;
	private String messageKey;
	private int placeHolderIndex;
	private String classPath;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="message_key", nullable=false)
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	@Column(name="place_holder_index", nullable=false)
	public int getPlaceHolderIndex() {
		return placeHolderIndex;
	}
	public void setPlaceHolderIndex(int placeHolderIndex) {
		this.placeHolderIndex = placeHolderIndex;
	}
	@Column(name="class_path", nullable=false)
	public String getClassPath() {
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	
	
	
}
