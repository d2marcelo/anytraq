package com.brtracker.services.controller.dao;

import java.util.List;
import com.brtracker.shared.payload.controller.data.State;

// TODO: Auto-generated Javadoc
/**
 * The Class StateDAOImpl.
 */
public class StateDAOImpl  extends ControllerDAOImpl {

	
	
	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the state
	 */
	public State get (Long id) {
		List<State> list = (List<State>) find("from State where id="+id);
		if (list.isEmpty()) return null;
		return list.get(0);
	}
	
	/**
	 * List.
	 *
	 * @param countryId the country id
	 * @return the list
	 */
	public List<State> listState (Long countryId) {
		return (List<State>) find("from State where country.id="+countryId);
	}

}
