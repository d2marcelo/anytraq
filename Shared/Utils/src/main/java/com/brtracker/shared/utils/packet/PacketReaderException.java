package com.brtracker.shared.utils.packet;

public class PacketReaderException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private PacketReaderContext context;
	
	public PacketReaderContext getContext() {
		return context;
	}

	public PacketReaderException() {
		super();
	}

	public PacketReaderException(String message, Throwable e) {
		super(message, e);
	}

	public PacketReaderException(String message) {
		super(message);
	}

	public PacketReaderException(Throwable e) {
		super(e);
	}

	public PacketReaderException(String message, Throwable e, PacketReaderContext context) {
		super(message, e);
		this.context = context;
	}

	public PacketReaderException(String message, PacketReaderContext context) {
		super(message);
		this.context = context;
	}

	public PacketReaderException(Throwable e, PacketReaderContext context) {
		super(e);
		this.context = context;
	}

}
