package com.brtracker.services.netty.server;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;

public interface MessageEventProcessor {

	public void processMessage(MessageEvent messageEvent, int port);

	public void handleException(ChannelHandlerContext ctx, ExceptionEvent e);
}
