package com.brtracker.services.controller;

import java.util.List;
import com.brtracker.services.controller.dao.CountryDAOImpl;
import com.brtracker.shared.payload.controller.CountryResponse;
import com.brtracker.shared.payload.controller.data.Country;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class CountryManager.
 */
public class CountryManager extends CountryDAOImpl {

	/**
	 * List.
	 *
	 * @return the string
	 */
	public String list (){
		try {
			List<Country> country = listCountry();
			CountryResponse response = new CountryResponse();
			response.setCountryList(country);
			return JSONMapper.toString(response);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	/**
	 * Adds the.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String add (String payload) {
		try {
			Country country = (Country) JSONMapper.toObject(payload, Country.class);
			Country dbCoutry = get(country.getCode());
			if (dbCoutry  != null)  ServiceResponse.getServiceResponse(true, "Country already added");
			add(country);
			return  ServiceResponse.getServiceResponse(true, "Country added successfuly");
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String delete (String payload) {
		try {
			Country country = (Country) JSONMapper.toObject(payload, Country.class);
			Long id = country.getId();
			if (id != null){
			delete(get(id));
			return  ServiceResponse.getServiceResponse(true, "Country deleted successfuly");
			} else {
			return  ServiceResponse.getServiceResponse(false, "Please provide the country id.");
			}
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
}
