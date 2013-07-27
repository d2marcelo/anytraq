package com.brtracker.shared.utils.packet;

import com.brtracker.shared.utils.packet.teltonika.GpsTeltonikaMessageTest;
import com.brtracker.shared.utils.packet.teltonika.PresentationTeltonikaMessageTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PacketReaderSuite extends TestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(ApplicationMessageTest.class);
		suite.addTestSuite(PresentationMessageTest.class);
		suite.addTestSuite(JBusMessageTest.class);
		suite.addTestSuite(PacketSerializationTest.class);
		suite.addTestSuite(GpsTeltonikaMessageTest.class);
		suite.addTestSuite(PresentationTeltonikaMessageTest.class);
		return suite;
	}
}
