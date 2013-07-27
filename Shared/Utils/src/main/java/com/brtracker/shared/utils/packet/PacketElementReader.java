package com.brtracker.shared.utils.packet;

import java.util.Map;

/**
 *	Encapsulates the read logic of a given element primitive or user defined.
 *  Primitive readers include: byte, int, string, etc.
 *  User Defined readers include: Gps extended binary information, time32, etc
 */
public interface PacketElementReader {
	
	public Object readPacketElement(PacketReaderContext context, Map<String, ?> e);
}
