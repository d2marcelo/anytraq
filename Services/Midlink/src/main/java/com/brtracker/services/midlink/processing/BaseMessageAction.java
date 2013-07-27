package com.brtracker.services.midlink.processing;

import static com.brtracker.shared.utils.SystemConfiguration.ASYNC;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.brtracker.services.midlink.processing.application.ActionHelper;
import com.brtracker.services.midlink.processing.application.ActionName;
import com.brtracker.services.midlink.processing.application.MessageActionException;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.Tracking;
import com.brtracker.shared.utils.gps.GpsUtils;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.TrackingMessage;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
public abstract class BaseMessageAction implements MessageAction {

	protected MyLogger logger = new MyLogger(BaseMessageAction.class);
	private MessagePropertyResolver propertyResolver;
	protected ActionHelper actionHelper;
	private WsClient wsClient;
	private MqClient mqClient;
	
	private Map<String, Map<String, Object>> propertiesCache = 
		Collections.synchronizedMap(new HashMap<String, Map<String, Object>>());
	
	protected Map<String, Object> getMessagePropertiesOrDie(TrackingMessage trackingMessage) {
		
		String messageType = trackingMessage.getMessageType();
		if (messageType == null) {
			throw new MessageActionException("Given tracking message instance is missing message type", trackingMessage);
		}
		
		Map<String, Object> messageProperties = 
			propertyResolver.getMessageProperties(messageType);
		if (messageProperties == null) {
			throw new MessageActionException("Given tracking message type has not property mapping", trackingMessage);
		}
		return Collections.unmodifiableMap(messageProperties);
	}

	protected Map<String, Object> getMessagePropertiesOrDie(TrackingMessage trackingMessage, ActionName actionName) {

		String key = trackingMessage.getMessageType() + "." + actionName.getName();
		
		if (propertiesCache.containsKey(key)) {
			return propertiesCache.get(key);
		}
		
		Map<String, Object> cacheProperties = new HashMap<String, Object>();
		
		Map<String, Object> messageProperties = getMessagePropertiesOrDie(trackingMessage);
		cacheProperties.putAll(messageProperties);

		String messageType = trackingMessage.getMessageType();

		Map<String, Object> messageActionProperties = propertyResolver.getMessageProperties(messageType, actionName);

		if (messageActionProperties != null) {
			cacheProperties.putAll(messageActionProperties);
		}
		System.out.println("adding cache properties " + key);
		propertiesCache.put(key, cacheProperties);
		
		return cacheProperties;
	}
	
	protected ServiceResponse postRequest(Object message, String endpoint) throws Exception {
		return wsClient.postRequest(message, endpoint);
	}

	protected void sendMessage(DeviceMessageAttribute message) throws Exception {
		
		String payload = JSONMapper.toString(message);
		if (validLocation(message)) {
			logger.logInfo("Sending message to Async " + payload);
		
			mqClient.sendMessageToQueue(message, ASYNC);
		} else {
			logger.logInfo("Ignoring invalid location message " + payload);
		}
	}
	
	private boolean validLocation(DeviceMessageAttribute message) {
		
		try {
			Map<String, String> attributes = message.getAttributes();
			
			String latStr = attributes.get("latitude");
			String lngStr = attributes.get("longitude");
			
			logger.logInfo("Checking location (" + latStr + "," + lngStr + ")");
			
			return !GpsUtils.invalidLocation(latStr, lngStr);
		} catch (Exception e) {
			logger.logError("Failed to validated location ", e);
		}
		return false;
	}

	protected DeviceMessageAttribute createDeviceMessageAttribute(String unitId, String msgDate, TrackingMessage 
			trackingMessage, Map<String, Object> messageProperties) {
		
		DeviceMessageAttribute dma = new DeviceMessageAttribute();
		dma.setUnitAddress(unitId);
		dma.setMessageDate(msgDate);
		dma.put("messageType", trackingMessage.getMessageType());
		
		Set<String> sourcePropertySet = messageProperties.keySet();
		for (String sourceProperty: sourcePropertySet) {
			String propertyValue = trackingMessage.getProperty(String.class, sourceProperty);
			if (propertyValue != null) {
				Object targetProperty = messageProperties.get(sourceProperty);
				if (targetProperty instanceof String) {
					dma.put(targetProperty.toString(), propertyValue);
				} 
			}
		}
		return dma;
	}

	protected String getTrackingEndpoint() {
		Tracking tracking = SystemResourcesUtil.Tracking.get();
		return tracking.getUrl() + ":" + tracking.getPort() + "/";
	}

	public void setPropertyResolver(MessagePropertyResolver propertyResolver) {
		this.propertyResolver = propertyResolver;
	}
	public void setWsClient(WsClient wsClient) {
		this.wsClient = wsClient;
	}

	public void setMqClient(MqClient mqClient) {
		this.mqClient = mqClient;
	}
	public void setActionHelper(ActionHelper actionHelper) {
		this.actionHelper = actionHelper;
	}


}
