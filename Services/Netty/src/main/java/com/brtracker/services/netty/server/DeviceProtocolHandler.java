package com.brtracker.services.netty.server;

import org.jboss.netty.channel.MessageEvent;

import com.brtracker.services.netty.MessageContext;

public interface DeviceProtocolHandler {
	void handleMessage(MessageContext messageContext, MessageEvent messageEvent);
}
