package com.anet.api;


public class CreditCard{
	public static String EXPIRY_DATE_FORMAT = "yyyy-MM";
    private String card_number;       // 13 or 16 digits. Must pass LUHN check.
    private String expiration_date;
    private String card_code;
	public CreditCard(){
		
	}
	public String getCardCode() {
		return card_code;
	}
	public void setCardCode(String card_code) {
		this.card_code = card_code;
	}
	public String getCardNumber() {
		return card_number;
	}
	public void setCardNumber(String card_number) {
		this.card_number = card_number;
	}
	public String getExpirationDate() {
		return expiration_date;
	}
	public void setExpirationDate(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	
}
	