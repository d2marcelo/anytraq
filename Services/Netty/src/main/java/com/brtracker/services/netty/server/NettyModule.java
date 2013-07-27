package com.brtracker.services.netty.server;

public interface NettyModule {

	public void start();
	public void shutdown();
	public int getPort();
	public void setPort(int port);
	
}
