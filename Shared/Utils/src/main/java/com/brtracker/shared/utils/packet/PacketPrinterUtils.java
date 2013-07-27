package com.brtracker.shared.utils.packet;

public class PacketPrinterUtils {

	public static String toHexString(short[] packet) {
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
}
