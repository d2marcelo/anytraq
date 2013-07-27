package com.brtracker.services.midlink.processing.command;

import java.util.List;

import com.brtracker.shared.utils.spring.HibernateHelper;

public class CommandIOConfigDao extends HibernateHelper {
	
	public List<CommandIoConfigEntity> listAll() {
		return super.listAll(CommandIoConfigEntity.class);
	}
	
	public List<CommandIoConfigEntity> getByIoKey(String ioKey) {
		List<CommandIoConfigEntity> commands = super.find(
				"find.by.io.key", 
				new String[] {"ioKey"}, 
				new Object[] {ioKey});
		return commands;
	}

}
