package com.seabright.services.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.brtracker.shared.payload.metadata.data.SystemResource;

import com.brtracker.shared.utils.SystemResourcesUtil;
import com.brtracker.shared.utils.activemq.QueueName;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class SystemResourceTest {

	/**
	 * @param args
	 * @throws JSONMapperException 
	 * @throws HibernateHelperException 
	 */
	public static void main(String[] args) throws JSONMapperException, HibernateHelperException {
		
		
		SystemResourceManager manager = new SystemResourceManager();
		
		List<SystemResource> list = manager.getSystemResource();
		for (SystemResource sr : list){
			Map<String,String> map = (Map<String, String>) JSONMapper.toObject(sr.getProperties(), Map.class);
			if (sr.getComponentName().equals("ACTIVE_MQ")){
				map.put("MIDLINK", "MIDLINK_TOPIC");
				sr.setProperties(JSONMapper.toString(map));
				manager.saveOrUpdate(sr);
			}
		}
		System.exit(1);
		
		SystemResource systemResources = new SystemResource();
		systemResources.setComponentName("UI_ALERT");
		Map<String,String> props = new HashMap<String,String>();
		props.put("url", "https://login.anytraq.com/notify.jsp");
		systemResources.setProperties(JSONMapper.toString(props));
		//manager.saveOrUpdate(systemResources);

	}
/*
 * netty
 * tracking 8081
 * midlink  8081
 * async    8081
 * appapi   8080
 * metadata 8080
 * account  8080
 */
}
