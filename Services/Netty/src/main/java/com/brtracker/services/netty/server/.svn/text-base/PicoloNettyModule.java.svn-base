package com.brtracker.services.netty.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.brtracker.shared.utils.logging.MyLogger;

public class PicoloNettyModule implements NettyModule {

	private MyLogger logger = new MyLogger(PicoloNettyModule.class);
	
	private int port;
	private MessageEventProcessor eventProcessor;
	private ChannelGroup allChannels = new DefaultChannelGroup("NettyModuleChannels");
	private ServerBootstrap bootstrap; 
	
	public void start() {
		
    	logger.logInfo("Creating NIO connection:" );
    	
    	bootstrap = new ServerBootstrap(
    			new NioServerSocketChannelFactory(
    					Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
    	
        logger.logInfo("Setting pipelinefactory." );
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() {
                return Channels.pipeline(new MessageChannelHandler(port, eventProcessor));
            }
        });
        
        bootstrap.setOption("child.keepAlive", true);
        
        logger.logInfo("Listening on port " + port);
        Channel channel = bootstrap.bind(new InetSocketAddress(port));
        allChannels.add(channel);
	}

	public void shutdown() {
		logger.logInfo("Shutting down Picolo Netty Module");
		ChannelGroupFuture future = allChannels.close();
		future.awaitUninterruptibly();
		bootstrap.releaseExternalResources();
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setEventProcessor(MessageEventProcessor eventProcessor) {
		this.eventProcessor = eventProcessor;
	}

}
