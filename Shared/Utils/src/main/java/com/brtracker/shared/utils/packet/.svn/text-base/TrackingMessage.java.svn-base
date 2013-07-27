package com.brtracker.shared.utils.packet;

/**
 * Represents a message used to track a given device. 
 * This kind of messages are the result of parsing and reading 
 * the device native packet payload.     
 */
public interface TrackingMessage {

	public <T> T getProperty(Class<T> type, String key);
	public String getMessageType();
	public void serialize(String fileName);
	public String serialize();
	public void deserialize(String json);
}
