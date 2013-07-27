package com.brtracker.shared.utils.wsutils;

import org.apache.wink.client.ClientAuthenticationException;
import org.apache.wink.client.ClientRuntimeException;
import org.apache.wink.client.ClientWebException;
import org.apache.wink.client.EntityType;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.apache.wink.client.ClientConfig;
import org.apache.wink.client.handlers.BasicAuthSecurityHandler;

import com.brtracker.shared.utils.logging.MyLogger;

public class ClientCall {

	private static MyLogger logger = new MyLogger(ClientCall.class);
	
	public static String get (String URL) throws ServiceException {
		logger.logInfo("GET: "+ URL);
		RestClient libraryClient = new RestClient(); 
        Resource editionResource = libraryClient.resource(URL); 
        editionResource.header("Accept", "application/json"); 
        try {
        String response =  editionResource.get(new EntityType<String>(){});
        logger.logInfo("RESPONSE: "+ response);
        return response;
        } catch (ClientWebException e){
			throw new ServiceException(e);
		} catch (ClientRuntimeException e){
			throw new ServiceException (e);
		}
	}
	
	public static String post (String URL, String payload ) throws ServiceException {
		logger.logInfo("POST: "+ URL +" PAYLOAD: "+payload);
		RestClient client = new RestClient(); 
		Resource resource = client.resource(URL);
		String response;
		try {
		  response = resource.contentType("application/json").accept("application/json").post(String.class, payload);
		  logger.logInfo("RESPONSE: "+ response);
		} catch (ClientWebException e){
			throw new ServiceException(e);
		} catch (ClientRuntimeException e){
			throw new ServiceException (e);
		}
		return response;
	}
	
	public static String get (String URL, String username, String password) throws ServiceException {
		String response;
		logger.logInfo("get: "+ URL +" USERNAME: "+username);
		 try {
		 ClientConfig config = new ClientConfig();
		 BasicAuthSecurityHandler basicAuthHandler = new BasicAuthSecurityHandler();
		 basicAuthHandler.setUserName(username);
		 basicAuthHandler.setPassword(password);
		 config.handlers(basicAuthHandler);
		 RestClient client = new RestClient(config);
		 Resource resource = client.resource(URL);
		 resource.header("Accept", "application/json"); 
	     response = resource.get(new EntityType<String>(){});
	     logger.logInfo("RESPONSE: "+ response);
		 } catch (ClientAuthenticationException e){
			 throw new ServiceException(e);
		}  catch (ClientWebException e){
			throw new ServiceException(e);
		}  catch (ClientRuntimeException e){
			throw new ServiceException (e);
		}
		return response;
	}

		
}
