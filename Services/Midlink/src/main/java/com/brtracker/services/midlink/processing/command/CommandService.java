package com.brtracker.services.midlink.processing.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.brtracker.shared.payload.controller.lookup.DeviceModel;
import com.brtracker.shared.payload.midlink.CommandIoConfig;
import com.brtracker.shared.payload.midlink.CommandRequest;
import com.brtracker.shared.payload.midlink.CommandRisk;
import com.brtracker.shared.payload.midlink.CommandState;
import com.brtracker.shared.payload.midlink.CommandTemplate;
import com.brtracker.shared.payload.midlink.DeviceCommand;
import com.brtracker.shared.payload.midlink.ListCommandIoConfig;
import com.brtracker.shared.payload.midlink.ListCommandTemplate;
import com.brtracker.shared.payload.midlink.ListDeviceCommand;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;

public class CommandService {
	
	private MyLogger logger = new MyLogger(CommandService.class);
	
	private CommandEventDao commandEventDao;
	private CommandTemplateDao commandTemplateDao;
	
	private CommandDispatcher commandDispatcher;
	private CommandValidator commandValidator;
	private CommandIOConfigDao commandIOConfigDao; 
	
	public ListCommandIoConfig listCommandIoConfig() {

		return mapCommandIoConfigEntities(commandIOConfigDao.listAll());
		
	}

	public ListCommandIoConfig listCommandIoConfigByIoKey(String ioKey) {
		
		return mapCommandIoConfigEntities(commandIOConfigDao.getByIoKey(ioKey));
		
	}
	
	public ListCommandTemplate listCommandTemplateByNameKey(String nameKey) {
		
		ListCommandTemplate ct = new ListCommandTemplate();
		try {
			
			
			List<CommandTemplateEntity> templateEntities = 
				commandTemplateDao.listCommandTemplatesByNameKey(nameKey);

			List<CommandTemplate> templates = mapCommandTemplates(templateEntities);
			ct.setCommandTemplates(templates);
			
		} catch (HibernateHelperException e) {
			logger.logError("Unexpected excetion ", e);
		}
		return ct;
	}
	
	public ListCommandTemplate listCommanTemplates() {
		
		List<CommandTemplateEntity> templateEntities = 
			commandTemplateDao.listCommandTemplates();
		
		ListCommandTemplate response = new ListCommandTemplate();
		List<CommandTemplate> templates = mapCommandTemplates(templateEntities);
		response.setCommandTemplates(templates);
		return response;
	}

	public String processCommand(CommandRequest request) {
		
		commandValidator.abortIfInvalidRequestCommand(request);
		try {
			commandDispatcher.dispatchCommand(request);
			return ServiceResponse.getServiceResponse(true, "Command processed successfully");
		} catch (Exception e) {
			return ServiceResponse.getSafeServiceResponse(false, e.getMessage());
		}
	}
	
	public ListDeviceCommand listCommands(Long accountUserId, Long affiliateUserId) {
		
		List<CommandEventEntity> commandEvents;
		
		if( accountUserId != 0){
			commandEvents = commandEventDao.findCommandEventsByAccountInfo(accountUserId, affiliateUserId);
		} else {
			commandEvents = commandEventDao.findCommandEventsByAffiliateInfo(affiliateUserId);
		}
		
		ListDeviceCommand response = new ListDeviceCommand();
		List<DeviceCommand> commands = new ArrayList<DeviceCommand>();

		for (CommandEventEntity e : commandEvents) {
			commands.add(mapCommandEventEntity(e));
		}
		
		response.setCommands(commands);
		
		return response;
	}
	
	public String approveCommand(Long affiliateUserId, Long commandId) {
		try {
			CommandEventEntity commandEvent = commandEventDao.getById(commandId);
			
			commandValidator.abortIfInvalidApproval(commandEvent);
			
			approveCommandEvent(affiliateUserId, commandEvent);
			
		} catch (Exception e) {
			return ServiceResponse.getSafeServiceResponse(false, e.getMessage());
		}
		return ServiceResponse.getSafeServiceResponse(true, "Command approved successfully");
	}
	
	public String cancelCommand(Long affiliateUserId, Long commandId) {
		try {
			CommandEventEntity commandEvent = commandEventDao.getById(commandId);
			
			commandValidator.abortIfInvalidCancelation(commandEvent);
			
			cancelCommandEvent(affiliateUserId, commandEvent);
			
		} catch (Exception e) {
			return ServiceResponse.getSafeServiceResponse(false, e.getMessage());
		}
		return ServiceResponse.getSafeServiceResponse(true, "Command canceled successfully");
	}

	private void cancelCommandEvent(Long affiliateUserId,
			CommandEventEntity commandEvent) throws HibernateHelperException {
		
		CommandState cancel = CommandState.CANCELED;
		
		commandEventDao.logAuditRecord(commandEvent, cancel, affiliateUserId);
		
		commandEvent.setState(cancel.name());
		
		commandEventDao.save(commandEvent);
	}

	private void approveCommandEvent(Long affiliateUserId, CommandEventEntity commandEvent) 
		throws HibernateHelperException {
		
		CommandState pendingDelivery = CommandState.PENDING_DELIVERY;
		
		commandEventDao.logAuditRecord(commandEvent, pendingDelivery, affiliateUserId);
		
		commandEvent.setState(pendingDelivery.name());
		
		commandEventDao.save(commandEvent);
	}

	private DeviceCommand mapCommandEventEntity(CommandEventEntity e) {
		
		DeviceCommand c = new DeviceCommand();
		
		c.setAccountUserId(e.getAccountUserId());
		c.setAffiliateUserId(e.getAffiliateUserId());
		c.setSecurityCode(e.getSecurityCode());
		c.setScheduledFor(e.getScheduledFor().getTime());
		c.setRetriesDelivery(e.getRetriesDelivery());
		c.setRetriesVerify(e.getRetriesVerify());
		c.setUpdatedOn(e.getLastUpdateOn().getTime());
		c.setCreatedOn(e.getCreatedOn().getTime());
		c.setState(CommandState.valueOf(e.getState()));
		c.setDeviceModel(DeviceModel.valueOf(e.getDeviceModel()));
		c.setRisk(CommandRisk.valueOf(e.getRisk()));
		c.setCommand(e.getCommand());
		c.setDeviceAdress(e.getDeviceId());
		c.setId(e.getId());
		
		return c;
	}

	private List<CommandTemplate> mapCommandTemplates(
			List<CommandTemplateEntity> templateEntities) {
		
		List<CommandTemplate> templates = new ArrayList<CommandTemplate>();
		for (Iterator<CommandTemplateEntity> iterator = templateEntities.iterator(); iterator.hasNext();) {
			
			CommandTemplateEntity e = iterator.next();
			
			CommandTemplate ct = new CommandTemplate();
			ct.setCommand(e.getCommand());
			ct.setDeviceModel(DeviceModel.valueOf(e.getDeviceModel()));
			ct.setId(e.getId());
			ct.setInputParams(e.getInputParameters());
			ct.setLabel(e.getLabelKey());
			ct.setRisk(CommandRisk.valueOf(e.getRisk()));
			
			templates.add(ct);
		}
		return templates;
	}

	private CommandIoConfig mapCommanIoConfigEntity(CommandIoConfigEntity e) {
		CommandIoConfig c = new CommandIoConfig();
		c.setId(e.getId());
		c.setIoKey(e.getIoKey());
		c.setNameKey(e.getNameKey());
		c.setState(e.getState());
		return c;
	}

	private ListCommandIoConfig mapCommandIoConfigEntities(
			List<CommandIoConfigEntity> commandIoConfigEntities) {
		
		ListCommandIoConfig lc = new ListCommandIoConfig();
		List<CommandIoConfig> commandIoConfigs = new ArrayList<CommandIoConfig>();
		for (CommandIoConfigEntity e: commandIoConfigEntities) {
			commandIoConfigs.add(mapCommanIoConfigEntity(e));
		}
		
		lc.setCommandIoConfigs(commandIoConfigs);
		return lc;
	}

	public void setCommandDispatcher(CommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
	}

	public void setCommandEventDao(CommandEventDao commandEventDao) {
		this.commandEventDao = commandEventDao;
	}

	public void setCommandTemplateDao(CommandTemplateDao commandTemplateDao) {
		this.commandTemplateDao = commandTemplateDao;
	}

	public void setCommandValidator(CommandValidator commandValidator) {
		this.commandValidator = commandValidator;
	}

	public void setCommandIOConfigDao(CommandIOConfigDao commandIOConfigDao) {
		this.commandIOConfigDao = commandIOConfigDao;
	}

}
