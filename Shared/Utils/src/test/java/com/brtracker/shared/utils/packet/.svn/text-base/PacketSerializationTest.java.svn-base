package com.brtracker.shared.utils.packet;

import java.util.Map;

public class PacketSerializationTest extends BasePacketReaderTest {

	public void testMessageSerialization() {

		try {
			
			TrackingMessage tmOriginal = packetReader.readPacket(packet);
			
			// serialize to a text file
			tmOriginal.serialize("sample-msg.json");
			
			// serialize to a string
			String json = tmOriginal.serialize();
			
			DefaultTrackingMessage tm2 = new DefaultTrackingMessage();
			tm2.deserialize(json);

			assertEquals("Invalid message name", 
					"PicoloGpsInfoMessage", tm2.getMessageType());

			assertEquals("Invalid unitId", 
					"045092047146", tm2.getProperty(String.class, "unitId"));
			
			assertTrue("Missing gpsInfo element", 
					tm2.getProperty(Map.class, "appMessage").containsKey("gpsInfo"));
			
			assertEquals("Invalid message length", 
					"110", tm2.getProperty(String.class, "appMessage.messageLength"));
			
			assertEquals("Invalid unit serial number", 
					Float.valueOf("702424.0"), tm2.getProperty(Float.class, "appMessage.gpsInfo.unitSerialNumber"));

		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Unexpected exception", false);
		}
	
	}

	@Override
	public short[] getPacket() {
		return packetFixture.getApplicationDataPacket();
	}

}
