package com.brtracker.services.async;

import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceException;

public class AsyncAccountMessageListener implements MessageListener {

	private MyLogger logger = new MyLogger(AsyncAccountMessageListener.class, this.getClass().getSimpleName());
	private UpdateVehicleStatus updateVehicleStatus;
	private AccountWSClient accountWSClient;
	
	@Override
	public void onMessage(Message message) {
		TextMessage amqMessage = (TextMessage) message;
		try {
			String deviceMessageAttribute = amqMessage.getText();
			logger.logInfo("Account message topic received. "+ deviceMessageAttribute);
			Map<String,String> obj = (Map<String, String>) JSONMapper.toObject(deviceMessageAttribute, Map.class);
			String objectType = obj.get("objectType");
			logger.logInfo("Expire Memcached");
			accountWSClient.expireMencached(Long.parseLong(obj.get("accountId")));
			if (!objectType.equals("ALERT")) {
				logger.logInfo("Update Vehicle Status");
				updateVehicleStatus.update(deviceMessageAttribute);
			}
		} catch (JMSException e) {
			logger.logError(e.getMessage(), e);
			e.printStackTrace();
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
			e.printStackTrace();
		} catch (ServiceException e) {
			logger.logError(e.getMessage(), e);
			e.printStackTrace();
		}  catch (HibernateHelperException e) {
			logger.logError(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public UpdateVehicleStatus getUpdateVehicleStatus() {
		return updateVehicleStatus;
	}

	public void setUpdateVehicleStatus(UpdateVehicleStatus updateVehicleStatus) {
		this.updateVehicleStatus = updateVehicleStatus;
	}

	public AccountWSClient getAccountWSClient() {
		return accountWSClient;
	}

	public void setAccountWSClient(AccountWSClient accountWSClient) {
		this.accountWSClient = accountWSClient;
	}
	
	

}

