package com.brtracker.services.async;

import java.util.Date;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.tracking.data.MessageAudit;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class AsyncAuditMessageListener implements MessageListener {

	private MyLogger logger = new MyLogger(AsyncAuditMessageListener.class, "asyncAuditMessageListener");
	private AsyncDao dao; 
	
	@Override
	public void onMessage(Message message) {
		TextMessage amqMessage = (TextMessage) message;
		logger.logInfo("Message Audit Message : "+amqMessage.toString());
		try {
			@SuppressWarnings("unchecked")
			Map<String,String> map = (Map<String, String>) JSONMapper.toObject(amqMessage.getText(), Map.class);
			MessageAudit msgAudit= new MessageAudit();
			msgAudit.setMessageType(map.get("messageType"));
			msgAudit.setParsedMessage(map.get("parsedMessage"));
			msgAudit.setRawMessage(map.get("rawMessage"));
			msgAudit.setUnitAddress(map.get("unitAddress"));
			msgAudit.setDateCreated(new Date(System.currentTimeMillis()));
			logger.logInfo("saving payload: "+JSONMapper.toString(msgAudit));
			dao.addOrUpdate(msgAudit);
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
		} catch (JMSException e) {
			logger.logError(e.getMessage(), e);
		} catch (HibernateHelperException e) {
			logger.logError(e.getMessage(), e);
		}
	}

	public AsyncDao getDao() {
		return dao;
	}

	public void setDao(AsyncDao dao) {
		this.dao = dao;
	}
	
	

}

