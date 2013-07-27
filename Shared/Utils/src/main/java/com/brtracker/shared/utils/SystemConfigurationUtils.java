package com.brtracker.shared.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.wsutils.ClientCall;

@Component
public class SystemConfigurationUtils implements SystemConfiguration, InitializingBean, ResourceLoaderAware {

	private MyLogger logger = new MyLogger(SystemConfigurationUtils.class);
	
	private String DEFAULT_CONFIG_ENDPOINTS= "classpath:defaultServiceEndpoints.properties";
	
	private boolean useDefaultConfiguration = false;
	private String metadaServiceEndpoint;
	private Map<String, Map<String,String>> serviceEndpoints = new HashMap<String, Map<String,String>>();
	private ResourceLoader resourceLoader;
	
	@Override
	public String getEndpointURL(String service) {
		Map<String, String> map = serviceEndpoints.get(service);
		if (map == null) {
			throw new SystemConfigurationException("No configuration found for service " + service);
		}
		
		String value = map.get(URL);
		if (value == null) {
			throw new SystemConfigurationException("No configuration found for element " + URL);
		}
		String url = serviceEndpoints.get(service).get(URL);
		
		value = map.get(PORT);
		if (value == null) {
			throw new SystemConfigurationException("No configuration found for element " + PORT);
		}
		String port = serviceEndpoints.get(service).get(PORT);
		return url + ":" + port;
	}

	@Override
	public String getUrl(String service) {
		Map<String, String> map = serviceEndpoints.get(service);
		if (map == null) {
			throw new SystemConfigurationException("No configuration found for service " + service);
		}
		
		String value = map.get(URL);
		if (value == null) {
			throw new SystemConfigurationException("No configuration found for element " + URL);
		}
		return serviceEndpoints.get(service).get(URL);
	}

	@Override
	public String getPort(String service) {
		Map<String, String> map = serviceEndpoints.get(service);
		if (map == null) {
			throw new SystemConfigurationException("No configuration found for service " + service);
		}
		
		String value = map.get(PORT);
		if (value == null) {
			throw new SystemConfigurationException("No configuration found for element " + PORT);
		}
		return serviceEndpoints.get(service).get(PORT);
	}

	@Override
	public String getConfigElement(String service, String element) {
		Map<String, String> map = serviceEndpoints.get(service);
		if (map == null) {
			throw new SystemConfigurationException("No configuration found for service " + service);
		}
		String value = map.get(element);
		if (value == null) {
			throw new SystemConfigurationException("No configuration found for element " + element);
		}
		return value;
	}

	@Override
	public String getConfigElement(String service, String element,
			String defaultValue) {
		
		String returnValue = defaultValue;
		Map<String, String> map = serviceEndpoints.get(service);
		if (map != null) {
			String value = map.get(element);
			if (value != null) {
				returnValue = value;
			}
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public void loadMetadataEndpoints () {
		try {
			
			Map<String, Map<String,String>> endpointsMap = (Map<String, Map<String,String>>) 
				JSONMapper.toObject(ClientCall.get(metadaServiceEndpoint), Map.class);
			
			setServiceEndpoints(endpointsMap);
			
		} catch (Exception e) {
			logger.logError("Unexpected exception loading MetadataEndpoints from " + metadaServiceEndpoint, e);
		} 
	}

	private void loadPropertiesFileEndpoints() {
		try {
			Resource resource = resourceLoader.getResource(DEFAULT_CONFIG_ENDPOINTS);

			Properties props = new Properties();
			
			props.load(resource.getInputStream());
			
			Enumeration<?> e = props.propertyNames();
			while (e.hasMoreElements()) {
			    String propName = (String)e.nextElement();
			    String propValue = (String)props.get(propName);
			    loadProperty(propName, propValue);
			}
			
		} catch (Exception e) {
			logger.logError("Unexpected exception loading Properties File Endpoints", e);
		}
	}

	private void loadProperty(String propName, String propValue) {
		
		if (!propName.contains(".")) {
			logger.logError("Default configuaration parameters " + 
					propName + " has no valid property, it should look like ACTIVE_MQ.url");
			return;
		}
		
		StringTokenizer st = new StringTokenizer(propName, ".");
		String serviceName = st.nextToken();
		String property = st.nextToken();
		
		if (!serviceEndpoints.containsKey(serviceName)) {
			serviceEndpoints.put(serviceName, new HashMap<String, String>());
		}
		serviceEndpoints.get(serviceName).put(property, propValue);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (!useDefaultConfiguration) {
			logger.logDebug("Loading endpoint config from metadata ...");
			loadMetadataEndpoints();
		} else {
			logger.logDebug("Loading default endpoint config from properties file ...");
			loadPropertiesFileEndpoints();
		}
	}

	public boolean isUseDefaultConfiguration() {
		return useDefaultConfiguration;
	}

	public void setUseDefaultConfiguration(boolean useDefaultConfiguration) {
		this.useDefaultConfiguration = useDefaultConfiguration;
	}

	public String getMetadaServiceEndpoint() {
		return metadaServiceEndpoint;
	}

	public void setMetadaServiceEndpoint(String metadaServiceEndpoint) {
		this.metadaServiceEndpoint = metadaServiceEndpoint;
	}

	public Map<String, Map<String, String>> getServiceEndpoints() {
		return serviceEndpoints;
	}

	public void setServiceEndpoints(
			Map<String, Map<String, String>> serviceEndpoints) {
		this.serviceEndpoints = serviceEndpoints;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

}
