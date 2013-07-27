package com.anet.api;
public class Message{
	
	private String result_code;
	private String code;
	private String text;
	
	public Message(){
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResultCode() {
		return result_code;
	}

	public void setResultCode(String result_code) {
		this.result_code = result_code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}