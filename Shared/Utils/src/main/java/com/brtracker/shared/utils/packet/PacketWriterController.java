package com.brtracker.shared.utils.packet;

import java.util.Map;

public class PacketWriterController {
	
	private short delimiter = 0;
	
	public short[] buildHeader(Map<String, String> haders) {
		short[] packet = new short[] {};
		for (String k : haders.keySet()) {
			packet = prependHeader(k, haders.get(k), packet);
		}
		return packet;
	}
	
	public short[] prependHeader(String key, String value, short[] packet) {
		
		short[] finalPacket = new short[key.length() + value.length() + 2 + packet.length];
		
		int ke = key.length() - 1;
		int vs = key.length() + 1;
		int ve = key.length() + value.length();
		int ps = key.length() + value.length() + 2;
		
		for (int i = 0; i<key.length(); i++) {
			finalPacket[i] = (short) key.charAt(i);
		}		
		finalPacket[ke+1] = delimiter;
		
		for (int i = 0; i<value.length(); i++) {
			finalPacket[vs+i] = (short) value.charAt(i);
		}
		finalPacket[ve+1] = delimiter;
		
		for (int i = 0; i<packet.length; i++) {
			finalPacket[ps+i] = packet[i];
		}
		
		return finalPacket;
	}

	public short[] prependHeader(String key, short value, short[] packet) {
		
		short[] finalPacket = new short[key.length() + 3 + packet.length];
		
		int ke = key.length() - 1;
		int ve = key.length() + 1;
		int ps = key.length() + 3;
		
		for (int i = 0; i<key.length(); i++) {
			finalPacket[i] = (short) key.charAt(i);
		}		
		finalPacket[ke+1] = delimiter;
		finalPacket[ke+2] = value;
		finalPacket[ve+1] = delimiter;
		
		for (int i = 0; i<packet.length; i++) {
			finalPacket[ps+i] = packet[i];
		}
		
		return finalPacket;
	}
	
	public short getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(short delimiter) {
		this.delimiter = delimiter;
	}

}
