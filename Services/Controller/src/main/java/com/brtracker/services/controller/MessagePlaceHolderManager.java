package com.brtracker.services.controller;

import com.brtracker.services.controller.dao.MessagePlaceHolderDAOImpl;
import com.brtracker.shared.payload.controller.MessagePlaceHolderResponse;
import com.brtracker.shared.payload.controller.data.MessagePlaceHolderMap;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.spring.HibernateHelperException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class MessagePlaceHolderManager extends MessagePlaceHolderDAOImpl {
	
	public String addPlaceHolder (String payload) {
		try {
			MessagePlaceHolderMap msg = (MessagePlaceHolderMap) JSONMapper.toObject(payload, MessagePlaceHolderMap.class);
			super.saveOrUpdate(msg);
			return ServiceResponse.getServiceResponse(true,"Message Place Holder added successfully");
		} catch (HibernateHelperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}
	
	public String listPlaceHolder (String msgKey) {
		try {
			MessagePlaceHolderResponse resp = new MessagePlaceHolderResponse();
			resp.setMsgPlaceHolderList(super.listPlaceHolderMap(msgKey));
			return JSONMapper.toString(resp);
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}

}
