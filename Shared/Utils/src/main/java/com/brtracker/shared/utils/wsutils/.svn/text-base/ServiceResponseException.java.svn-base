package com.brtracker.shared.utils.wsutils;

import java.io.PrintWriter;
import java.io.StringWriter;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;


public class ServiceResponseException  {

	public static String generateResponse(Throwable e) {
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setReturnValue(false);
		serviceResponse.setMessage(e.getMessage());
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		serviceResponse.setStackTrace(sw.toString());
		
		try {
			return JSONMapper.toString(serviceResponse);
		} catch (JSONMapperException e1) {
			return "ServiceResponseException - UNABLE TO GENERATE ERROR DUE TO JSON MAPPING FAILURE.";
		}
		
	}
}
