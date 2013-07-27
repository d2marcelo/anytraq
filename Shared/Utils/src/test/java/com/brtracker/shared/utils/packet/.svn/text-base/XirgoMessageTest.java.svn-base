package com.brtracker.shared.utils.packet;

import static com.brtracker.shared.utils.packet.PacketDefinitionConstants.MESSAGE_DATE;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.packet.vendors.XirgoConstants;
import com.brtracker.shared.utils.packet.vendors.XirgoUtils;


public class XirgoMessageTest extends BasePacketReaderTest {

	@Override
	public short[] getPacket() {
		return XirgoFixtures.getPeriodicOn();
	}
	
	public void testPeriodicOn1() {
		try {
			
			short[] packet = XirgoFixtures.getPeriodicOn();
//			String strMessage = StringUtil.shortArray2String(packet);
//			System.out.println(strMessage);

			TrackingMessage tm = packetReader.readPacket(packet);

			assertEquals("Invalid message name", 
					XirgoConstants.MESSAGE_NAME, tm.getMessageType());

			assertEquals("Invalid unitId", 
					"352054050522722", tm.getProperty(String.class, "UID").trim());
			
			assertEquals("Invalid standard unitId", 
					"352054050522722", tm.getProperty(String.class, PacketDefinitionConstants.UNIT_ID));
			
			assertEquals("Invalid Lat", 
					Float.valueOf("37.37782"), tm.getProperty(Float.class, "LT"));

			assertEquals("Invalid Lng", 
					Float.valueOf("-121.94098"), tm.getProperty(Float.class, "LN"));

			assertEquals("Invalid Heading", 
					0.0F, tm.getProperty(Float.class, "HD"));
			
			assertEquals("Invalid Speed", 
					Float.valueOf("0"), tm.getProperty(Float.class, "SP"));
			
			assertEquals("Invalid Num Satellites", 
					Integer.valueOf("6"), tm.getProperty(Integer.class, "SV"));

			assertEquals("Invalid miles per galon", 
					Float.valueOf("21.0"), tm.getProperty(Float.class, "MG"));

			String timeStamp = tm.getProperty(String.class, MESSAGE_DATE);
			
			assertEquals("Invalid Message Date", 
					"2012/07/04 17:16:50", timeStamp);
			
			Calendar cal = XirgoUtils.strToCal(timeStamp);
			
			System.out.println(cal.get(Calendar.YEAR) + " " + cal.get(Calendar.MONTH) + 
					" " + cal.get(Calendar.DAY_OF_MONTH));
			
			assertAuditMessage(tm, packet);
			
			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void testIgnitionOn2() {
		try {
			
			short[] packet = XirgoFixtures.getIgnitionOn2();

			TrackingMessage tm = packetReader.readPacket(packet, 8084);

			assertEquals("Invalid message name", 
					XirgoConstants.MESSAGE_NAME, tm.getMessageType());

			assertEquals("Invalid unitId", 
					"352054050522722", tm.getProperty(String.class, "UID").trim());

			assertEquals("Invalid standard unitId", 
					"352054050522722", tm.getProperty(String.class, PacketDefinitionConstants.UNIT_ID));

			assertEquals("Invalid Lat", 
					Float.valueOf("37.35836"), tm.getProperty(Float.class, "LT"));

			assertEquals("Invalid Lng", 
					Float.valueOf("-121.93552"), tm.getProperty(Float.class, "LN"));

			assertEquals("Invalid Heading", 
					0.0F, tm.getProperty(Float.class, "HD"));
			
			assertEquals("Invalid Speed", 
					Float.valueOf("0"), tm.getProperty(Float.class, "SP"));
			
			assertEquals("Invalid Num Satellites", 
					Integer.valueOf("6"), tm.getProperty(Integer.class, "SV"));
			
			assertEquals("Invalid miles per galon", 
					Float.valueOf("0.0"), tm.getProperty(Float.class, "MG"));
			
			assertEquals("Invalid Fuel level", 
					Float.valueOf("0.0"), tm.getProperty(Float.class, "FL"));
			
			assertAuditMessage(tm, packet);
			
			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	public void assertAuditMessage(TrackingMessage tm, short[] packet) throws Exception {
		
		String unitId = tm.getProperty(String.class, PacketDefinitionConstants.UNIT_ID);
		assertNotNull("Xirgo unit id null", unitId);
		
		Map<String, Object> data = new HashMap<String, Object>();
		assertNotNull("Xirgo message type is null", tm.getMessageType());
		assertNotNull("Xirgo message type is null", tm.serialize());
		
		String rawMessage = "\"" + PacketPrinterUtils.toHexString(packet) + "\"";
		assertNotNull("Xirgo raw message is null", rawMessage);
		
		String message = JSONMapper.toString(data);
		System.out.println(message);
	}

	public void testIgnitionOn() {
		
		try {

			short[] packet = XirgoFixtures.getIgnitionOn();
			
			TrackingMessage tm = packetReader.readPacket(packet, 8084);

			assertEquals("Invalid message name", 
					XirgoConstants.MESSAGE_NAME, tm.getMessageType());

			assertEquals("Invalid unitId", 
					"352054050676239", tm.getProperty(String.class, "UID").trim());

			assertEquals("Invalid Fuel level", 
					Float.valueOf("0.0"), tm.getProperty(Float.class, "FL"));

			assertEquals("Invalid Lat", 
					Float.valueOf("36.96766"), tm.getProperty(Float.class, "LT"));

			assertEquals("Invalid Lng", 
					Float.valueOf("-122.00837"), tm.getProperty(Float.class, "LN"));

			assertEquals("Invalid Heading", 
					0.0F, tm.getProperty(Float.class, "HD"));

		} catch (Exception e) {
			
		}
	}
	
	public void testIgnitionOff() {
		
		try {

			short[] packet = XirgoFixtures.getIgnitionOff();
			
			TrackingMessage tm = packetReader.readPacket(packet, 8084);

			assertEquals("Invalid message name", 
					XirgoConstants.MESSAGE_NAME, tm.getMessageType());

			assertEquals("Invalid unitId", 
					"352054050676239", tm.getProperty(String.class, "UID").trim());

			assertEquals("Invalid Lat", 
					Float.valueOf("36.96528"), tm.getProperty(Float.class, "LT"));

			assertEquals("Invalid Lng", 
					Float.valueOf("-122.0109"), tm.getProperty(Float.class, "LN"));

			assertEquals("Invalid Heading", 
					0.0F, tm.getProperty(Float.class, "HD"));

		} catch (Exception e) {
			
		}
	}

	public void testPeriodicOff() {
		try {
			
			short[] packet = XirgoFixtures.getPeriodicOff();
			
			TrackingMessage tm = packetReader.readPacket(packet, 8084);

			assertEquals("Invalid message name", 
					XirgoConstants.MESSAGE_NAME, tm.getMessageType());

			assertEquals("Invalid unitId", 
					"352054050532838", tm.getProperty(String.class, "UID").trim());

			assertEquals("Invalid standard unitId", 
					"352054050532838", tm.getProperty(String.class, PacketDefinitionConstants.UNIT_ID));

			assertEquals("Invalid Lat", 
					Float.valueOf("39.18546"), tm.getProperty(Float.class, "LT"));

			assertEquals("Invalid Lng", 
					Float.valueOf("-121.06663"), tm.getProperty(Float.class, "LN"));

			assertEquals("Invalid Heading", 
					0.0F, tm.getProperty(Float.class, "HD"));
			
			assertEquals("Invalid Speed", 
					Float.valueOf("0"), tm.getProperty(Float.class, "SP"));
			
			assertEquals("Invalid Num Satellites", 
					Integer.valueOf("12"), tm.getProperty(Integer.class, "SV"));

			assertAuditMessage(tm, packet);
			
			assertTrue(true);
		} catch (InvalidPacketDefException e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Keep alive Message failed ", false);
		}
	}

	@Override
	protected String getSchemaFileName() {
		return "json/enfora-schema.json";
	}

}
