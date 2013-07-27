package com.brtracker.services.netty.server;

public class NettyMessageProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NettyMessageProcessingException() {
		super();
	}

	public NettyMessageProcessingException(String message, Throwable t) {
		super(message, t);
	}

	public NettyMessageProcessingException(String message) {
		super(message);
	}

	public NettyMessageProcessingException(Throwable t) {
		super(t);
	}

}
