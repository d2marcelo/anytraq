package com.anet.api;

public class ARBCustomer{
	private ARBNameAndAddress bill_to;
	private ARBNameAndAddress ship_to;
	private ARBDriversLicense license;
	
	// type = individual | business
	//
	private String type; 
	private String id;
	private String email;
	private String phoneNumber;
	private String faxNumber;
    private boolean driversLicenseSpecified;
    
    private String taxId;
	
	public ARBCustomer(){
		
	}

	public ARBNameAndAddress getBillTo() {
		return bill_to;
	}

	public void setBillTo(ARBNameAndAddress bill_to) {
		this.bill_to = bill_to;
	}

	public boolean isDriversLicenseSpecified() {
		return driversLicenseSpecified;
	}

	public void setDriversLicenseSpecified(boolean driversLicenseSpecified) {
		this.driversLicenseSpecified = driversLicenseSpecified;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ARBDriversLicense getLicense() {
		return license;
	}

	public void setLicense(ARBDriversLicense license) {
		this.license = license;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ARBNameAndAddress getShipTo() {
		return ship_to;
	}

	public void setShipTo(ARBNameAndAddress ship_to) {
		this.ship_to = ship_to;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}