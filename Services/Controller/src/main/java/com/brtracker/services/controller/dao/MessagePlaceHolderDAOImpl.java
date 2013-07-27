package com.brtracker.services.controller.dao;

import java.util.List;

import com.brtracker.shared.payload.controller.data.MessagePlaceHolderMap;

public class MessagePlaceHolderDAOImpl extends ControllerDAOImpl {

	public List<MessagePlaceHolderMap> listPlaceHolderMap (String msgKey) {
		return (List<MessagePlaceHolderMap>) super.find("from MessagePlaceHolderMap where messageKey ='"+msgKey+"'");
	}
}
