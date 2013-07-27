package com.brtracker.shared.payload.controller.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="driver")
public class Driver extends Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Account account;
	
	@ManyToOne
    @JoinColumn(name="account_id", nullable=false)
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		Account account = this.getAccount();
		if (account != null)
		b.append(account.toString());
		b.append(super.toString());
		return b.toString();
	}
	
}