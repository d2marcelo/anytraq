package com.brtracker.shared.utils.packet;

public class PacketToString {

	public static String getHexString(short[] packet) {
		if (packet == null || packet.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < packet.length; i++) {
			sb.append(Integer.toHexString(packet[i]) + " ");
		}
		return sb.toString();
	}

	public static String getIntHexString(int[] packet) {
		if (packet == null || packet.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < packet.length; i++) {
			sb.append(Integer.toHexString(packet[i]) + " ");
		}
		return sb.toString();
	}

	public static String getJsonArrayHexString(short[] packet) {
		if (packet == null || packet.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < packet.length; i++) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append("\"" + Integer.toHexString(packet[i]) + "\"");
		}
		return "[" + sb.toString() + "]";
	}

	public static String getIntJsonArrayHexString(int[] packet) {
		if (packet == null || packet.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < packet.length; i++) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append("\"" + Integer.toHexString(packet[i]) + "\"");
		}
		return "[" + sb.toString() + "]";
	}

	public static String getDecString(short[] packet) {
		if (packet == null || packet.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < packet.length; i++) {
			sb.append(Integer.toString(packet[i]) + " ");
		}
		return sb.toString();
	}

	public static String getHexString(int[] packet) {
		if (packet == null || packet.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < packet.length; i++) {
			sb.append(Integer.toHexString(packet[i]) + " ");
		}
		return sb.toString();
	}

	public static String getDecString(int[] packet) {
		if (packet == null || packet.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < packet.length; i++) {
			sb.append(Integer.toString(packet[i]) + " ");
		}
		return sb.toString();
	}

	public String getBitsForShort(short value) {
		int displayMask = 1 << 15;
		StringBuffer buf = new StringBuffer(35);

		for (int c = 1; c <= 16; c++) {
			buf.append((value & displayMask) == 0 ? '0' : '1');
			value <<= 1;

			if (c % 8 == 0)
				buf.append(' ');
		}

		return buf.toString();
	}

}
