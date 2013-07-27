package com.brtracker.shared.payload.controller;

import java.util.List;

import com.brtracker.shared.payload.controller.data.Referral;

public class ReferralResponse {
 
	List<Referral> referralList;

	public List<Referral> getReferralList() {
		return referralList;
	}

	public void setReferralList(List<Referral> referralList) {
		this.referralList = referralList;
	}
	
	
	
}
