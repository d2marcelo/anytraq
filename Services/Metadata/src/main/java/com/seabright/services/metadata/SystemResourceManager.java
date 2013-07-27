package com.seabright.services.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.brtracker.shared.payload.metadata.Resources;
import com.brtracker.shared.payload.metadata.data.SystemResource;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;
import com.seabright.services.metadata.dao.MetadataDAOImpl;


public class SystemResourceManager extends MetadataDAOImpl {
	
	private static MyLogger logger = new MyLogger(SystemResourceManager.class);
	
	
	public String list ()  {
	logger.logInfo("list resources service:");
	try{
		List<SystemResource> list = super.getSystemResource();
		logger.logInfo("Resources: "+list.size());
		Resources resources = new Resources();
		Map<String,Map<String,String>> properties = new HashMap<String, Map<String,String>>();
		for (SystemResource sr : list){
			Map<String,String> map = (Map<String, String>) JSONMapper.toObject(sr.getProperties(), Map.class);
			properties.put(sr.getComponentName(), map);
		}
		resources.setProperties(properties);
		return JSONMapper.toString(properties);
	} catch (JSONMapperException e) {
		return ServiceResponseException.generateResponse(e);
	} 
	}


	public String put(String key, String att, String value) {
		try{
			List<SystemResource> list = super.getSystemResource();
			logger.logInfo("put : "+list.size());
			for (SystemResource sr : list){
				Map<String,String> map = (Map<String, String>) JSONMapper.toObject(sr.getProperties(), Map.class);
				if (sr.getComponentName().equals(key)){
					map.put(att, value);
					sr.setProperties(JSONMapper.toString(map));
					super.saveOrUpdate(sr);
					return "Data Saved Successfully : ";
				}
			}
			SystemResource sr = new SystemResource();
			Map<String,String> map  = new HashMap<String,String>();
			map.put(att, value);
			sr.setComponentName(key);
			sr.setProperties(JSONMapper.toString(map));
			super.saveOrUpdate(sr);
			return "Data Saved Successfully : ";
			
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			return ServiceResponseException.generateResponse(e);
		} 
	}
	
}
