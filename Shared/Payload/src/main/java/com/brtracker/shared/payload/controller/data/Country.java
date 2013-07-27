package com.brtracker.shared.payload.controller.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brtracker.shared.payload.controller.ServiceAuth;

@Entity
@Table(name="country")
public class Country extends ServiceAuth implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String code;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="name", nullable=false, length=30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="code", nullable=false)
	public String getCode() {
		return code;
	}
	public void setCode (String code) {
		this.code = code;
	}
	
	
}
