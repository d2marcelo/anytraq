package com.brtracker.shared.payload.controller;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.controller.data.Account;

public class AccountResponse  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Account> accountList;

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	
	
	
}
