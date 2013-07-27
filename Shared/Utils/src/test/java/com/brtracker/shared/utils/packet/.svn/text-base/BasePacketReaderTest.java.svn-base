package com.brtracker.shared.utils.packet;

import java.util.Map;

import com.brtracker.shared.utils.packet.vendors.DefaultXirgoEventReader;
import com.brtracker.shared.utils.packet.vendors.XirgoPacketReaderController;

import junit.framework.TestCase;

public abstract class BasePacketReaderTest extends TestCase {

	protected short [] packet;
	protected PacketReaderController packetReader;
	protected PacketFixture packetFixture = new PacketFixture();
	
	public abstract short[] getPacket();
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		packet = getPacket();		
		
		String schemaFile = getSchemaFileName();
		String commonSchemaFile = "json/tracking-header-schema.json";
		
		FileBasedPacketDefinitionLoader loader = new FileBasedPacketDefinitionLoader();
		Map<String, ?> schema = loader.loadPacketDefinitions(schemaFile);
		Map<String, ?> commonSchema = loader.loadPacketDefinitions(commonSchemaFile);
		
		PacketDefinitionRegistry registry = new PacketDefinitionRegistry();
		registry.addDefaultDefinition(schema);
		registry.addDefinition(commonSchema);
		
		registry.addPreprocessord(PacketPreprocessorConstants.PICOLO_PREPROCESSOR, 
				new PicoloPacketPreprocessor());
		
		registry.addPreprocessord(PacketPreprocessorConstants.DEFAULT_PREPROCESSOR, 
				new DefaultPacketPreprocessor());
		
		PacketReaderRegistry readerRegistry = new PacketReaderRegistry();
		
		packetReader = new PacketReaderController();
		packetReader.setPacketDefinitionRegistry(registry);
		packetReader.setReaderRegistry(readerRegistry);
		DefaultXirgoEventReader defaultXirgoEventReader = new DefaultXirgoEventReader();
		XirgoPacketReaderController xirgoReader = new XirgoPacketReaderController();
		xirgoReader.setDefaultXirgoEventReader(defaultXirgoEventReader);
		packetReader.setXirgoReader(xirgoReader);

	}
	
	protected String getSchemaFileName() {
		return "json/picolo-schema.json";
	}

}
