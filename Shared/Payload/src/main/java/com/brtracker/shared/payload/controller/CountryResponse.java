package com.brtracker.shared.payload.controller;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.controller.data.Country;

public class CountryResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Country> countryList;

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
	
	
	
}
