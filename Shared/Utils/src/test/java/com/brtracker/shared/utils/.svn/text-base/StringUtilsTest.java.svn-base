package com.brtracker.shared.utils;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StringUtilsTest extends TestCase {
	
	public void testSanitizePhoneNumber() {
		
		Assert.assertEquals("Invalid phone number", 
				StringUtil.sanitizePhoneNumber("01155(62) 8442-2953"), "011556284422953");

		Assert.assertEquals("Invalid phone number", 
				StringUtil.sanitizePhoneNumber(" 01155((62) --8442-2953"), "011556284422953");
	}
}
