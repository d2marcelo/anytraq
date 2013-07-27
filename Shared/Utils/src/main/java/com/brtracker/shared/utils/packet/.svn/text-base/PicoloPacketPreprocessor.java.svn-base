package com.brtracker.shared.utils.packet;

import java.util.Arrays;
import java.util.Map;

import static com.brtracker.shared.utils.packet.PacketDefinitionConstants.*;

public class PicoloPacketPreprocessor implements PacketPreprocessor {

	public static short[] escapeData(short[] message) {
		
		short buffer[] = new short[MAX_WLI_PCK_SIZE];
		int bufIdx = 0;
		short c;
		int j= 0;
		
        do {
        	c = (short) (message[j++] & 0xFF);
        	if(c == ETX_END_WLI_PCK){
        		buffer[bufIdx++] = c;
        		break;
        	}
        	// Replace escaped characters with the original ones
        	if(c== ESCAPE_HDR) {
        		int c1 = (int) message[j++] & 0xFF;
        		switch(c1) {
	        		case ESCAPE_STX:
	        			c = STX_START_WLI_PCK;
	        			break;
	        		case ESCAPE_ETX:
	        			c = ETX_END_WLI_PCK;
	        			break;
	        		case ESCAPE_DB:
	        			c = ESCAPE_HDR;
	        			break;
        		}
        	}
        	buffer[bufIdx++] = c;
        } while(bufIdx < MAX_WLI_PCK_SIZE && j < message.length);
        
        return Arrays.copyOfRange(buffer, 0, bufIdx);
	}

	public static int[] escapeOutboundDataInt(int[] message) {
		
		int escapedMessage[] = new int[MAX_WLI_PCK_SIZE];
		int escapedIndx = 0;
		for (int i = 0; i < message.length; i++) {
			int c = message[i];
    		switch(c) {
	    		case STX_START_WLI_PCK:
	    			escapedMessage[escapedIndx++] = ESCAPE_HDR;
	    			escapedMessage[escapedIndx++] = ESCAPE_STX;
	    			break;
	    		case ETX_END_WLI_PCK:
	    			escapedMessage[escapedIndx++] = ESCAPE_HDR;
	    			escapedMessage[escapedIndx++] = ESCAPE_ETX;
	    			break;
	    		case ESCAPE_HDR:
	    			escapedMessage[escapedIndx++] = ESCAPE_HDR;
	    			escapedMessage[escapedIndx++] = ESCAPE_DB;
	    			break;
	    		default:
	    			escapedMessage[escapedIndx++] = c;
	    			break;
    		}
		}
        return Arrays.copyOfRange(escapedMessage, 0, escapedIndx);
	}

	@Override
	public void preprocess(PacketReaderContext context, Map<String, ?> e) {
		
		short[] message = context.getRawPacket();
	
		short[] escapedMessage = escapeData(message);
		
		context.setFromShortPacket(escapedMessage);
		
	}

}
