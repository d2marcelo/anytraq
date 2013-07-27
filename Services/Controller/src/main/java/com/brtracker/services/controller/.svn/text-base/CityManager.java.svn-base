package com.brtracker.services.controller;

import java.util.List;

import com.brtracker.services.controller.dao.CityDAOImpl;
import com.brtracker.shared.payload.controller.CityResponse;
import com.brtracker.shared.payload.controller.data.City;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class CityManager.
 */
public class CityManager extends CityDAOImpl {

	/**
	 * List.
	 *
	 * @param stateId the state id
	 * @return the string
	 */
	public String list (Long stateId){
		try {
			List<City> city = super.listCity(stateId);
			CityResponse response = new CityResponse();
			response.setCityList(city);
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
			City city = (City) JSONMapper.toObject(payload, City.class);
			add(city);
			return  ServiceResponse.getServiceResponse(true, "City added successfuly");
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
			City city = (City) JSONMapper.toObject(payload, City.class);
			Long id = city.getId();
			if (id != null) {
				delete(get(id));
				return  ServiceResponse.getServiceResponse(true, "City deleted successfuly");
			} else {
				return  ServiceResponse.getServiceResponse(false, "Please provide the city id");
			}
			
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
}
