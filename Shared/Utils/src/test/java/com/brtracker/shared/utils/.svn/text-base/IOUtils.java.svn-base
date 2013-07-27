package com.brtracker.shared.utils;

import java.net.InetSocketAddress;
import java.util.Date;

import net.spy.memcached.MemcachedClient;

import com.brtracker.shared.utils.wsutils.StartJetty;

import junit.framework.Test;
import junit.framework.TestSuite;

public class IOUtils {

	public static Test suite() {
        TestSuite suite = new TestSuite("Test suite for com.xxx.yyyy.test");
        //$JUnit-BEGIN$
        
        //$JUnit-END$
        return suite;
	}
	
	public static void main(String[] args) {
		try {
			MemcachedClient c=new MemcachedClient(
	                new InetSocketAddress("72.167.54.78", 11211));
	       
	        c.set("someKey", 3600, new Date());
	        Object myObject=c.get("someKey");
	        System.out.println(myObject);
	        System.exit(1);
			StartJetty.run(8080);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}