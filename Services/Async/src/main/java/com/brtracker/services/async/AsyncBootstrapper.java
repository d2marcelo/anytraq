package com.brtracker.services.async;

import javax.jms.MessageListener;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.ActiveMQ;
import com.brtracker.shared.utils.activemq.ActiveMQConnection;
import com.brtracker.shared.utils.activemq.QueueName;
import com.brtracker.shared.utils.activemq.TopicName;
import com.brtracker.shared.utils.logging.MyLogger;



public class AsyncBootstrapper implements InitializingBean, DisposableBean  {

	private MessageListener asyncAccountListener;
	private MessageListener asyncMidlinkListener;
	private MessageListener asyncAuditListener;
	private ActiveMQConnection connectionFactory;
	
	private MyLogger logger = new MyLogger(AsyncBootstrapper.class);
	@Override
	public void afterPropertiesSet() throws Exception {
		ActiveMQ activeMQ = SystemResourcesUtil.ActiveMQ.get();
		String url = activeMQ.getUrl() +":"+activeMQ.getPort();
		connectionFactory = ActiveMQConnection.get(url);
		connectionFactory.createMessageListener(asyncAccountListener,TopicName.ACCOUNT_TOPIC);
		connectionFactory.createMessageListener(asyncMidlinkListener, QueueName.ASYNC);
		connectionFactory.createMessageListener(asyncAuditListener,QueueName.MSG_AUDIT);
   }
	

	public MessageListener getAsyncAccountListener() {
		return asyncAccountListener;
	}


	public void setAsyncAccountListener(MessageListener asyncAccountListener) {
		this.asyncAccountListener = asyncAccountListener;
	}


	public MessageListener getAsyncMidlinkListener() {
		return asyncMidlinkListener;
	}


	public void setAsyncMidlinkListener(MessageListener asyncMidlinkListener) {
		this.asyncMidlinkListener = asyncMidlinkListener;
	}


	public MessageListener getAsyncAuditListener() {
		return asyncAuditListener;
	}


	public void setAsyncAuditListener(MessageListener asyncAuditListener) {
		this.asyncAuditListener = asyncAuditListener;
	}


	public ActiveMQConnection getConnectionFactory() {
		return connectionFactory;
	}


	public void setConnectionFactory(ActiveMQConnection connectionFactory) {
		this.connectionFactory = connectionFactory;
	}


	@Override
	public void destroy() throws Exception {
		logger.logInfo("Destroying activemq connections");
		connectionFactory.shutdown();
	}
}
