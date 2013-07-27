package com.seabright.services.metadata.dao;

import java.util.List;
import com.brtracker.shared.payload.metadata.data.SystemResource;
import com.brtracker.shared.utils.spring.Context;
import com.brtracker.shared.utils.spring.HibernateHelper;


// TODO: Auto-generated Javadoc
/**
 * The Class ControllerDAOImpl.
 */
public class MetadataDAOImpl extends HibernateHelper {

	
	/**
	 * Instantiates a new controller dao impl.
	 */
	public MetadataDAOImpl() {
		super.setSessionFactory(Context.getSessionFactory());
	}

	public List<SystemResource> getSystemResource() {
		return (List<SystemResource>) find("from SystemResource");
	}

	
}
