package com.brtracker.services.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.MessageEvent;
import com.brtracker.shared.payload.netty.DeviceMessageRequest;
import com.brtracker.shared.payload.netty.WLIUSMessageRequest;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;

	public class MessageProducerHandler implements Runnable
	{
		private MyLogger logger = new MyLogger(MessageProducerHandler.class);
			   private MessageEvent messageEvent;
	   private DeviceMessageRequest request;
	    public MessageProducerHandler(MessageEvent messageEvent, DeviceMessageRequest request) {
	    	this.messageEvent = messageEvent;
	    	this.request =request;
	    }
	    
		public void run() 
	    {
			// get channel
			Channel out= messageEvent.getChannel();
			byte[] b = new byte[1500];
			WLIUSMessageRequest msg;
			try {
				// serialize string to object
				msg = (WLIUSMessageRequest) JSONMapper.toObject(request.getMessageObject(), WLIUSMessageRequest.class);
				
				int[] message = msg.getMessage();
				// convert int to bytes
				for(int i = 0; i < message.length ; i++) {
					b[i] = ((byte)message[i]);
				}
				// send the message..
				ChannelBuffer ch = ChannelBuffers.wrappedBuffer(b);
				out.write(ch);
				logger.logInfo("message sent successfuly");

			} catch (JSONMapperException e) {
				logger.logError(e.getMessage() , e);
			}
	    }
}