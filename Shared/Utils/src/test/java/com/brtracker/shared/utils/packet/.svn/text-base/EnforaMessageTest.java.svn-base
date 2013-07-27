package com.brtracker.shared.utils.packet;

import java.util.List;

public class EnforaMessageTest extends BasePacketReaderTest {

	@Override
	public short[] getPacket() {
		return EnforaFixtures.getEnforaKeepAlivePacket();
	}
	
	public void test1() {
		try {
			TrackingMessage tm = packetReader.readPacket(EnforaFixtures.getEnforaKeepAlivePacket());
			
			assertEquals("Invalid message name", 
					"EnforaMT3000Message", tm.getMessageType());

			assertEquals("Invalid unitId", 
					"012553000439151", tm.getProperty(String.class, "payloadType1.mdmid").trim());
			
			assertEquals("Invalid Lat", 
					Float.valueOf("37.358135"), tm.getProperty(Float.class, "payloadType1.gpsLat"));

			assertEquals("Invalid Lng", 
					Float.valueOf("-121.12258"), tm.getProperty(Float.class, "payloadType1.gpsLng"));

			assertEquals("Invalid Heading", 
					0.0F, tm.getProperty(Float.class, "payloadType1.gpsHeading"));
			
			assertEquals("Invalid Speed", 
					Float.valueOf("0"), tm.getProperty(Float.class, "payloadType1.gpsSpeed"));
			
			assertEquals("Invalid Odometer", 
					Integer.valueOf("150999771"), tm.getProperty(Integer.class, "payloadType1.gpsOdo"));
			
			assertEquals("Invalid Num Satellites", 
					Integer.valueOf("22"), tm.getProperty(Integer.class, "payloadType1.gpsNumSat"));

			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void test2() {
		try {
			TrackingMessage tm = packetReader.readPacket(EnforaFixtures.getEnforaKeepAlivePacket2());
			
			assertEquals("Invalid message name", 
					"EnforaMT3000Message", tm.getMessageType());

			assertEquals("Invalid unitId", 
					"SB000001", tm.getProperty(String.class, "payloadType1.mdmid").trim());
			
			assertEquals("Invalid Lat", 
					Float.valueOf("37.358166"), tm.getProperty(Float.class, "payloadType1.gpsLat"));

			assertEquals("Invalid Lng", 
					Float.valueOf("-121.12258"), tm.getProperty(Float.class, "payloadType1.gpsLng"));

			assertEquals("Invalid Heading", 
					75.0F, tm.getProperty(Float.class, "payloadType1.gpsHeading"));
			
			assertEquals("Invalid Speed", 
					Float.valueOf("0"), tm.getProperty(Float.class, "payloadType1.gpsSpeed"));
			
			assertEquals("Invalid Odometer", 
					Integer.valueOf("201655826"), tm.getProperty(Integer.class, "payloadType1.gpsOdo"));
			
			assertEquals("Invalid Num Satellites", 
					Integer.valueOf("9"), tm.getProperty(Integer.class, "payloadType1.gpsNumSat"));
			
			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void test3() {
		try {
			TrackingMessage tm = packetReader.readPacket(EnforaFixtures.getEnforaKeepAlivePacket3());
			
			assertEquals("Invalid message name", 
					"EnforaMT3000Message", tm.getMessageType());

			assertEquals("Invalid unitId", 
					"012563000439151", tm.getProperty(String.class, "payloadType1.mdmid").trim());
			
			assertEquals("Invalid Lat", 
					Float.valueOf("37.52205"), tm.getProperty(Float.class, "payloadType1.gpsLat"));

			assertEquals("Invalid Lng", 
					Float.valueOf("-121.95202"), tm.getProperty(Float.class, "payloadType1.gpsLng"));

			assertEquals("Invalid Heading", 
					0.0F, tm.getProperty(Float.class, "payloadType1.gpsHeading"));
			
			assertEquals("Invalid Speed", 
					Float.valueOf("0"), tm.getProperty(Float.class, "payloadType1.gpsSpeed"));
			
			assertEquals("Invalid Odometer", 
					Integer.valueOf("1785658"), tm.getProperty(Integer.class, "payloadType1.gpsOdo"));
			
			assertEquals("Invalid Num Satellites", 
					Integer.valueOf("9"), tm.getProperty(Integer.class, "payloadType1.gpsNumSat"));

			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void testOBDDetected() {
		try {
			TrackingMessage tm = packetReader.readPacket(EnforaFixtures.getEnforaOBDDetected());
			
			assertEquals("Invalid message name", 
					"EnforaMT3000Message", tm.getMessageType());
			
			assertEquals("Invalid Param1 for OBD Detected", 
					"9900", tm.getProperty(String.class, "param1"));

			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void testIgnitionOn() {
		try {
			TrackingMessage tm = packetReader.readPacket(EnforaFixtures.getEnforaIgnitionOn());
			
			assertEquals("Invalid message name", 
					"EnforaMT3000Message", tm.getMessageType());
			
			assertEquals("Invalid Param1 for Ignition On", 
					"1110", tm.getProperty(String.class, "param1"));

			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void testPeriodicIgnitionOn() {
		try {
			TrackingMessage tm = packetReader.readPacket(EnforaFixtures.getEnforaPeriodicIgnitionOn2());
			
			assertEquals("Invalid message name", 
					"EnforaMT3000Message", tm.getMessageType());
			
			assertEquals("Invalid Param1 for OBD Detected", 
					"2110", tm.getProperty(String.class, "param1"));
			
			assertEquals("Invalid Speed", 
					Float.valueOf("53.708"), tm.getProperty(Float.class, "payloadType1.gpsSpeed"));
			
			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void testIgnitionOff() {
		try {
			TrackingMessage tm = packetReader.readPacket(EnforaFixtures.getEnforaIgnitionOff());
			
			assertEquals("Invalid message name", 
					"EnforaMT3000Message", tm.getMessageType());
			
			assertEquals("Invalid Param1 for Ignition On", 
					"1100", tm.getProperty(String.class, "param1"));

			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void testPeriodicIgnitionOnMessages() {
		try {
			
			List<short[]> messages = EnforaFixtures.getPeriodicIgnitionOnMessages();
			for (short[] m : messages) {
				TrackingMessage tm = packetReader.readPacket(m);

				assertEquals("Invalid message name", 
						"EnforaMT3000Message", tm.getMessageType());
				
				assertEquals("Invalid Param1 for OBD Detected", 
						Integer.valueOf(2110), tm.getProperty(Integer.class, "param1"));
				
				System.out.println(tm.getProperty(Float.class, "payloadType1.gpsLat") + "," +
						tm.getProperty(Float.class, "payloadType1.gpsLng") + " - speed: " + 
						tm.getProperty(Float.class, "payloadType1.gpsSpeed") + " - odo: " + 
						tm.getProperty(Float.class, "payloadType1.gpsOdo"));
			}
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}
	
	public void testPeriodicIgnitionOnMessages3() {
		try {
			
			List<short[]> messages = EnforaFixtures.getPeriodicIgnitionOnMessages3();
			int c = 0;
			for (short[] m : messages) {
				TrackingMessage tm = packetReader.readPacket(m);

				assertEquals("Invalid message name", 
						"EnforaMT3000Message", tm.getMessageType());
				
				Integer param1 = tm.getProperty(Integer.class, "param1");
				
				if (2110 == param1) {
//					System.out.println(c + "||" + tm.getProperty(Float.class, "payloadType1.gpsLat") + "," +
//							tm.getProperty(Float.class, "payloadType1.gpsLng") + " - speed: " + 
//							tm.getProperty(Float.class, "payloadType1.gpsSpeed") + " - odo: " + 
//							tm.getProperty(Float.class, "payloadType1.gpsOdo") + " - satellites " +
//							tm.getProperty(Integer.class, "payloadType1.gpsNumSat"));
					System.out.println(tm.getProperty(Float.class, "payloadType1.gpsLat") + "," +
							tm.getProperty(Float.class, "payloadType1.gpsLng") + "\n");					
				}
				c++;
			}
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void test11() {
		try {
			TrackingMessage tm = packetReader.readPacket(EnforaFixtures.getEnforaPeriodicIgnitionOn3());
			
			assertEquals("Invalid message name", 
					"EnforaMT3000Message", tm.getMessageType());

			assertEquals("Invalid unitId", 
					"SB000001", tm.getProperty(String.class, "payloadType1.mdmid").trim());
			
			assertEquals("Invalid Lat", 
					Float.valueOf("37.357082"), tm.getProperty(Float.class, "payloadType1.gpsLat"));

			assertEquals("Invalid Lng", 
					Float.valueOf("-121.1226"), tm.getProperty(Float.class, "payloadType1.gpsLng"));

			assertEquals("Invalid Heading", 
					281.4F, tm.getProperty(Float.class, "payloadType1.gpsHeading"));
			
			assertEquals("Invalid Speed", 
					Float.valueOf("53.708"), tm.getProperty(Float.class, "payloadType1.gpsSpeed"));
			
			assertEquals("Invalid Odometer", 
					Integer.valueOf("3620671"), tm.getProperty(Integer.class, "payloadType1.gpsOdo"));
			
			assertEquals("Invalid Num Satellites", 
					Integer.valueOf("9"), tm.getProperty(Integer.class, "payloadType1.gpsNumSat"));

			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void test12() {
		try {
			TrackingMessage tm = packetReader.readPacket(EnforaFixtures.getEnforaPeriodicIgnitionOn4());
			
			assertEquals("Invalid message name", 
					"EnforaMT3000Message", tm.getMessageType());

			assertEquals("Invalid unitId", 
					"SB000003", tm.getProperty(String.class, "payloadType1.mdmid").trim());
			
			assertEquals("Invalid Lat", 
					Float.valueOf("37.346115"), tm.getProperty(Float.class, "payloadType1.gpsLat"));

			assertEquals("Invalid Lng", 
					Float.valueOf("-122.27517"), tm.getProperty(Float.class, "payloadType1.gpsLng"));

			assertEquals("Invalid Heading", 
					176.3F, tm.getProperty(Float.class, "payloadType1.gpsHeading"));
			
			assertEquals("Invalid Speed", 
					Float.valueOf("34.4472"), tm.getProperty(Float.class, "payloadType1.gpsSpeed"));
			
			assertEquals("Invalid Odometer", 
					Integer.valueOf("211706"), tm.getProperty(Integer.class, "payloadType1.gpsOdo"));
			
			assertEquals("Invalid Num Satellites", 
					Integer.valueOf("8"), tm.getProperty(Integer.class, "payloadType1.gpsNumSat"));

			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	@Override
	protected String getSchemaFileName() {
		return "json/enfora-schema.json";
	}

}
