package com.brtracker.shared.utils.packet.vendors;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class XirgoUtils {
	private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy/M/dd HH:mm:ss");
	
	public static Calendar strToCal(String timestamp)  {
		Calendar cal = GregorianCalendar.getInstance();
		try {
			
			Date parse = sf.parse(timestamp);
			cal.setTime(parse);
			
		} catch (Exception e) {}
		return cal;
	}
}
