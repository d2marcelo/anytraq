package com.brtracker.services.midlink.processing;

import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceResponse;

public class DefaultWsClient implements WsClient {

	@Override
	public ServiceResponse postRequest(Object message, String endpoint)
			throws Exception {

		String payload = JSONMapper.toString(message);
		String response = ClientCall.post(endpoint, payload);
		ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(response, ServiceResponse.class);
		return resp;
	}

}
