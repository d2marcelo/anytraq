package com.brtracker.shared.utils.packet.teltonika;

import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.packet.BasePacketReaderTest;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class GpsTeltonikaMessageTest extends BasePacketReaderTest {
	
	
	public void testTeltonikaGpsMessageWithFakeFuel2() {
		try {
			
			TrackingMessage tm = packetReader.readPacket((new TeltonikaFixture()).getTeltonikaPacketWithRealFuel2());
			
			assertEquals("Invalid message name", 
			"TeltonikaAVLMessage", tm.getMessageType());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testTeltonikaGpsMessageWithFakeFuel() {
		try {
			
			TrackingMessage tm = packetReader.readPacket((new TeltonikaFixture()).getTeltonikaPacketWithFakeFuel());
			
			assertEquals("Invalid message name", 
			"TeltonikaAVLMessage", tm.getMessageType());

			Json json = Json.read(tm.serialize());
			
			float fuelLevel = json.at("TeltonikaAVLMessage")
			   					  .at("data_items").at(0)
			   					  .at("io_data").at("fuelLevel").asFloat();
			
			float totalFuel = json.at("TeltonikaAVLMessage")
								  .at("data_items").at(0)
								  .at("io_data").at("totalFuel").asFloat();

			float fuelEconomy = json.at("TeltonikaAVLMessage")
				  				  .at("data_items").at(0)
				  				  .at("io_data").at("fuelEconomy").asFloat();
			
			float totalDistance = json.at("TeltonikaAVLMessage")
									  .at("data_items").at(0)
									  .at("io_data").at("totalDistance").asFloat();
			
			assertEquals("Invalid fuel level value", 9.2F, fuelLevel);
			assertEquals("Invalid total fuel value", 16.5F, totalFuel);
			assertEquals("Invalid fuel economy value", 17.0F, fuelEconomy);
			assertEquals("Invalid total distance value", 35.0F, totalDistance);

			Json byteProperty1Byte = json.at("TeltonikaAVLMessage")
										   .at("data_items").at(0)
										   .at("io_data")
										   .at("1byte_io_properties");
			
			assertEquals("Invalid fuel level id", "28", byteProperty1Byte.at(0).at("property_id").asString());
			assertEquals("Invalid fuel level value", fuelLevel, byteProperty1Byte.at(0).at("property_value").asFloat());
			
			Json byteProperty4Byte = json.at("TeltonikaAVLMessage")
										   .at("data_items").at(0)
										   .at("io_data")
										   .at("4byte_io_properties");

			assertEquals("Invalid total fuel id", "29", byteProperty4Byte.at(0).at("property_id").asString());
			assertEquals("Invalid total fuel value", totalFuel, byteProperty4Byte.at(0).at("property_value").asFloat());

			assertEquals("Invalid fuel economy id", "27", byteProperty4Byte.at(1).at("property_id").asString());
			assertEquals("Invalid fuel economy value", fuelEconomy, byteProperty4Byte.at(1).at("property_value").asFloat());
			
			assertEquals("Invalid total distance id", "26", byteProperty4Byte.at(2).at("property_id").asString());
			assertEquals("Invalid total distance value", totalDistance, byteProperty4Byte.at(2).at("property_value").asFloat());

			Float lat = json.at("TeltonikaAVLMessage")
			   				.at("data_items").at(0)
			   				.at("gps_data")
			   				.at("lat").asFloat();
			assertEquals("Invalid latitude", Float.valueOf(37.38688F), lat);
			
			Float lng = json.at("TeltonikaAVLMessage")
							.at("data_items").at(0)
							.at("gps_data")
							.at("lng").asFloat();
			assertEquals("Invalid longitude", Float.valueOf(-121.89124F), lng);
			
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Teltonika GPS Message failed ", false);
		}
	}

	public void testTeltonikaGpsMessage() {
		try {
			
			TrackingMessage tm = packetReader.readPacket(packet);
			
			assertEquals("Invalid message name", 
					"TeltonikaAVLMessage", tm.getMessageType());

			assertEquals("Invalid unitId", 
					"123423452345", tm.getProperty(String.class, "unitId"));
			
			assertEquals("Invalid unitId", 
					25, tm.getProperty(Object[].class, "data_items").length);

			Json json = Json.read(tm.serialize());

			Float lat = json.at("TeltonikaAVLMessage")
							.at("data_items").at(0)
							.at("gps_data")
							.at("lat").asFloat();
			assertEquals("Invalid latitude", Float.valueOf(37.822372F), lat);

			Float lng = json.at("TeltonikaAVLMessage")
							.at("data_items").at(0)
							.at("gps_data")
							.at("lng").asFloat();
			assertEquals("Invalid longitude", Float.valueOf(-122.296745F), lng);

		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Teltonika GPS Message failed ", false);
		}
	}
	
	public void testTeltonikaDirectAccessMessage() {
		try {
			
			Integer dataLength = (Integer) packetReader.seekFieldValue(packet, 3002, "data_length");
			
			assertEquals("Invalid message data length", Integer.valueOf(25), dataLength);
			
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
		return (new TeltonikaFixture()).getTeltonikaPacket2();
	}

}
