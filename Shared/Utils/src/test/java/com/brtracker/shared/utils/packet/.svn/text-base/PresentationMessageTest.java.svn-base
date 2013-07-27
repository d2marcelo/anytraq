package com.brtracker.shared.utils.packet;

import java.util.Map;

public class PresentationMessageTest extends BasePacketReaderTest {

	private static final int PICOLO_PORT = 3001;
	
	public void testParsePresentationMessage() {

		try {
			
			TrackingMessage tm = packetReader.readPacket(packet);
			assertTrackingMessage(tm);
			
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Unexpected exception", false);
		}
	
	}

	public void testGetUnitIdentifier() {
		String unitIdentifier = packetReader.findUnitIdentifier(packet);
		assertEquals("Invalid unit id", "012123412253", unitIdentifier);
	}
	
	public void testGetUnitIdentifierWithPort() {
		String unitIdentifier = packetReader.findUnitIdentifier(packet, PICOLO_PORT);
		assertEquals("Invalid unit id", "012123412253", unitIdentifier);
	}

	public void testReadPacketWithPort() {
		try {
			
			TrackingMessage tm = packetReader.readPacket(packet, PICOLO_PORT);
			assertTrackingMessage(tm);
			
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Unexpected exception", false);
		}
	}
	
	private void assertTrackingMessage(TrackingMessage tm) {
		
		assertEquals("Invalid message name", 
				"PicoloPresentationMessage", tm.getMessageType());
		
		assertEquals("Invalid unitId", 
				"1234", tm.getProperty(String.class, "unitId"));
		
		assertTrue("Missing gpsInfo element", 
				tm.getProperty(Map.class, "presentationMessage").containsKey("unitId"));

		assertTrue("Missing gpsInfo element", 
				tm.getProperty(Map.class, "presentationMessage").containsKey("keyword"));

	}
	@Override
	public short[] getPacket() {
		return packetFixture.getPresentationPacket();
	}

	public short[] getPacketNoHeader() {
		return packetFixture.getPresentationPacketNoHeader();
	}

}
