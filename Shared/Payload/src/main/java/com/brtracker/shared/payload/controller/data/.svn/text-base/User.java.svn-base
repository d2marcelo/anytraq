package com.brtracker.shared.payload.controller.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.hibernate.search.annotations.Indexed;


@Indexed
@MappedSuperclass
public class User extends Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean admin;
	
	
	@Column(name="user_name", nullable=false, unique=true, length=50)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password", nullable=false, length=100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="is_admin")
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getUsername());
		b.append(this.getPassword());
		b.append(super.toString());
		return b.toString();
	}
	
}
