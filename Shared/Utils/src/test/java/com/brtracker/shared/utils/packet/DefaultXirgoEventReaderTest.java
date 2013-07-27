package com.brtracker.shared.utils.packet;

import java.util.Map;

import com.brtracker.shared.utils.packet.vendors.DefaultXirgoEventReader;

import junit.framework.TestCase;

public class DefaultXirgoEventReaderTest extends TestCase {

	private DefaultXirgoEventReader reader = new DefaultXirgoEventReader();
	public void testRead() {
		
		String message = "352054050522722,6011,2012/07/04,17:14:24,37.35749,-121.93662,-4.3,0,0,0,0,0.0,8,1.0,0,0.0,14.2,10,1,0.0,0";
		Map<String, Object> readRawMessage = reader.readRawMessage(message);
		System.out.println("====== " + readRawMessage);
	}
}
