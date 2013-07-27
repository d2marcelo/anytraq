package com.brtracker.services.midlink.processing;

import com.brtracker.shared.utils.wsutils.ServiceResponse;

public interface WsClient {
	ServiceResponse postRequest(Object message, String endpoint) throws Exception;
}
