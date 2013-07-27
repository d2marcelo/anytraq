package com.brtracker.services.midlink.processing.command;

import com.brtracker.shared.payload.controller.lookup.DeviceCommandState;
import com.brtracker.shared.payload.controller.lookup.DeviceModel;
import com.brtracker.shared.payload.midlink.CommandRequest;
import com.brtracker.shared.payload.midlink.CommandRisk;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class CommandValidator {
	
	private MyLogger logger = new MyLogger(CommandValidator.class);
	private CommandTemplateDao commandTemplateDao;
	
	public void abortIfInvalidRequestCommand(CommandRequest request) {
		try {
			
			request.setState(CommanState.PENDING_DELIVERY.name());
			
			CommandTemplateEntity commandTemplate = commandTemplateDao
				.getCommandTemplate(request.getCommandTemplateId());
			
			Long affiliateUserId = request.getAffiliateUserId();
			Long accountUserId = request.getAccountUserId();
			
			boolean affiliateIdPresent = affiliateUserId != null && affiliateUserId.intValue() > 0;
			boolean accountIdPresent = accountUserId != null && accountUserId.intValue() > 0;
			
			if (!affiliateIdPresent && !accountIdPresent) {
				throw new CommandProcessingException("MISSING_REQUESTOR_ID", 
					"The given command request is missing both account user id and affiliate user id, who's sending this command ?");
			}
			
			String risk = commandTemplate.getRisk();
			if (CommandRisk.HIGH.name().equals(risk) || CommandRisk.MEDIUM.name().equals(risk)) {
				
				String securityCode = request.getSecurityCode();
				boolean securityCodePresent = securityCode != null;
				
				// the user is requesting a high/medium command without security code
				if (!affiliateIdPresent && accountIdPresent && !securityCodePresent) {
					throw new CommandProcessingException("MISSING_SEC_CODE", 
						"The given high/medium command request is missing the security code");
				} 
				// the user is requesting a high/medium command with security code, marking the request as pending for approval
				if (!affiliateIdPresent && accountIdPresent && securityCodePresent) {
					logger.logInfo("Setting request state to approval pending ....");
					request.setState(CommanState.APPROVAL_PENDING.name());
				}
				
			}
			
			DeviceModel deviceModel = request.getDeviceModel();
			if (deviceModel == null) {
				throw new CommandProcessingException("MISSING_DEV_MODEL", 
					"The given command request is missing the device model");
			}
			
			String deviceAddress = request.getDeviceAddress();
			if (deviceAddress == null) {
				throw new CommandProcessingException("MISSING_DEV_ADDRESS", 
					"The given command request is missing the device address");
			}
			
			if (commandTemplate.getAdminRole() && 
					(affiliateUserId == null || affiliateUserId.intValue() < 0) ) {
				throw new CommandProcessingException("MISSING_AFFIL_ID", 
						"The given ADMIN command template requires affiliate id in the command request");
			}
			
		} catch (HibernateHelperException e) {
			throw new CommandProcessingException("INVALID_TEMPLATE_ID", 
					"The given command template id does not exists");
		} catch (CommandProcessingException e) {
			throw e;
		}
	}
	
	public void abortIfInvalidApproval(CommandEventEntity request) {
		
		String state = request.getState();
		if (state == null || !DeviceCommandState.APPROVAL_PENDING.name().equals(state)) {
			throw new CommandProcessingException("INVALID_COMMAND_STATE", 
				"Approve commands requires an a APPROVAL_PENDING commnand");
		}
		Long affiliateUserId = request.getAffiliateUserId();
		if (affiliateUserId == null || affiliateUserId.intValue() < 0) {
			throw new CommandProcessingException("MISSING_APPROVER_ID", 
				"The given command request cannot be approved without the affiliate id");
		}
	}
	
	public void abortIfInvalidCancelation(CommandEventEntity request) {
		String state = request.getState();
		if (state != null && (DeviceCommandState.DELIVERED.name().equals(state) ||
				DeviceCommandState.VERIFIED.name().equals(state))) {
			throw new CommandProcessingException("INVALID_COMMAND_STATE", 
				"Cannot cancel an already sent or verified command");
		}
	}

	public void setCommandTemplateDao(CommandTemplateDao commandTemplateDao) {
		this.commandTemplateDao = commandTemplateDao;
	}

}
