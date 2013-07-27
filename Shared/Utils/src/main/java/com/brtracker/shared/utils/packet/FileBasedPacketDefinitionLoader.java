package com.brtracker.shared.utils.packet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;

public class FileBasedPacketDefinitionLoader implements PacketDefinitionLoader {

	private MyLogger logger = new MyLogger(FileBasedPacketDefinitionLoader.class);
	
	private List<String> schemaFileNames;
	
	@SuppressWarnings("unchecked")
	public Map<String, ?> loadPacketDefinitions(String schemaFileName) throws JSONMapperException {

		return (Map<String, ?>) JSONMapper.loadClasspathResource(schemaFileName, Map.class);
		
	}
	
	public List<Map<String, ?>> getSchemaDefinitions() {
		List<Map<String, ?>> schemas = new ArrayList<Map<String,?>>();
		if (schemaFileNames != null) {
			for (String schemaFileName : schemaFileNames) {
				try {
					schemas.add(loadPacketDefinitions(schemaFileName));
				} catch (JSONMapperException e) {
					logger.logError("Exception loading schema " + schemaFileName, e);
				}
			}
		}
		return schemas;
	}

	public void setSchemaFileNames(List<String> schemaFileNames) {
		this.schemaFileNames = schemaFileNames;
	}
}
