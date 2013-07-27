package com.anytraq.notification.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
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

public class TestTwilio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//        HttpHost targetHost = new HttpHost("api.twilio.com", 443, "https");
//
//        DefaultHttpClient httpclient = new DefaultHttpClient();
//        try {
//            httpclient.getCredentialsProvider().setCredentials(
//                    new AuthScope(targetHost.getHostName(), targetHost.getPort()),
//                    new UsernamePasswordCredentials("ACb374e42e35a64ba8887b0c66d0569f9f", "d8c0f4f419cb89e800dd88d3bb05d71a"));
//
//            // Create AuthCache instance
//            AuthCache authCache = new BasicAuthCache();
//            // Generate BASIC scheme object and add it to the local
//            // auth cache
//            BasicScheme basicAuth = new BasicScheme();
//            authCache.put(targetHost, basicAuth);
//
//            // Add AuthCache to the execution context
//            BasicHttpContext localcontext = new BasicHttpContext();
//            localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
//
//            HttpPost httppost = new HttpPost("/2010-04-01/Accounts/ACb374e42e35a64ba8887b0c66d0569f9f/SMS/Messages.json");
//
//            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
//            parameters.add(new BasicNameValuePair("From", "14155992671"));
//            parameters.add(new BasicNameValuePair("To", "14087442176"));
//            parameters.add(new BasicNameValuePair("Body", "seabright alert: speed limit exceeded"));
//            
//			HttpEntity postEntity = new UrlEncodedFormEntity(parameters);
//			httppost.setEntity(postEntity);
//            
//            System.out.println("executing request: " + httppost.getRequestLine());
//            System.out.println("to target: " + targetHost);
//
//            for (int i = 0; i < 3; i++) {
//                HttpResponse response = httpclient.execute(targetHost, httppost, localcontext);
//                HttpEntity entity = response.getEntity();
//                int code = response.getStatusLine().getStatusCode();
//                System.out.println(code);
//                BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
//                String str = "";
//                while (str != null) {
//                    str = in.readLine();
//                    System.out.print(str);
//                }                
//                System.out.println("----------------------------------------");
//                System.out.println(response.getStatusLine());
//                if (entity != null) {
//                    System.out.println("Response content length: " + entity.getContentLength());
//                }
//                EntityUtils.consume(entity);
//            }
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//        } finally {
//            // When HttpClient instance is no longer needed,
//            // shut down the connection manager to ensure
//            // immediate deallocation of all system resources
//            httpclient.getConnectionManager().shutdown();
//        }
//
	}

}
