package com.brtracker.services.midlink.processing.command;

import java.util.Date;
import java.util.List;

import com.brtracker.shared.payload.midlink.CommandState;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public interface CommandEventDao {
	
	void save(CommandEventEntity event) throws HibernateHelperException;
	
	CommandEventEntity getById(Long id);

	List<CommandEventEntity> findCommandEventsByAffiliateInfo(
			Long affiliateUserId);
	
	List<CommandEventEntity> findCommandEventsByAccountInfo(
			Long accountUserId, Long affiliateUserId);
	
	void logAuditRecord(CommandEventEntity e, CommandState newState, Long responsible);

	CommandEventEntity findByMessageSeq(Integer messageSeq) throws HibernateHelperException;

	List<CommandEventEntity> findPendingRetryCommands(Date time);
}
