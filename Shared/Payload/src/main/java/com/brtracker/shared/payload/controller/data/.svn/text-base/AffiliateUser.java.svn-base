package com.brtracker.shared.payload.controller.data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="affiliate_user")
public class AffiliateUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Affiliate affiliate;
	
	@ManyToOne
    @JoinColumn(name="affiliate_id", nullable=false)
	public Affiliate getAffiliate() {
		return affiliate;
	}
	public void setAffiliate(Affiliate affiliate) {
		this.affiliate = affiliate;
	}
	
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(super.toString());
		Affiliate affiliate = this.getAffiliate();
		if (affiliate != null)
		b.append(affiliate.toString());
		return b.toString();
	}
}
