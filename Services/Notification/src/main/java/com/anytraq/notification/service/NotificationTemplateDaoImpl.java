package com.anytraq.notification.service;

import java.util.List;

import com.brtracker.shared.utils.spring.HibernateHelper;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class NotificationTemplateDaoImpl extends HibernateHelper implements NotificationTemplateDao {

	@Override
	public void save(NotificationTemplateEntity notificationTemplateEntity) {
		try {
			super.saveOrUpdate(notificationTemplateEntity);
		} catch (HibernateHelperException e) {
			throw new NotificationServiceException("Unexpected exception while saving notification template", e);
		}
	}

	@Override
	public NotificationTemplateEntity getNotificationTemplateByKey(
			String templateKey, String locale, String type) {
		
		List<NotificationTemplateEntity> templates = super.find(
				"find.by.primary.key", 
				new String[] {"templateKey", "locale", "type"}, 
				new Object[] {templateKey, locale, type});
		
		return templates != null && templates.size() > 0 ? templates.get(0) : null; 
	}
	
}
