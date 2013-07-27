package com.brtracker.services.controller;

import java.util.List;
import com.brtracker.services.controller.dao.StateDAOImpl;
import com.brtracker.shared.payload.controller.StateResponse;
import com.brtracker.shared.payload.controller.data.State;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class StateManager.
 */
public class StateManager extends StateDAOImpl {


	/**
	 * List.
	 *
	 * @param countryId the country id
	 * @return the string
	 */
	public String list (Long countryId){
		try {
			List<State> state = listState(countryId);
			StateResponse response = new StateResponse();
			response.setStateList(state);
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
			State state = (State) JSONMapper.toObject(payload, State.class);
			add(state);
			return  ServiceResponse.getServiceResponse(true, "State added successfuly");
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
			State state = (State) JSONMapper.toObject(payload, State.class);
			Long id = state.getId();
			if (id != null){
			delete(super.get(id));
			return  ServiceResponse.getServiceResponse(true, "State deleted successfuly");
			} else {
			return  ServiceResponse.getServiceResponse(true, "Please provide the state id");	
			}
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (HibernateHelperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
}
