package com.brtracker.shared.utils;

import java.nio.charset.Charset;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {

	
	public static int compressString(String data, byte[] output, int len) {
	    Deflater deflater = new Deflater();
	    deflater.setInput(data.getBytes(Charset.forName("utf-8")));
	    deflater.finish();
	    return deflater.deflate(output, 0, len);
	}

	public static String decompressString(byte[] input, int len) {

	    String result = null;
	    try {
	        Inflater inflater = new Inflater();
	        inflater.setInput(input, 0, len);

	        byte[] output = new byte[100]; //todo may oveflow, find better solution
	        int resultLength = inflater.inflate(output);
	        inflater.end();

	        result = new String(output, 0, resultLength, Charset.forName("utf-8"));
	    } catch (DataFormatException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    return result;
	}
	
	public static String generateReducedString (String string, int size){
		string = StringUtils.trimAllWhitespace(string);
		string = StringUtils.replace(string, "null", "x");
		String left = org.apache.commons.lang.StringUtils.left(string, size);
		String right = org.apache.commons.lang.StringUtils.right(string, size);
		String out =  left + right;
		return out;
	}
	
	public static String getZeroIfNull (String value) {
		if (value == null) return "0";
		return value;
	}
	
	public static String sanitizePhoneNumber(String recipientAddress) {
		String sanitizedAddress = null;
		if (!org.apache.commons.lang.StringUtils.isEmpty(recipientAddress)) {
			sanitizedAddress = recipientAddress
				.replace("(", "")
				.replace(")", "")
				.replace(" ", "")
				.replace("-", "");
		}
		return sanitizedAddress;
	}

	public static String shortArray2String(short[] packet) {
		StringBuilder sb = new StringBuilder();
		for (short s:packet) {
			sb.append((char) s);
		}
		return sb.toString();
	}

}
