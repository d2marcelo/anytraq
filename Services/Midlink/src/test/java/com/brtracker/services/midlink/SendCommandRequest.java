package com.brtracker.services.midlink;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import sun.util.calendar.Gregorian;

import com.brtracker.shared.payload.controller.lookup.DeviceModel;
import com.brtracker.shared.payload.controller.lookup.DeviceType;
import com.brtracker.shared.payload.midlink.DeviceEvent;
import com.brtracker.shared.payload.midlink.MobileDeviceEventType;
import com.brtracker.shared.payload.midlink.MobileDeviceMessage;
import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.json.JSONMapper;

public class SendCommandRequest {

	public static void main(String[] args) {
//		System.out.println(System.currentTimeMillis()-30000);
		sendMessage();
//		checkTimezone();
	}

	private static void checkTimezone() {
		
		String TimeZoneIds[] = TimeZone.getAvailableIDs();
		  for(int i = 0; i < TimeZoneIds.length; i++) {
			  TimeZone tz = TimeZone.getTimeZone(TimeZoneIds[i]);
			  System.out.print(TimeZoneIds[i] + ": ");
		  }
	}
	private static void sendMessage() {
		ConnectionFactory activeMQFactory = null;
		Connection connection = null;
		Session session = null;
		TextMessage jmsMessage = null;
		
		try {
			
			activeMQFactory = new ActiveMQConnectionFactory("tcp://107.20.202.100:61616");
			connection = activeMQFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			 
			String message = createMobileMessage();
//			String message = createXirgoCommands()[0];
			jmsMessage = session.createTextMessage(message);
			
//			String queueName = SystemConfiguration.NETTY;
			String queueName = SystemConfiguration.NOTIFICATION;
			
			Queue queue = session.createQueue(queueName);
	       	MessageProducer queueProducer = session.createProducer(queue);
	       	queueProducer.send(jmsMessage);
	       	System.out.println("Message send " + message);
	       	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
				if (connection != null) {
					connection.close();
				}
			}catch (Exception e) {
				
			}
		}
	}
	private static String createMessage() throws Exception {
		DeviceEvent deviceEvent = new DeviceEvent();
		deviceEvent.setEventType(MobileDeviceEventType.END_SESSION.ordinal());
		deviceEvent.setTimestamp(System.currentTimeMillis());
		deviceEvent.setUnitAddress("045110233016");
		return JSONMapper.toString(deviceEvent);
	}
	
	private static String createMobileMessage() throws Exception {
		MobileDeviceMessage mdm = new MobileDeviceMessage();
		mdm.setAltitude(0.0);
		mdm.setAccuracy(0.0);
		mdm.setAltitudeAccuracy(0.0);
		mdm.setDeviceAddress("12341234");
		mdm.setDeviceModel(DeviceModel.IPHONE);
		mdm.setDeviceType(DeviceType.SMARTPHONE);
		mdm.setHeading(35.0);
		mdm.setLatitude(0.0);
		mdm.setLongitude(0.0);
		mdm.setMobileDeviceEventType(MobileDeviceEventType.GPS_AVAILABLE);
		mdm.setSpeed(13.0);
		return JSONMapper.toString(mdm);
	}
	
	private static Calendar dateInThePast() {
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("America/Los_Angeles"));
		System.out.println(cal.getTime());
		System.out.println(cal.getTimeZone());
		cal.add(Calendar.MINUTE, -5);
		return cal;
	}
	private static String configureApnXirgoCommand() {
		String p = "{\"templateKey\":\"set_device_host\",\"recipients\":[\"5134886509\"],\"locale\":\"en_US\",\"parameters\":[\"10.10.30.133\",\"3001\"],\"type\":\"sms\"}";
		return p;
	}

	private static String createChangeIp() {
		String p = "{\"templateKey\":\"set_device_host\",\"recipients\":[\"5134886509\"],\"locale\":\"en_US\",\"parameters\":[\"10.10.30.133\",\"3001\"],\"type\":\"sms\"}";
		return p;
	}
	private static String createSmsMessage() {
		//String p = "{\"templateKey\":\"over_speed_alert\",\"recipients\":[\"6296088430\"],\"locale\":\"pt_BR\",\"scheduledTime\":\"1331982548908\",\"parameters\":[\"Fermin Ordaz\",\"beyond-80\",\"Sat, 17 Mar 2012 04:11:02 -0700\",\"80 kph\",\"115 kph\"],\"type\":\"sms\"}";
		
		String p = "{\"templateKey\":\"over_speed_alert\",\"recipients\":[\"4082033850\"],\"locale\":\"en_US\"," +
				"\"scheduledTime\":\"" + dateInThePast().getTimeInMillis() + "\",\"parameters\":[\"Fermin Ordaz\",\"beyond-80\",\"Sat, 17 Mar 2012 04:11:02 -0700\",\"80 kph\",\"115 kph\"],\"type\":\"sms\"}";
		return p;
	}
	
	private static String[] createXirgoCommands() {
		List<String> commands = new ArrayList<String>();
		String cmd = "{ \"templateKey\": \"xirgo.set.apn\", " +
				"\"recipients\": [\"15136939991\"], " +
				"\"locale\": " +
				"\"en_US\", " +
				"\"scheduledTime\":\"" + dateInThePast().getTimeInMillis() +
				"\"parameters\": [\"\", \"\", \"m2m.t-mobile.com\"], \"type\": \"sms\" }";
		commands.add(cmd);
		return commands.toArray(new String[] {});
	}

	private static String createMonitoringMessage() {
		return "{\"nagiosKey\":\"12345\"}";
	}

}
