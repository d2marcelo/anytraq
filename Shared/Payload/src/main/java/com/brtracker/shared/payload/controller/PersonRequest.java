package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.data.Person;
import com.brtracker.shared.payload.reporting.AbstractSearchCriteria;

public class PersonRequest extends AbstractSearchCriteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person person;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	
	

}
