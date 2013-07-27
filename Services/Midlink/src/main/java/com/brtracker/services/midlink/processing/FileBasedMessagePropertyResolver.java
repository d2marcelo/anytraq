package com.brtracker.services.midlink.processing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.brtracker.services.midlink.processing.application.ActionName;
import com.brtracker.shared.utils.json.JSONMapper;

public class FileBasedMessagePropertyResolver implements InitializingBean, MessagePropertyResolver {

	private List<String> propertyMappingFileNames;
	
	private Map<String, Map<String, Object>> propertiesByMessageType = 
		new HashMap<String, Map<String,Object>>();
	
	@Override
	public Map<String, Object> getMessageProperties(String messageType) {
		return propertiesByMessageType.get(messageType);
	}

	@Override
	public Map<String, Object> getMessageProperties(String messageType,
			ActionName actionName) {
		return propertiesByMessageType.get(messageType + "." + actionName.getName());
	}

	public List<String> getPropertyMappingFileNames() {
		return propertyMappingFileNames;
	}

	public void setPropertyMappingFileNames(List<String> propertyMappingFileNames) {
		this.propertyMappingFileNames = propertyMappingFileNames;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void afterPropertiesSet() throws Exception {
		if (propertyMappingFileNames != null) {
			
			for (String fileName: propertyMappingFileNames) {
				
				Map<String, Object> propertyMapping = 
					(Map<String, Object>) JSONMapper.loadClasspathResource(fileName, Map.class);
				
				Iterator<String> keysIterator = propertyMapping.keySet().iterator();
				while (keysIterator.hasNext()) {
					String messageType = keysIterator.next();
					Map<String, Object> propertiesMap = 
						(Map<String, Object>) propertyMapping.get(messageType);
					propertiesByMessageType.put(messageType, propertiesMap);
				}
			}
		}
	}


}
