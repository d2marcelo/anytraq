package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.data.Device;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class DeviceRequest extends AbstractSearchCriteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Device device;
	private Long affiliateId;

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Long getAffiliateId() {
		return affiliateId;
	}

	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}
	
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		if (device != null)
		b.append(this.getDevice().toString());
		b.append(this.getAffiliateId());
		b.append(super.toString());
		return b.toString();
	}
	
	

}
