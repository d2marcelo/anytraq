package com.brtracker.services.netty.server;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import com.brtracker.shared.utils.logging.MyLogger;

public class NettyServer implements InitializingBean, DisposableBean {

	private MyLogger logger = new MyLogger(NettyServer.class);

	private List<NettyModule> nettyModules;
	
	private void start() {

		try {
			// starting device specific modules
			for (NettyModule s : nettyModules) {
				s.start();
			}
		} catch (Exception e) {
			logger.logError("Unexpecpted exception during Netty bootstrap process", e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

	public void setNettyModules(List<NettyModule> nettyModules) {
		this.nettyModules = nettyModules;
	}

	@Override
	public void destroy() throws Exception {
		logger.logInfo("Shutting down Netty Server");
		for (NettyModule m: nettyModules) {
			try {
				m.shutdown();
			} catch (Exception e) {
				logger.logError("Error shutting down netty module", e);
			}
		}
	}

}
