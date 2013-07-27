package com.brtracker.services.controller;

public class MqClientFactory {

	protected static MqClient mqClient;

	public static MqClient getMqClient() {
		return mqClient;
	}

	public void setMqClient(MqClient mqClient) {
		MqClientFactory.mqClient = mqClient;
	}
	
}
