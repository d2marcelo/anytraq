package com.brtracker.shared.utils.packet;

import java.util.Map;

import com.brtracker.shared.utils.logging.MyLogger;

public class PacketReaderUtils {
	
	private static MyLogger logger = new MyLogger(PacketReaderUtils.class);
	
	public static final String getByHexOrDec(Map<String, ?> mapping, int i) {
		Object value = mapping.get("0x" + Integer.toHexString(i));
		if (value == null) {
			value = mapping.get(Integer.toString(i));
		}
		return (String) value;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getOptionalMapVal(Map<String, ?> mapping, String key) {
		Map<String, Object> map = null;
		Object o = mapping.get(key);
		if (o instanceof Map<?,?>) {
			map = (Map<String, Object>) o;
		}
		return map;
	}
	
	public static String getOptionalStringVal(Map<String, ?> mapping, String key) {
		return getOptionalStringVal(mapping, key, null);
	}
	
	public static String getOptionalStringVal(Map<String, ?> mapping, String key, String d) {
		try {
			String value = (String) mapping.get(key);
			if (value != null) {
				return value;
			}
		} catch (Exception e) {
			logger.logError("getOptionalStringVal failed reading key " + key, e);
		}
		return d;
	}

	public static Integer getOptionalIntegerVal(Map<String, ?> mapping, String key) {
		return getOptionalIntegerVal(mapping, key, null);
	}
	
	public static Integer getOptionalIntegerVal(Map<String, ?> mapping, String key, Integer d) {
		try {
			String value = (String) mapping.get(key);
			if (value != null) {
				return Integer.valueOf(value);
			}
		} catch (Exception e) {
			logger.logError("getOptionalIntegerVal failed reading key " + key, e);
		}
		return d;
	}
	
	public static Double getOptionalDoubleVal(Map<String, ?> mapping, String key) {
		return getOptionalDoubleVal(mapping, key, null);
	}

	public static Double getOptionalDoubleVal(Map<String, ?> mapping, String key, Double d) {
		try {
			String value = (String) mapping.get(key);
			if (value != null) {
				return Double.valueOf(value);
			}
		} catch (Exception e) {
			logger.logError("getOptionalDoubleVal failed reading key " + key, e);
		}
		return d;
	}
	public static Float getOptionalFloatVal(Map<String, ?> mapping, String key) {
		return getOptionalFloatVal(mapping, key, null);
	}
	public static Float getOptionalFloatVal(Map<String, ?> mapping, String key, Float d) {
		try {
			String value = (String) mapping.get(key);
			if (value != null) {
				return Float.valueOf(value);
			}
		} catch (Exception e) {
			logger.logError("getOptionalFloatVal failed reading key " + key, e);
		}
		return d;
	}
	public static Boolean getOptionalBooleanVal(Map<String, ?> mapping, String key) {
		return getOptionalBooleanVal(mapping, key, null);
	}
	
	public static Boolean getOptionalBooleanVal(Map<String, ?> mapping, String key, Boolean d) {
		try {
			String value = (String) mapping.get(key);
			if (value != null) {
				return Boolean.valueOf(value);
			}
		} catch (Exception e) {
			logger.logError("getOptionalBooleanVal failed reading key " + key, e);
		}
		return d;
	}
	
}
