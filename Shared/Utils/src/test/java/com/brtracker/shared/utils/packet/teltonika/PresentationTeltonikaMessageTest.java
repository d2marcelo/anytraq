package com.brtracker.shared.utils.packet.teltonika;

import com.brtracker.shared.utils.packet.BasePacketReaderTest;

public class PresentationTeltonikaMessageTest extends BasePacketReaderTest {

	public void testTeltonikaPresentationMessage() {
		try {
			
			String unitId = packetReader.findUnitIdentifier(packet, 3002);
			
			assertEquals("Invalid unitId", 
					"354330030616604", unitId);
			
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Teltonika GPS Message failed ", false);
		}
	}
	
	@Override
	protected String getSchemaFileName() {
		return "json/teltonika-schema.json";
	}

	@Override
	public short[] getPacket() {
		return (new TeltonikaFixture()).getTeltonikaPresentationPacket();
	}

}
