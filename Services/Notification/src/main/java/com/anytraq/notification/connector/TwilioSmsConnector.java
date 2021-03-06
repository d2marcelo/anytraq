package com.anytraq.notification.connector;

import static com.brtracker.shared.utils.SystemConfiguration.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.anytraq.notification.service.NotificationEntity;
import com.anytraq.notification.service.NotificationServiceException;
import com.anytraq.notification.service.NotificationServiceImpl;
import com.anytraq.notification.service.NotificationTemplateEntity;
import com.anytraq.notification.service.NotificationServiceException.Reason;
import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;

@Component("twilioSmsConnector")
public class TwilioSmsConnector implements NotificationConnector,
		InitializingBean {

	private MyLogger logger = new MyLogger(NotificationServiceImpl.class);
	
	@Autowired
	@Qualifier("systemConfiguration")
	private SystemConfiguration systemConfiguration;
	
    private String twilioAccountId;
	private String twilioAccountSecret;

	@Override
	public void afterPropertiesSet() throws Exception {
		initialize();
	}

	private void initialize() {
		InputStream credentialsStream = null;
		try {
			
			logger.logInfo("Inititializing Twilio connector ....");
			
			// TODO: move out credentials from property file to a keystore
			credentialsStream = TwilioSmsConnector.class
				.getClassLoader().getResourceAsStream("TwilioCredentials.properties");

			Properties p = new Properties();
			p.load(credentialsStream);
			
	        twilioAccountId = p.getProperty("twilio_account_id");
			twilioAccountSecret = p.getProperty("twilio_account_secret");
			
		} catch (Exception e) {
			throw new NotificationServiceException(
					"Unexpected exception while creating http client", e, Reason.SMS_INIT_ERROR);
		} finally {
			if (credentialsStream != null) {
				try {
					credentialsStream.close();
				} catch (IOException e) {
					logger.logError("Unable to close input string", e);
				}
			}
		}
	}

	@Override
	public void sendNotification(NotificationEntity n, String formattedMessage,
			NotificationTemplateEntity template) {

		String sendSmsPath = systemConfiguration.getConfigElement(NOTIFICATION, NOTIFICATION_TWILIO_SMS_PATH);
		String smsFrom = systemConfiguration.getConfigElement(NOTIFICATION, NOTIFICATION_TWILIO_FROM_SMS);

		DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(sendSmsPath);

        try {

        	logger.logInfo("sending SMS with Twilio connector ....");
        	
			String twilioHost = systemConfiguration.getConfigElement(NOTIFICATION, NOTIFICATION_TWILIO_API_HOST);
			
			HttpHost targetHost = new HttpHost(twilioHost, 443, "https");
			
			httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                    new UsernamePasswordCredentials(twilioAccountId , twilioAccountSecret));

        	// Create AuthCache instance
            AuthCache authCache = new BasicAuthCache();
            
            // Generate BASIC scheme object and add it to the local
            // auth cache
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(targetHost, basicAuth);

            // Add AuthCache to the execution context
            BasicHttpContext localcontext = new BasicHttpContext();
            localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("From", smsFrom));
            parameters.add(new BasicNameValuePair("To", n.getRecipient()));
            parameters.add(new BasicNameValuePair("Body", formattedMessage));
            
			HttpEntity postEntity = new UrlEncodedFormEntity(parameters);
			httppost.setEntity(postEntity);
            
			logger.logInfo("sending SMS with Twilio connector ...." + n.getRecipient());
			
            HttpResponse response = httpclient.execute(targetHost, httppost, localcontext);
            int code = response.getStatusLine().getStatusCode();
            
            processResponse(code, response);

        } catch (NotificationServiceException e) {
        	throw e;
        } catch (Exception e) {
        	throw new NotificationServiceException("Unexpected exception while sending sms", e, Reason.SMS_SERVER_ERROR);
        } finally {
        	try {
        		httpclient.getConnectionManager().shutdown();
        	} catch (Exception e) {
        		
        	}
        }
	}

	private void processResponse(int code, HttpResponse response) {
		
		BufferedReader is  = null;
		try {
	    	HttpEntity entity = response.getEntity();
	    	
	    	if(entity != null) {
		    	StringBuilder details = new StringBuilder();
		        is = new BufferedReader(new InputStreamReader(entity.getContent()));
		        
		        StringBuilder sb = new StringBuilder();
		        String str = "";
		        while (str != null) {
		            str = is.readLine();
		            sb.append(str);
		        }
		        
		        
		        // parsing json payload only for failed requests 
		        if (code != HttpStatus.SC_CREATED) {
			        try {
			        	Json jsonResponse = Json.read(sb.toString());
			        	details.append("Twilio error: " + jsonResponse.at("code") + ":" + jsonResponse.at("message"));
			        } catch (Exception e) {
			        	details.append("Http error: " + response.getStatusLine());
			        }
			        EntityUtils.consume(entity);
			        
			        NotificationServiceException nse = new NotificationServiceException("Reporting sms exception");
			        nse.setDetails(details.toString());
			        throw nse;
		        }
	    	}
	        
		} catch (NotificationServiceException e) {
        	throw e;
        } catch (Exception e) {
        	throw new NotificationServiceException("Unexpected exception while reportHttpError", e, Reason.SMS_SERVER_ERROR);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
					// left blank intentionally
			}
		}
	}

	public void setSystemConfiguration(SystemConfiguration systemConfiguration) {
		this.systemConfiguration = systemConfiguration;
	}

}
