package com.brtracker.services.controller.dao;

import java.util.List;
import com.brtracker.shared.payload.controller.data.Country;

// TODO: Auto-generated Javadoc
/**
 * The Class CountryDAOImpl.
 */
public class CountryDAOImpl extends ControllerDAOImpl {

	
	/**
	 * Gets the.
	 *
	 * @param code the code
	 * @return the country
	 */
	public Country get (String code) {
		List<Country> list = (List<Country>) find("from Country where code='"+code+"'");
		if (list.isEmpty()) return null;
		return list.get(0);
	}
	
	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the country
	 */
	public Country get (Long id) {
		List<Country> list = (List<Country>) find("from Country where id="+id);
		if (list.isEmpty()) return null;
		return list.get(0);
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Country> listCountry () {
		return (List<Country>) find("from Country");
	}

}
