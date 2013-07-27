package com.brtracker.shared.payload.controller;

import java.util.List;

import com.brtracker.shared.payload.controller.data.MessagePlaceHolderMap;

public class MessagePlaceHolderResponse {

	private List<MessagePlaceHolderMap> msgPlaceHolderList;

	public List<MessagePlaceHolderMap> getMsgPlaceHolderList() {
		return msgPlaceHolderList;
	}

	public void setMsgPlaceHolderList(List<MessagePlaceHolderMap> msgPlaceHolderList) {
		this.msgPlaceHolderList = msgPlaceHolderList;
	}
	
	
	
}
