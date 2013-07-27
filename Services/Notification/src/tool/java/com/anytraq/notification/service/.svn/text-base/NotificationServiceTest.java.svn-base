package com.anytraq.notification.service;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import com.brtracker.shared.utils.json.Json;

public class NotificationServiceTest extends AbstractDependencyInjectionSpringContextTests {
	
	private NotificationService notificationService;
	private NotificationTemplateDao notificationTemplateDao;
	
	protected String[] getConfigLocations() {
        return new String[] { "classpath:spring/notification-test-beans.xml" };
    }

	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
		
		populateTemplate("geofencing_alert", "en_US", "email", 
				"On {0} {1} went out of the boundaries", "fermin@seabrightsoftware.com");
		
		populateTemplate("geofencing_alert", "en_US", "sms", 
				"On {0} your family member {1} exeeced the speed limit of {2} by {3}", "void");
	}
	
	@Override
	protected void onTearDown() throws Exception {
		super.onTearDown();
	}

	public void testSaveNotification() {
		Json payload = Json.read("{ \"templateKey\":\"geofencing_alert\", \"recipients\":[\"fermin@ordaz.com\", \"d2oliveira@gmail.com\"], \"type\":\"email\", \"locale\":\"en_US\", " +
				"\"parameters\":[\"01/01/2011\", \"someNameHere\"], \"scheduledTimee\":\"1234123974123098\" }");
		
		notificationService.saveNotification(payload);
	}

	public void testGetTemplate() {
		
		String templateKey = "geofencing_alert";
		String locale = "en_US";
		String type = "email";
		
		NotificationTemplateEntity templateEntity = notificationService
			.getNotificationTemplateByKey(templateKey, locale, type);
		
		System.out.println("templateEntity " + templateEntity);
	}

	public void testProcesPendingEmail() {
		
		Json payload = Json.read("{ \"templateKey\":\"geofencing_alert\", \"recipients\":[\"fermin@ordaz.com\", \"d2oliveira@gmail.com\"], " +
				"\"type\":\"email\", \"locale\":\"en_US\", " +
				"\"parameters\":[\"01/01/2011\", \"Meilyn\",\"90\",\"15\"] }");

		notificationService.saveNotification(payload);
		
		System.out.println("just waiting for  " + payload);
		
	}

	public void testProcesPendingSms() {
		
		Json payload = Json.read("{ \"templateKey\":\"geofencing_alert\", \"recipients\":[\"14082033850\"], " +
				"\"type\":\"sms\", \"locale\":\"en_US\", " +
				"\"parameters\":[\"01/01/2011\", \"Meilyn\", \"90\", \"15\"] }");
		
		notificationService.saveNotification(payload);
		
		System.out.println("just waiting for  " + payload);
		
	}

	private void populateTemplate(
			String templateKey, String locale, String type, String template, String sender) {

		NotificationTemplateEntity templateEntity = notificationService
			.getNotificationTemplateByKey(templateKey, locale, type);

		if (templateEntity == null) {
			
			NotificationTemplateEntity n = new NotificationTemplateEntity();
	
			n.setLocale(locale);
			n.setTemplate(template);
			n.setTemplateKey(templateKey);
			n.setType(type);
			n.setSender(sender);
			
			notificationTemplateDao.save(n);
		}

	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public void setNotificationTemplateDao(
			NotificationTemplateDao notificationTemplateDao) {
		this.notificationTemplateDao = notificationTemplateDao;
	}

}
