package com.anytraq.notification.service;

import java.util.List;

import com.brtracker.shared.utils.spring.HibernateHelper;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class RecipientInfoDaoImpl extends HibernateHelper implements RecipientInfoDao {

	@Override
	public void save(RecipientInfoEntity recipient) {
		try {
			super.saveOrUpdate(recipient);
		} catch (HibernateHelperException e) {
			throw new NotificationServiceException("Unexpected exception while saving recipient info ", e);
		}
	}

	@Override
	public RecipientInfoEntity getRecipientByAddress(String address) {
		
		List<RecipientInfoEntity> templates = super.find(
				"find.by.address", new String[] {"address"}, new Object[] {address});
		
		return templates != null && templates.size() > 0 ? templates.get(0) : null; 
	}

}
