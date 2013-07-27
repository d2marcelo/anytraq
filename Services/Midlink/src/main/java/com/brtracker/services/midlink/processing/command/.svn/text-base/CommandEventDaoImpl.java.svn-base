package com.brtracker.services.midlink.processing.command;

import java.util.Date;
import java.util.List;

import com.brtracker.shared.payload.midlink.CommandState;
import com.brtracker.shared.utils.spring.HibernateHelper;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class CommandEventDaoImpl extends HibernateHelper implements CommandEventDao {

	@Override
	public void save(CommandEventEntity event) throws HibernateHelperException {
		super.saveOrUpdate(event);
	}

	@Override
	public List<CommandEventEntity> findCommandEventsByAccountInfo(
			Long accountUserId, Long affiliateUserId) {
		
		List<CommandEventEntity> commands = super.find(
				"find.by.account.info", 
				new String[] {"accountUserId", "affiliateUserId"}, 
				new Object[] {accountUserId, affiliateUserId});
		
		return commands;
	}

	@Override
	public List<CommandEventEntity> findCommandEventsByAffiliateInfo(
			Long affiliateUserId) {
		
		List<CommandEventEntity> commands = super.find(
				"find.by.affiliate.info", 
				new String[] {"affiliateUserId"}, 
				new Object[] {affiliateUserId});
		
		return commands;
	}

	@Override
	public CommandEventEntity getById(Long id) {
		return super.get(CommandEventEntity.class, id);
	}

	@Override
	public CommandEventEntity findByMessageSeq(Integer messageSeq) throws HibernateHelperException {
		
		List<CommandEventEntity> commands = super.find("find.by.message.seq", 
				new String[] {"messageSeq"}, new Object[] {messageSeq});
		
		CommandEventEntity result = null;
		if (commands != null && commands.size() == 1) {
			result = commands.get(0);
		} else {
			throw new HibernateHelperException("Unable to find message by message sequence " + messageSeq);
		}
		return result;
	}

	@Override
	public void logAuditRecord(CommandEventEntity e, CommandState newState,
			Long responsible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CommandEventEntity> findPendingRetryCommands(Date time) {

		List<CommandEventEntity> commands = super.find("find.pending.retry", 
				new String[] {"since"}, new Object[] {time});
		
		return commands;
		
	}

}
