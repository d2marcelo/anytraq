package com.brtracker.services.midlink.processing.command;

public class CommandProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String code;

	public CommandProcessingException() {
	}

	public CommandProcessingException(String code, String message) {
		super(message);
		this.code = code;
	}

	public CommandProcessingException(String message) {
		super(message);
	}

	public CommandProcessingException(Throwable cause) {
		super(cause);
	}

	public CommandProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
