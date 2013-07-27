package com.brtracker.services.async;

import java.util.ArrayList;
import java.util.List;
import com.brtracker.shared.utils.logging.MyLogger;

public class AsyncMessageHandler implements MessageHandler {
	
	private List<AsyncAction> actions = new ArrayList<AsyncAction>();
	private MyLogger logger = new MyLogger(AsyncMessageHandler.class, "asyncMessageHandler");
	private MyThreadPoolExecutor es = new MyThreadPoolExecutor();
	
	@Override
	public  void handle(String message) {
		try {
		for (AsyncAction action: actions) {
				action.setMessage(message);
				es.execute(action);
		}
		logger.logInfo("waiting for threads to finish");
		es.waitForExecuted();
		logger.logInfo("threads finished!!");
		logger.logInfo("=============================");
		} catch (Exception e) {
			es.shutdown();
			logger.logError("Unexpected exception executing action", e);
		}
		
	}
	
	public void setActions(List<AsyncAction> actions) {
		this.actions = actions;
	}

	
}
