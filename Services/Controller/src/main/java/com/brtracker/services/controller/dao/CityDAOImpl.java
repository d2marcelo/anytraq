package com.brtracker.services.controller.dao;

import java.util.List;
import com.brtracker.shared.payload.controller.data.City;

// TODO: Auto-generated Javadoc
/**
 * The Class CityDAOImpl.
 */
public class CityDAOImpl extends ControllerDAOImpl {

	
	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the city
	 */
	public City get (Long id) {
		List<City> list = (List<City>) find("from City where id="+id);
		if (list.isEmpty()) return null;
		return list.get(0);
	}
	
	/**
	 * List.
	 *
	 * @param stateId the state id
	 * @return the list
	 */
	public List<City> listCity (Long stateId) {
		return (List<City>) find("from City where state.id="+stateId);
	}

}
