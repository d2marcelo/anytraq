package com.brtracker.services.netty.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.MessageEvent;

import com.brtracker.services.netty.MessageContext;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.PacketReaderController;

public class TeltonikaProtocolHandler implements DeviceProtocolHandler {
	
	private MyLogger logger = new MyLogger(TeltonikaProtocolHandler.class);
	private PacketReaderController packetReader;
	
	@Override
	public void handleMessage(MessageContext messageContext,
			MessageEvent messageEvent) {

		try {
			Channel out= messageEvent.getChannel();
			byte ack = 0;
			if (messageContext.isPresentationMessage()) {
				logger.logInfo("Ack presentation Teltonika message");
				// just acknowledging the presentation message 
				ack = 1; 
				
			} else {
				short[] packet = messageContext.getMessage();
				int port = messageContext.getPort();

				Integer numDataItems = (Integer) packetReader.seekFieldValue(packet, port, "data_length");
				
				ack = numDataItems.byteValue();
				logger.logInfo("Ack Teltonika AVL packet " + ack);
				
			}

			ChannelBuffer ch = ChannelBuffers.wrappedBuffer(new byte[]{ack});
			out.write(ch);
			
		} catch (Exception e) {
			logger.logError("Unexpcted exception", e);
		} 
	}

	public void setPacketReader(PacketReaderController packetReader) {
		this.packetReader = packetReader;
	}

}
