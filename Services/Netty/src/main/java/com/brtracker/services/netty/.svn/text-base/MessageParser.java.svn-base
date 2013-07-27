package com.brtracker.services.netty;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.PacketPrinterUtils;

import edu.emory.mathcs.backport.java.util.Arrays;

public class MessageParser {

	private static MyLogger logger = new MyLogger(MessageParser.class);
	private final static int MASK =0x000000ff;
	
	public static MessageContext readMessage (String message) {
		MessageContext unitMessage = new MessageContext();
		
		byte[] messageBytes = message.getBytes();
		short buffer[] = new short[messageBytes.length];
		for (int i=0; i<messageBytes.length; i++) {
			buffer[i] = messageBytes[i];
			buffer[i] &= MASK;
		}
		
		unitMessage.setMessage(buffer);
		unitMessage.setSize(buffer.length);
		String stringMsg = PacketPrinterUtils.toHexString(unitMessage.getMessage());
		unitMessage.setStrMessage(stringMsg);
		
		return unitMessage;
	}
	
	public static MessageContext readMessage (ChannelBuffer buf) {
		MessageContext unitMessage = new MessageContext();
		short buffer[] = new short[1500];
		StringBuffer strBuffer = new StringBuffer();
		int count =  0;
		logger.logInfo("Reading Full Message.");
		while(buf.readable()) {
			buffer[count] =  buf.readUnsignedByte();
			strBuffer.append(buffer[count]);
			count++;
	    }
		unitMessage.setMessage(Arrays.copyOfRange(buffer, 0, count));
		unitMessage.setSize(count);
		String stringMsg = PacketPrinterUtils.toHexString(unitMessage.getMessage());
		unitMessage.setStrMessage(stringMsg);
		return unitMessage;
	}
	
	public static int unsignedByteToInt(byte b) {
	    return (int) b & 0xFF;
	    }
	
	public static String getStringMessage (int[] message, String unit) {
		List<String> str = new ArrayList<String>();
		str.add(unit);
		for (int i : message){
			str.add(new Integer(i).toString());
		}
		String resp =  StringUtils.join(str, ",");
		logger.logInfo("String representation of message: "+ resp);
		return resp;
	}
	
	
	
}
