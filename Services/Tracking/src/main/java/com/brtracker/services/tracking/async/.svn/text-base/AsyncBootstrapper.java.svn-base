package com.brtracker.services.tracking.async;

import javax.jms.MessageListener;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.SystemResourcesUtil.ActiveMQ;
import com.brtracker.shared.utils.activemq.ActiveMQConnection;
import com.brtracker.shared.utils.activemq.QueueName;
import com.brtracker.shared.utils.activemq.TopicName;


public class AsyncBootstrapper implements InitializingBean , DisposableBean {

	private MessageListener midlinkMesageListener;
	private MessageListener accountMesageListener;
	ActiveMQConnection connectionFactory;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		midlinkMesageListener = new MidlinkMessageComsumer();
		accountMesageListener = new AccountMessageComsumer();
		ActiveMQ activeMQ = SystemResourcesUtil.ActiveMQ.get();
		String url = activeMQ.getUrl() +":"+activeMQ.getPort();
		connectionFactory = ActiveMQConnection.get(url);
		connectionFactory.createMessageProducer(AsyncMessageProducer.getInstance(), QueueName.ASYNC);
		connectionFactory.createMessageListener(midlinkMesageListener, TopicName.MIDLINK_TOPIC);
		connectionFactory.createMessageListener(accountMesageListener,"ACCOUNT_TOPIC");
   }

	@Override
	public void destroy() throws Exception {
		connectionFactory.shutdown();
	}
	
}
