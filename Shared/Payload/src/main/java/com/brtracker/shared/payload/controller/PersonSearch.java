package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.data.Person;
import com.brtracker.shared.payload.tracking.data.VehicleStatus;


public class PersonSearch {

	private Person person;
	private VehicleStatus vehicleStatus;
	
	
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}
	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	
	
	
}
