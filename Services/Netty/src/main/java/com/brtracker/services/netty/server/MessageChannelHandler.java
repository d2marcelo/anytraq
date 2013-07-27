package com.brtracker.services.netty.server;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.brtracker.shared.utils.logging.MyLogger;

public class MessageChannelHandler extends SimpleChannelHandler {
	
	private MyLogger logger = new MyLogger(MessageChannelHandler.class);
	
	private MessageEventProcessor eventProcessor;
	private int port;
	
    public MessageChannelHandler(int port, MessageEventProcessor eventProcessor) {
    	this.port = port;
    	this.eventProcessor = eventProcessor;
	}

	@Override
    public void messageReceived(ChannelHandlerContext context, final MessageEvent messageEvent) {
		logger.logInfo("Receiving message");
		eventProcessor.processMessage(messageEvent, port);
		logger.logDebug("Message processed sucessfully");
	}
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
    	
    	try {
    		if (e != null && e.getCause() != null) {
    			logger.logInfo("ExceptionCaught " + e.getCause().getMessage());
    		}
    		eventProcessor.handleException(ctx, e);
    		
    	} finally {
    		if (e != null && e.getChannel() != null) {
    			Channel ch = e.getChannel();
    			ch.close();
    		}
    	}
    }

}
