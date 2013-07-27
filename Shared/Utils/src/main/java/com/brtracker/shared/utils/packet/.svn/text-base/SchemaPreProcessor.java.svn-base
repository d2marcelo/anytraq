package com.brtracker.shared.utils.packet;

import java.util.Map;

public class SchemaPreProcessor {

	private static Map<String, PacketPreprocessor> preProcessorMap;

	public static Map<String, PacketPreprocessor> getPreProcessorMap() {
		return preProcessorMap;
	}

	public void setPreProcessorMap(Map<String, PacketPreprocessor> preProcessorMap) {
		SchemaPreProcessor.preProcessorMap = preProcessorMap;
	}
	
	public static PacketPreprocessor get(String namespace) throws InvalidPacketException {
		if (preProcessorMap.containsKey(namespace)) {
			return preProcessorMap.get(namespace);
		} else {
			throw new InvalidPacketException("Namespace \""+namespace+" not found.");
		}
		
	}
}
