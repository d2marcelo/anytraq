package com.brtracker.shared.utils.packet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.brtracker.shared.utils.logging.MyLogger;

/**
 * Encapsulates access to all the available schema definitions.
 * Each Schema has a namespace that can be used to group set of schema definition 
 * per manufacturer and device family and virtually any category.
 *  
 * - A Schema Definition includes all the schema types the define a given set of messages.
 * - A Message Root is the entry point of the packet reader, any subsequent message and field is read from it.
 * - An element definition is a specific message definition within a given schema set, ex: the application message definition within the Picolo family
 * 
 */
public class PacketDefinitionRegistry implements InitializingBean {
	
	private MyLogger logger = new MyLogger(PacketDefinitionRegistry.class);
	
	private Map<String, Map<String,?>> schemaDefinitions = new HashMap<String, Map<String,?>>();
	private Map<Integer, Map<String,?>> schemasByPort = new HashMap<Integer, Map<String,?>>();
	
	private String defaultNs;
	
	private Map<String, PacketPreprocessor> preprocessors = new HashMap<String, PacketPreprocessor>();
	
	private PacketDefinitionLoader schemaLoader;
	
	public Map<String,?> getMessageRoot() {
		return getMessageRoot(defaultNs);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,?> getMessageRoot(String ns) {
		return Collections.unmodifiableMap(
				(Map<String,?>) schemaDefinitions.get(ns).get(PacketDefinitionConstants.MESSAGE_ROOT));
	}

	public void addDefaultDefinition(Map<String, ?> definition) {
		addDefinition(definition);
		defaultNs = (String) definition.get(PacketDefinitionConstants.SCHEMA_NS);
	}
	
	public void addDefinition(Map<String, ?> definition) {
		
		if (!definition.containsKey(PacketDefinitionConstants.SCHEMA_NS)) {
			throw new InvalidSchemaException("Given schema has no namespace");
		}
		
		if (!definition.containsKey(PacketDefinitionConstants.MESSAGE_ROOT)) {
			throw new InvalidSchemaException("Given schema has no message root");
		}

		if (defaultNs == null) {
			defaultNs = (String) definition.get(PacketDefinitionConstants.SCHEMA_NS);
		}
		
		String ns = (String) definition.get(PacketDefinitionConstants.SCHEMA_NS);
		
		schemaDefinitions.put(ns, definition);
		
		Integer port = PacketReaderUtils.getOptionalIntegerVal(definition, PacketDefinitionConstants.SCHEMA_PORT_MAPPING_P);
		if (port != null) {
			schemasByPort.put(port, definition);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String, ?> getElementDefinition(String definitioName) {
		return (Map<String, ?>) schemaDefinitions.get(defaultNs).get(definitioName);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, ?> getElementDefinition(String definitioName, String ns) {
		return (Map<String, ?>) schemaDefinitions.get(ns).get(definitioName);
	}

	public Map<String, ?> getSchemaDefinitionByPort(int port) {
		return (Map<String, ?>) schemasByPort.get(port);
	}

	public String getSchemaNamespaceByPort(int port) {
		
		String schemaNs = null;
		
		Map<String, ?> schema = schemasByPort.get(port);
		if (schema != null) {
			schemaNs = (String) schema.get(PacketDefinitionConstants.SCHEMA_NS);
		}
		return schemaNs;
	}

	public Map<String, ?> getSchemaDefinition() {
		return (Map<String, ?>) schemaDefinitions.get(defaultNs);
	}
	
	public Map<String, ?> getSchemaDefinition(String ns) {
		return (Map<String, ?>) schemaDefinitions.get(ns);
	}

	public void addPreprocessord(String key, PacketPreprocessor preprocessor) {
		preprocessors.put(key, preprocessor);
	}
	
	public PacketPreprocessor getPreprocessor(String key) {
		return preprocessors.get(key);
	}
	
	public String getDefaultNs() {
		return defaultNs;
	}
	
	public void setDefaultNs(String defaultNs) {
		this.defaultNs = defaultNs;
	}

	public void setDefinitions(Map<String, Map<String, ?>> definitions) {
		this.schemaDefinitions = definitions;
	}

	public void setPreprocessors(Map<String, PacketPreprocessor> preprocessors) {
		this.preprocessors = preprocessors;
	}

	public void setSchemaLoader(PacketDefinitionLoader schemaLoader) {
		this.schemaLoader = schemaLoader;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (schemaLoader != null) {
			List<Map<String, ?>> loadedSchemas = schemaLoader.getSchemaDefinitions();
			
			logger.logInfo("List of " + loadedSchemas.size() + " schemas ");
			
			for (Map<String, ?> schema: loadedSchemas) {
				logger.logInfo("Adding schema " + schema);
				addDefinition(schema);
			}
		} else {
			logger.logInfo("No schema loader has been defined for PacketDefinitionRegistry");
		}
	}

}
