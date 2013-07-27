package com.brtracker.shared.payload.controller;

import java.util.List;

public class SingleObjectWrapper {

	public SingleObjectWrapper (){};

	public List<?> list;

	public SingleObjectWrapper(List<?> list){
		this.list = list;
	}
	
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	} 
	
	
}
