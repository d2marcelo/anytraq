package com.brtracker.shared.payload.controller.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_user")
public class AccountUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account account;
	private Boolean acceptedTermsOfService;
	
	@Column(name="has_accepted_terms_of_service", nullable=false)
	public Boolean getAcceptedTermsOfService() {
		return acceptedTermsOfService;
	}
	public void setAcceptedTermsOfService(Boolean acceptedTermsOfService) {
		this.acceptedTermsOfService = acceptedTermsOfService;
	}
	public boolean hasAcceptedTermsOfService () {
		if (acceptedTermsOfService == null) return false;
		return acceptedTermsOfService;
	}
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
		b.append(super.toString());
		b.append(this.getAcceptedTermsOfService());
		Account account = this.getAccount();
		if (account != null)
		b.append(account.toString());
		return b.toString();
	}
}
