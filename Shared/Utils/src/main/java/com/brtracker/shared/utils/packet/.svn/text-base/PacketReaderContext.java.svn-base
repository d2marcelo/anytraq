package com.brtracker.shared.utils.packet;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;

import edu.emory.mathcs.backport.java.util.Arrays;

import static com.brtracker.shared.utils.packet.PacketDefinitionConstants.*;


public class PacketReaderContext {
	
	private short[] rawPacket;
	private int[] packet;
	private int currentPosition = 0;
	private boolean fieldDelimiterReached = false;
	private Stack<Object> messageDataObjects = new Stack<Object>();
	private String messageName;
	private String namespace;
	private String unitId;
	private String messageDate;
	private Integer port;
	
	public void dropPacketHeader(int packetStartIndex) {
		int originalLength = packet.length;
		packet = Arrays.copyOfRange(packet, packetStartIndex, originalLength);
		currentPosition = 0;
	}
	
	public TrackingMessage getTrackingMessage() {
		DefaultTrackingMessage tm = new DefaultTrackingMessage(createMessageWrapper(), messageName);
		return tm;
	}
	
	public TrackingHeader getTrackingHeader() {
		TrackingHeader th = new TrackingHeader(createMessageWrapper(), messageName);
		th.setUnitPacketStartIndex(currentPosition);
		return th;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> createMessageWrapper() {
		if (messageName == null) {
			messageName = "messageData";
		}
		
		Map<String, Object> messageRoot = (Map<String, Object>) messageDataObjects.pop();
		if (unitId != null) {
			messageRoot.put(UNIT_ID, unitId);
		}
		
		if (messageDate != null) {
			messageRoot.put(MESSAGE_DATE, messageDate);
		}
		
		Map<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put(messageName, messageRoot);
		return wrapper;
	}
	
	public void pushDataObject(Object dataObject) {
		messageDataObjects.push(dataObject);
	}
	public Object popDataObject() {
		return messageDataObjects.pop();
	}
	public Object getCurrentDataObject() {
		return messageDataObjects.peek();
	}
	public boolean isPacketReady() {
		return packet != null;
	}
	
	public int[] getPacket() {
		return packet;
	}
	public void setPacket(int[] message) {
		this.packet = message;
	}
	public void setFromShortPacket(short[] message) {
		this.packet = new int[message.length];
		for (int i=0; i<message.length; i++) {
			this.packet[i] = message[i];
		}
	}
	public int getCurrentPosition() {
		return currentPosition;
	}
	public boolean hasNextInt() {
		return currentPosition < packet.length - 1;
	}
	public int readInt() {
		if (currentPosition >= packet.length) {
			throw new PacketReadOutOfBoundary("Reading int out of bound", this);
		}
		return packet[currentPosition++];
	}
	public void rewindInts(int l) {
		if (currentPosition - l < 0) {
			throw new PacketReadOutOfBoundary("Rewind out of bound " + currentPosition + " " + l, this);
		}
		currentPosition -= l;
	}
	public void skipInts(int length) {
		if (currentPosition + length >= packet.length) {
			throw new PacketReadOutOfBoundary("Skipping out of boud of message", this);
		}
		currentPosition += length;
	}
	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
	public void moveCurrentPositionBy(int d) {
		this.currentPosition += d;
	}
	public short[] getRawPacket() {
		return rawPacket;
	}
	public void setRawPacket(short[] rawMessage) {
		this.rawPacket = rawMessage;
	}

	public boolean isFieldDelimiterReached() {
		return fieldDelimiterReached;
	}

	public void setFieldDelimiterReached(boolean fieldDelimiterReached) {
		this.fieldDelimiterReached = fieldDelimiterReached;
	}
	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("unit id " + unitId + " namespace " + namespace + "\n");
			sb.append("current pos " + currentPosition + "\n");
			sb.append("packet " + PacketToString.getHexString(packet) + "\n");
			sb.append("raw packet " + PacketToString.getHexString(packet) + "\n");
			sb.append("message objects " + JSONMapper.toString(messageDataObjects) + "\n");
		} catch (JSONMapperException e) {
		}
		return sb.toString();
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	
	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	public void initializePacketFromRawData() {
		this.packet = new int[rawPacket.length];
		for (int i=0; i<packet.length; i++) {
			packet[i] = (int) rawPacket[i];
		}
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
}
