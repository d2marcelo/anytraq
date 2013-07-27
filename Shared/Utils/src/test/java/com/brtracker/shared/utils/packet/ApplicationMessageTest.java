package com.brtracker.shared.utils.packet;

import java.util.Map;

public class ApplicationMessageTest extends BasePacketReaderTest {

	public void testProdMsg1() {
		try {
			packetReader.readPacket(packetFixture.getProdMsg1());
			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}
	public void testGps1() {
		try {
			packetReader.readPacket(packetFixture.getGps1());
			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void testGps2() {
		try {
			packetReader.readPacket(packetFixture.getGps2());
			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void testNoValidGpsFix() {
		try {
			
			packetReader.readPacket(packetFixture.getNoValidGps());
			assertTrue(true);

		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}
	public void testKeepAliveMessage() {

		try {
			
			packetReader.readPacket(packetFixture.getKeepAlive());
			assertTrue(true);

		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}
	
	public void testParseApplicationMessage() {

		try {
			
			TrackingMessage tm = packetReader.readPacket(packet);

			assertEquals("Invalid message name", 
					"PicoloGpsInfoMessage", tm.getMessageType());

			assertEquals("Invalid unitId", 
					"045092047146", tm.getProperty(String.class, "unitId"));
			
			assertTrue("Missing gpsInfo element", 
					tm.getProperty(Map.class, "appMessage").containsKey("gpsInfo"));
			
			assertEquals("Invalid message length", 
					"110", tm.getProperty(String.class, "appMessage.messageLength"));
			
			assertEquals("Invalid message length", 
					"1334", tm.getProperty(String.class, "appMessage.sequenceNumber"));

			assertEquals("Invalid unit serial number", 
					Float.valueOf("702424.0"), tm.getProperty(Float.class, "appMessage.gpsInfo.unitSerialNumber"));
			
			assertEquals("Invalid distance", 
					Float.valueOf("1256400.2"), tm.getProperty(Float.class, "appMessage.gpsInfo.distance"));

			assertNotNull("Null or empty unit time", tm.getProperty(Object.class, "appMessage.gpsInfo.unitTime"));

			Integer[] reason = tm.getProperty(Integer[].class, "appMessage.gpsInfo.reason");
			
			assertNotNull("Gps Info reason array null", reason);
			
			assertEquals("Invalid unit serial number", 
					reason[0], Integer.valueOf(3));

		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Unexpected exception", false);
		}
	
	}

	public void testAlarmApplicationMessage() {
		packet = packetFixture.getAlarmApplicationPacket();
		packetReader.readPacket(packet);		
	}
	
	public void testIOServiceApplicationMessage3() {
		packet = packetFixture.getIoService();
		TrackingMessage tm = packetReader.readPacket(packet);
		System.out.println("waiting ");
		
		assertEquals("Invalid message name", 
				"PicoloIOServiceMessage", tm.getMessageType());
		
		assertEquals("Invalid unitId", 
				"045110233016", tm.getProperty(String.class, "unitId"));
		
		assertEquals("Invalid message length", 
				"2", tm.getProperty(String.class, "appMessage.sequenceNumber"));
		
		assertEquals("Invalid sequence", 
				"3719", tm.getProperty(String.class, "appMessage.ioService.sequence"));

		assertEquals("Invalid dry input 6", 
				Integer.valueOf("0"), tm.getProperty(Integer.class, "appMessage.ioService.dryInput6"));

		assertEquals("Invalid dry input 1", 
				Integer.valueOf("1"), tm.getProperty(Integer.class, "appMessage.ioService.dryInput1"));

	}
	
	public void testIOServiceApplicationMessage() {
		packet = packetFixture.getIOServicePacket();
		TrackingMessage tm = packetReader.readPacket(packet);

		assertEquals("Invalid message name", 
				"PicoloIOServiceMessage", tm.getMessageType());
		
		assertEquals("Invalid unitId", 
				"045092047146", tm.getProperty(String.class, "unitId"));
		
		assertEquals("Invalid message length", 
				"1377", tm.getProperty(String.class, "appMessage.sequenceNumber"));

		assertEquals("Invalid sequence", 
				"557", tm.getProperty(String.class, "appMessage.ioService.sequence"));

		assertEquals("Invalid dry input 5", 
				Integer.valueOf("1"), tm.getProperty(Integer.class, "appMessage.ioService.dryInput5"));

	}
	
	public void testIOServiceApplicationMessage2() {
		packet = packetFixture.getIOServicePacket2();
		TrackingMessage tm = packetReader.readPacket(packet);

		assertEquals("Invalid message name", 
				"PicoloIOServiceMessage", tm.getMessageType());
		
		assertEquals("Invalid unitId", 
				"013110133057", tm.getProperty(String.class, "unitId"));
		
		assertEquals("Invalid message length", 
				"7300", tm.getProperty(String.class, "appMessage.sequenceNumber"));

		assertEquals("Invalid dry input 5", 
				Integer.valueOf("0"), tm.getProperty(Integer.class, "appMessage.ioService.dryInput5"));

	}


	@Override
	public short[] getPacket() {
		return packetFixture.getApplicationDataPacket();
	}
}
