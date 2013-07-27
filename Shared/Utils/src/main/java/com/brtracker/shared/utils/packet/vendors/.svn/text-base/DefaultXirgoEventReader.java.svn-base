package com.brtracker.shared.utils.packet.vendors;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.math.UnitConversionUtils;
import com.brtracker.shared.utils.packet.DefaultTrackingMessage;
import com.brtracker.shared.utils.packet.PacketDefinitionConstants;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class DefaultXirgoEventReader implements XirgoEventReader {

	private Map<Integer, Map<Integer, String>> fieldNameLookupByEvent = new HashMap<Integer, Map<Integer,String>>();
	
	private Map<String, Converter> convertersLookup = new HashMap<String, Converter>();
	
	protected static MyLogger logger = new MyLogger(DefaultXirgoEventReader.class);
	
	public DefaultXirgoEventReader() {
		initializeLookUps();
	}
	
	@Override
	public TrackingMessage readPacket(short[] packet) {
		String rawMessage = getRawMessage(packet);
		
		Map<String, Object> messagePropertyMap = readRawMessage(rawMessage);
		populateMessageDate(messagePropertyMap);
		populateUntiId(messagePropertyMap);

		Map<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put(XirgoConstants.MESSAGE_NAME, messagePropertyMap);

		TrackingMessage tm = new DefaultTrackingMessage(wrapper, XirgoConstants.MESSAGE_NAME);
		
		return tm;
	}

	private void populateUntiId(Map<String, Object> messagePropertyMap) {
		String unitId = (String) messagePropertyMap.get(XirgoConstants.XIRGO_UNIT_ADDRESS);
		messagePropertyMap.put(PacketDefinitionConstants.UNIT_ID, unitId);
	}

	private void populateMessageDate(Map<String, Object> messagePropertyMap) {
		String date = (String) messagePropertyMap.get(XirgoConstants.XIRGO_DATE);
		String time = (String) messagePropertyMap.get(XirgoConstants.XIRGO_TIME);
		if (!StringUtils.isEmpty(date) && !StringUtils.isEmpty(time)) {
			messagePropertyMap.put(PacketDefinitionConstants.MESSAGE_DATE, date + " " + time);
		}
	}

	public Map<String, Object> readRawMessage(String deviceMessage) {
		
		Map<String, Object> messagePropertyMap = new HashMap<String, Object>();
		StringTokenizer st = new StringTokenizer(deviceMessage, ",");
		int field = 1;
		String uid = st.nextToken();
		String evNum = st.nextToken();
		logger.logInfo("Reading Xirgo message for event " + evNum + " for unit " + uid);
		
		if (!StringUtils.isEmpty(evNum)) {
			
			Map<Integer, String> fieldNameLookup = fieldNameLookupByEvent.get(Integer.valueOf(evNum));
			
			if (fieldNameLookup != null) {
			
				String fieldName = fieldNameLookup.get(field++);
				messagePropertyMap.put(fieldName, uid);
				
				fieldName = fieldNameLookup.get(field++);
				messagePropertyMap.put(fieldName, evNum);
				
				while (st.hasMoreTokens()) {
					String tk = st.nextToken();
					fieldName = fieldNameLookup.get(field++);
					if (StringUtils.isEmpty(fieldName)) {
						break;
					}
					Converter converter = convertersLookup.get(fieldName);
					if (converter != null) {
						tk = converter.convert(tk);
					}
					messagePropertyMap.put(fieldName, tk);
				}
				
			} else {
				logger.logError("Could not retrive field lookup by event " + evNum);
			}
		} else {
			logger.logError("Empty event number in device message " + deviceMessage);
		}
		
		return messagePropertyMap;
	}
	

	private String shortArray2String(short[] packet) {
		StringBuilder sb = new StringBuilder();
		for (short s:packet) {
			sb.append((char) s);
		}
		return sb.toString();
	}
	
	private String getRawMessage(short[] packet) {
		String s = shortArray2String(packet);
		s = s.replace("$$", "");
		s= s.replace("##", "");
		return s;
	}

	private void initializeLookUps() {
		
		Map<Integer, String> sharedFieldsLookup = new HashMap<Integer, String>();
		sharedFieldsLookup.put(1,"UID"); 
		sharedFieldsLookup.put(2,"EV");

		//$$<UID>,<EV#>,<D>,<T>,<LT>,<LN>,<AL>,<SP>,<AC>,<DC>,<RP>
		//,<HD>,<SV>,<HP>,<MI>,<MG>,<BV>,<CQ>,<GS>,<GT>[,<SEQ>]##
		sharedFieldsLookup.put(3,"D");
		sharedFieldsLookup.put(4,"T");
		sharedFieldsLookup.put(5,"LT");
		sharedFieldsLookup.put(6,"LN");
		sharedFieldsLookup.put(7,"AL");
		sharedFieldsLookup.put(8,"SP"); 
		sharedFieldsLookup.put(9,"AC"); 
		sharedFieldsLookup.put(10,"DC"); 
		sharedFieldsLookup.put(11,"RP");
		
		sharedFieldsLookup.put(12,"HD"); 
		sharedFieldsLookup.put(13,"SV"); 
		sharedFieldsLookup.put(14,"HP"); 
		sharedFieldsLookup.put(15,"MI"); 
		sharedFieldsLookup.put(16,"MG"); 
		sharedFieldsLookup.put(17,"BV"); 
		sharedFieldsLookup.put(18,"CQ"); 
		sharedFieldsLookup.put(19,"GS");
		
		Map<Integer, String> event6011Lookup = new HashMap<Integer, String>();
		event6011Lookup.putAll(sharedFieldsLookup);
		event6011Lookup.put(20,"FL"); 
		event6011Lookup.put(21,"GT");
		event6011Lookup.put(22,"SEQ");
		
		Map<Integer, String> non6011EventLookup = new HashMap<Integer, String>();
		non6011EventLookup.putAll(sharedFieldsLookup);
		non6011EventLookup.put(20,"GT"); 
		non6011EventLookup.put(21,"SEQ");
		
		// 6011
		fieldNameLookupByEvent.put(6011, event6011Lookup);
		//4001, 4002, 6012
		fieldNameLookupByEvent.put(4001, non6011EventLookup);
		fieldNameLookupByEvent.put(4002, non6011EventLookup);
		fieldNameLookupByEvent.put(6012, non6011EventLookup);
		
		convertersLookup.put("SP", new SpeedConverter());
		convertersLookup.put("MI", new DistanceConverter());
	}
	
	private static interface Converter {
		public String convert(String value);
	}
	
	private static class SpeedConverter implements Converter {
		@Override
		public String convert(String value) {
			String output = "";
			try {
				output = String.valueOf(UnitConversionUtils.mphToKph(value));
			} catch (Exception e) {
				logger.logError("Unable to convert Xirgo Speed", e);
			}
			return output;
		}
		
	}

	private static class DistanceConverter implements Converter {
		@Override
		public String convert(String value) {
			String output = "";
			try {
				output = String.valueOf(UnitConversionUtils.milesToKm(value));
			} catch (Exception e) {
				logger.logError("Unable to convert Xirgo Speed", e);
			}
			return output;
		}
		
	}
}
