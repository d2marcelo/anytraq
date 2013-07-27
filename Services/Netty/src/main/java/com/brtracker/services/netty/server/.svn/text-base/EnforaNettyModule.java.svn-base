package com.brtracker.services.netty.server;

import static com.brtracker.shared.utils.SystemConfiguration.NETTY;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.FixedReceiveBufferSizePredictorFactory;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.DatagramChannelFactory;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;

import com.brtracker.services.netty.DeviceConnection;
import com.brtracker.services.netty.MessageContext;
import com.brtracker.services.netty.MessageParser;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.activemq.JmsSender;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.PacketReaderController;
import com.brtracker.shared.utils.packet.PacketWriterController;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class EnforaNettyModule implements NettyModule {

	private MyLogger logger = new MyLogger(EnforaNettyModule.class);
	private int port;
	
	@SuppressWarnings("unused")
	private MessageEventProcessor eventProcessor;
	
	private ConnectionlessBootstrap bootstrap;
	
	private PacketReaderController packetReader;
	private PacketWriterController packetWriter;
	private JmsSender jmsSender;
	
	private ChannelGroup allChannels = new DefaultChannelGroup("EnforaModuleChannels");

	@Override
	public void start() {

		logger.logInfo("Creating UDP NIO connection:" );
		
		DatagramChannelFactory f = new NioDatagramChannelFactory(Executors.newCachedThreadPool());
			 
		bootstrap = new ConnectionlessBootstrap(f);
			 
		// Configure the pipeline factory.
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			 public ChannelPipeline getPipeline() throws Exception {
				 return Channels.pipeline(
						 new StringEncoder(CharsetUtil.ISO_8859_1),
						 new StringDecoder(CharsetUtil.ISO_8859_1),
						 new EnforaHandler());
			 }
		});
		// Enable broadcast
		bootstrap.setOption("broadcast", "false");
		
		bootstrap.setOption("receiveBufferSizePredictorFactory", new FixedReceiveBufferSizePredictorFactory(1024));
		
		logger.logInfo("Listening UDP on port " + port);
		Channel channel = bootstrap.bind(new InetSocketAddress(port));
		allChannels.add(channel);
	}

	@Override
	public void shutdown() {
		logger.logInfo("Shutting down Enfora Netty Module");
		ChannelGroupFuture future = allChannels.close();
		future.awaitUninterruptibly();
		bootstrap.releaseExternalResources();
	}

	@Override
	public int getPort() {
		return port;
	}

	private class EnforaHandler extends SimpleChannelUpstreamHandler {
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent messageEvent)
			throws Exception {
			try {
				Class<?> eventClass = messageEvent.getMessage().getClass();
				
				if (String.class.isAssignableFrom(eventClass)) {
					
					String strMessage = (String) messageEvent.getMessage();
					logMessage(strMessage);
					inspectIpAddress(ctx, messageEvent);
					
					MessageContext messageContext = MessageParser.readMessage(strMessage);
					messageContext.setPort(port);
					short[] unitPacket = createUnitPacket(messageContext, port);
					DeviceConnection.put(messageContext.getUnitAddress(), messageContext.getStrMessage());
					
					jmsSender.sendStreamMessage(unitPacket, NETTY);

				} else {
					logger.logInfo("Message Received of unk type : " + eventClass.getName());
				}
				
			} catch (Exception e) {
				logger.logError("Unexpected exception", e);
			}
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
			e.getCause().printStackTrace();
		}		
	}

	private short[] createUnitPacket(MessageContext messageContext, int port) {
		
		TrackingMessage tm = packetReader.readPacket(messageContext.getMessage(), port);
		
		String unitAddress = tm.getProperty(String.class, "payloadType1.mdmid").trim();
		messageContext.setUnitAddress(unitAddress);
		
		Map<String, String> headers = new HashMap<String, String>();
		// port header
		headers.put("h01", String.valueOf(port));
		// unit id
		headers.put("h02", messageContext.getUnitAddress());
		// date
		headers.put("h03", String.valueOf(DateUtil.getUTCDateAndTime().getTime()));	
		
		short[] header = packetWriter.buildHeader(headers);
		short[] message = messageContext.getMessage();
		
		short[] devicePacket = new short[header.length + message.length];
		int i = 0;
		for (short s: header) { 
			devicePacket[i++] = s;
		}
		for (short s : messageContext.getMessage()) { 
			devicePacket[i++] = s;
		}
		return devicePacket;
	}

	public void inspectIpAddress(ChannelHandlerContext ctx, MessageEvent messageEvent) {
		
		InetSocketAddress remoteAddress = (InetSocketAddress) ctx.getChannel().getRemoteAddress();
		
		if (remoteAddress == null) {
			remoteAddress = (InetSocketAddress) messageEvent.getRemoteAddress();
		}
		
		if (remoteAddress == null) {
			Channel channel = messageEvent.getChannel();
			if (channel != null) {
				remoteAddress = (InetSocketAddress) channel.getRemoteAddress();
			}
		}
		
		if (remoteAddress != null) {
			StringBuffer msg = new StringBuffer();
			if (remoteAddress.getAddress() != null) {
				msg.append(" host address " + remoteAddress.getAddress().getHostAddress());
			}
			msg.append(" " + remoteAddress.getPort() + " " + remoteAddress.getHostName());
			logger.logInfo("Device address info " + msg.toString());
		} else {
			logger.logInfo("NO Device address info ");
		}
	}
	public void logMessage(String strMessage) {
		
		byte[] bytes = strMessage.getBytes();
		
		logger.logInfo("Message Received str: length=" + 
				bytes.length + " content [" + strMessage + "]");

		logger.logInfo("Message Received hex: length=" + 
				bytes.length + " content [" + toHexString(bytes) + "]");
	}
	public static String toHexString(byte[] packet) {
		if (packet == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0; i< packet.length; i++) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append(Integer.toHexString(packet[i]));
		}
		return sb.toString();
	}

	public void setEventProcessor(MessageEventProcessor eventProcessor) {
		this.eventProcessor = eventProcessor;
	}

	public void setPacketReader(PacketReaderController packetReader) {
		this.packetReader = packetReader;
	}

	public void setPacketWriter(PacketWriterController packetWriter) {
		this.packetWriter = packetWriter;
	}

	public void setJmsSender(JmsSender jmsSender) {
		this.jmsSender = jmsSender;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
	}

}
