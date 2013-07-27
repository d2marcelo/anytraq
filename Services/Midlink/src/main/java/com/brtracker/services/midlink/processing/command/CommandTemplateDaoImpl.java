package com.brtracker.services.midlink.processing.command;

import java.util.List;

import com.brtracker.shared.utils.spring.HibernateHelper;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class CommandTemplateDaoImpl extends HibernateHelper implements CommandTemplateDao {

	@Override
	public CommandTemplateEntity getCommandTemplate(String command,
			String deviceType) throws HibernateHelperException {
		
		return null;
	}

	@Override
	public List<CommandTemplateEntity> listCommandTemplates() {
		
		List<CommandTemplateEntity> templates = 
			super.listAll(CommandTemplateEntity.class);
		
		return templates;
	}

	@Override
	public CommandTemplateEntity getCommandTemplate(Long templateId)
			throws HibernateHelperException {
		return super.get(CommandTemplateEntity.class, templateId);
	}

	@Override
	public List<CommandTemplateEntity> listCommandTemplatesByNameKey(String nameKey)
			throws HibernateHelperException {
		List<CommandTemplateEntity> templates = super.find(
				"find.by.name.key", 
				new String[] {"nameKey"}, 
				new Object[] {nameKey});
		return templates;
	}

}
