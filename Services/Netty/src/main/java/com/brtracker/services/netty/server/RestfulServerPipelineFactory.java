package com.brtracker.services.netty.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

public class RestfulServerPipelineFactory implements ChannelPipelineFactory {

	private int port;
	private MessageEventProcessor eventProcessor;
	
	public RestfulServerPipelineFactory(int port, MessageEventProcessor eventProcessor) {
		this.port = port;
		this.eventProcessor = eventProcessor;
	}
	
	public ChannelPipeline getPipeline() throws Exception {
		
		// Create a default pipeline implementation.
		ChannelPipeline pipeline = Channels.pipeline();
		
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("handler", new MessageChannelHandler(port, eventProcessor));
		return pipeline;
	}

}
