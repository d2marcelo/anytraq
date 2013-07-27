package com.brtracker.services.midlink;

public class WliGpsRequest {

	private static final int MAX_WLI_PCK_SIZE = 1500;
	private static final int STX_START_WLI_PCK = 0x02;
	private static final int ETX_END_WLI_PCK = 0x03;

	private static final int APP_CLASS = 0x31;
	private static final int ESCAPE_HDR = 0xDB;
	private static final int ESCAPE_STX = 0xD2;
	private static final int ESCAPE_ETX = 0xD3;
	private static final int ESCAPE_DB = 0xDD;

	private int hostToUnitMsgSeqNumber = 0;

	public void sendApplicationMessage(int bufMessage[], int msgSize) {

		int buf[] = new int[MAX_WLI_PCK_SIZE];
		
		buf[0] = STX_START_WLI_PCK;
		buf[1] = APP_CLASS;

		// Message Sequence Number

		buf[2] = ((hostToUnitMsgSeqNumber >> 8) & 0xFF);
		buf[3] = hostToUnitMsgSeqNumber & 0xFF;
		hostToUnitMsgSeqNumber++;

		// Message length
		buf[4] = ((msgSize >> 8) & 0xFF);
		buf[5] = msgSize & 0xFF;

		// Application message checksum
		int checksum = calculateWLICheckSum(bufMessage, 0, msgSize - 1);
		buf[6] = (checksum >> 8) & 0xFF;
		buf[7] = checksum & 0xFF;

		int idx = 8;
		for (int i = 0; i < msgSize; i++) {
			switch (bufMessage[i]) {
			case STX_START_WLI_PCK:
				buf[idx++] = ESCAPE_HDR;
				buf[idx++] = ESCAPE_STX;
				break;
			case ETX_END_WLI_PCK:
				buf[idx++] = ESCAPE_HDR;
				buf[idx++] = ESCAPE_ETX;
				break;
			case ESCAPE_HDR:
				buf[idx++] = ESCAPE_HDR;
				buf[idx++] = ESCAPE_DB;
				break;
			default:
				buf[idx++] = bufMessage[i];
				break;
			}
		}
		buf[idx] = ETX_END_WLI_PCK;

		// Send the message
		for (int j = 0; j < idx + 1; j++) {
			System.out.print(getBits(buf[j]) + " ");
		}
	}

	private String getBits( int value )
    {
    int displayMask = 1 << 31;
    StringBuffer buf = new StringBuffer( 35 );
   
    for ( int c = 1; c <= 32; c++ ) 
        {
        buf.append( ( value & displayMask ) == 0 ? '0' : '1' );
        value <<= 1;
       
        if ( c % 8 == 0 )
        buf.append( ' ' );
    }
   
    return buf.toString();
}
	public int calculateWLICheckSum(int buffer[], int startIdx, int endIdx) {

		int loopCounter;
		long total = 0;
		int addVal;
		int msgSize = endIdx - startIdx + 1;

		// Add each couple of bytes as short number (network order).
		for (loopCounter = startIdx; loopCounter <= endIdx; loopCounter += 2) {
			total += (buffer[loopCounter] << 8) + buffer[loopCounter + 1];
		}

		// If we have odd bytes - add the last one
		if (msgSize % 2 != 0) {
			total += buffer[msgSize - 1] << 8;
		}

		// Make sure the result is not bigger than short (2 bytes)
		while (total > 0xFFFF) {
			addVal = (int) ((total >> 16) & 0xffff);
			total = (long) (total & 0xFFFF) + addVal;
		}
		return (int) total;
	}

	static final int GPS_REQUEST_MSG_LEN = 6;
	private static final int APP_GPS_MSG = 0xC9;

	public void sendGPSRequest() {
		int buf[] = new int[GPS_REQUEST_MSG_LEN];
		buf[0] = APP_GPS_MSG;
		buf[1] = 0x00;
		buf[2] = 0x01;
		buf[3] = 0x00;
		buf[4] = '4';
		buf[5] = 0x00;
		sendApplicationMessage(buf, GPS_REQUEST_MSG_LEN);
	}
	
	public static void main(String[] args) {
		WliGpsRequest t = new WliGpsRequest();
		t.sendGPSRequest();
	}
}
