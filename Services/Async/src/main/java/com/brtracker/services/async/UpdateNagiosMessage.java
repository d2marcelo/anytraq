package com.brtracker.services.async;

import java.util.Date;
import java.util.Map;
import com.brtracker.services.async.dao.AsyncDao;
import com.brtracker.shared.payload.tracking.data.NagiosMessageCheck;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class UpdateNagiosMessage  {

	private MyLogger logger = new MyLogger(UpdateNagiosMessage.class, "updateNagiosMessage");
	private AsyncDao dao; 
	
	
	public void onMessage(String message) {
		try {
			@SuppressWarnings("unchecked")
			Map<String,String> map = (Map<String, String>) JSONMapper.toObject(message, Map.class);
			NagiosMessageCheck msgCheck= new NagiosMessageCheck();
			msgCheck.setChecked(false);
			msgCheck.setDateCreated(new Date());
			msgCheck.setNagiosKey(map.get("nagiosKey"));
			logger.logInfo("saving payload: "+JSONMapper.toString(msgCheck));
			dao.addOrUpdate(msgCheck);
		} catch (JSONMapperException e) {
			logger.logError(e.getMessage(), e);
		}  catch (HibernateHelperException e) {
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
