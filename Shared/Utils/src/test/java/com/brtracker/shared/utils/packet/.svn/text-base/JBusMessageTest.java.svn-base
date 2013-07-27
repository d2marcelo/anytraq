package com.brtracker.shared.utils.packet;

import java.util.Map;

public class JBusMessageTest extends BasePacketReaderTest {

	public void testJBusMessage() {

		try {
			
			TrackingMessage tm = packetReader.readPacket(packet);

			assertEquals("Invalid message name", 
					"PicoloJBusMessage", tm.getMessageType());

			assertEquals("Invalid unitId", 
					"1234", tm.getProperty(String.class, "unitId"));

			assertEquals("Invalid message length", 
					"292", tm.getProperty(String.class, "appMessage.sequenceNumber"));

			assertTrue("Missing gpsInfo element", 
					tm.getProperty(Map.class, "appMessage").containsKey("jBus"));
			
			assertEquals("Invalid message length", 
					"120", tm.getProperty(String.class, "appMessage.messageLength"));
			
			assertEquals("Invalid current speed", 
					"5860", tm.getProperty(String.class, "appMessage.jBus.currentSpeed"));

			assertEquals("Invalid report type ", 
					Integer.valueOf(1), tm.getProperty(Integer.class, "appMessage.jBus.reportType"));
			
			assertNotNull("Null or empty unit time", tm.getProperty(Object.class, "appMessage.jBus.unitTime"));

		} catch (PacketReaderException e) {
			System.out.println(e.getContext());
			assertTrue("Unexpected PacketReaderException", false);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Unexpected exception", false);
		}
	
	}

	public void testJBusMessage2() {

		try {
			
			TrackingMessage tm = packetReader.readPacket(packetFixture.getJBusPacket2());

			assertEquals("Invalid message name", 
					"PicoloJBusMessage", tm.getMessageType());

			assertEquals("Invalid unitId", 
					"045092049000", tm.getProperty(String.class, "unitId"));
			
			assertTrue("Missing gpsInfo element", 
					tm.getProperty(Map.class, "appMessage").containsKey("jBus"));
			
			assertEquals("Invalid message length", 
					"125", tm.getProperty(String.class, "appMessage.messageLength"));

			assertEquals("Invalid message length", 
					"2260", tm.getProperty(String.class, "appMessage.sequenceNumber"));
			
			assertEquals("Invalid current speed", 
					"4968", tm.getProperty(String.class, "appMessage.jBus.currentSpeed"));

			assertEquals("Invalid report type ", 
					Integer.valueOf(1), tm.getProperty(Integer.class, "appMessage.jBus.reportType"));
			
			assertNotNull("Null or empty unit time", tm.getProperty(Object.class, "appMessage.jBus.unitTime"));

		} catch (PacketReaderException e) {
			System.out.println(e.getContext());
			assertTrue("Unexpected PacketReaderException", false);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Unexpected exception", false);
		}
	
	}

	@Override
	public short[] getPacket() {
		return packetFixture.getJBusPacket(); 
	}

}
