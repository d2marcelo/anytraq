package com.brtracker.shared.utils.packet;

public class InvalidSchemaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidSchemaException() {
		super();
	}

	public InvalidSchemaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidSchemaException(String arg0) {
		super(arg0);
	}

	public InvalidSchemaException(Throwable arg0) {
		super(arg0);
	}

}
