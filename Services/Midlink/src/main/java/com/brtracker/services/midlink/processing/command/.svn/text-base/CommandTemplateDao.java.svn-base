package com.brtracker.services.midlink.processing.command;

import java.util.List;

import com.brtracker.shared.utils.spring.HibernateHelperException;

public interface CommandTemplateDao {
	public CommandTemplateEntity getCommandTemplate(String command, String deviceType) 
		throws HibernateHelperException;
	
	public CommandTemplateEntity getCommandTemplate(Long templateId) 
		throws HibernateHelperException;

	public List<CommandTemplateEntity> listCommandTemplates();
	
	public List<CommandTemplateEntity> listCommandTemplatesByNameKey(
			String nameKey) throws HibernateHelperException;
}
