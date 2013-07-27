package com.brtracker.shared.utils.spring;

import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.logging.MyLogger;

public class MetadataPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements InitializingBean {

	private String key; 
	private Map<String, Map<String,String>> resources; 
	private MyLogger logger = new MyLogger(MetadataPropertyPlaceholderConfigurer.class);
	
	
	 public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
		String value = null;
		if (!resources.containsKey(key)) {
			logger.logInfo("No resources for key ["+ key + "]");
		} else {
			if (!resources.get(key).containsKey(placeholder)) {
				logger.logInfo("No resources for place holder ["+ placeholder + "]");
			} else {
				value = resources.get(key).get(placeholder);
			}
		}
		logger.logInfo("Loaded key/placeholder: "+ placeholder + "/" + key + " value: "+value);
		return value;
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.logInfo("Loading SystemResourcesUtil resources");
		resources = SystemResourcesUtil.getResources();
	}
	   
}
