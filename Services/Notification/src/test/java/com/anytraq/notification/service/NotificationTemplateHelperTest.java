package com.anytraq.notification.service;

import junit.framework.Assert;
import junit.framework.TestCase;


public class NotificationTemplateHelperTest extends TestCase {

	public void testMessageFormating() {
		NotificationTemplateHelper h = new NotificationTemplateHelper();
		NotificationTemplateEntity template = new NotificationTemplateEntity();
		template.setTemplate("This is a sample template with one {0} and two {1} parameters");
		NotificationEntity notification = new NotificationEntity();
		notification.setPayload("{\"templateKey\":\"geofencing_alert\",\"locale\":\"en_US\",\"scheduledTime\":\"1319135406811\",\"parameters\":[\"Thu Oct 20 11:30:06 GMT-07:00 2011\",\"Marcelo Oliveira\"],\"type\":\"email\",\"recipient\":\"d2oliveira@gmail.com\"}");
		String formatNotificationMessage = h.formatNotificationMessage(template, notification);
		Assert.assertEquals("Test failed", "This is a sample template with one Thu Oct 20 11:30:06 GMT-07:00 2011 and two Marcelo Oliveira parameters", formatNotificationMessage);
	}
}
