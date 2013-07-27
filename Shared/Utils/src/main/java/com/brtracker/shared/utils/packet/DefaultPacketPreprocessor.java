package com.brtracker.shared.utils.packet;

import java.util.Map;

public class DefaultPacketPreprocessor implements PacketPreprocessor {

	@Override
	public void preprocess(PacketReaderContext context, Map<String, ?> e) {
		context.initializePacketFromRawData();
	}

}
