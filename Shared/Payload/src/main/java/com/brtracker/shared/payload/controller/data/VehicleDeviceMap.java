package com.brtracker.shared.payload.controller.data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class VehicleDeviceMap {

	private Long id;
	private Device device;
	private Vehicle vehicle;
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
    @JoinColumn(name="device_id")
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	
	@ManyToOne
    @JoinColumn(name="vehicle_id")
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	@Override
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(this.getId());
		Device device = this.getDevice();
		if (device != null)
		b.append(device.toString());
		Vehicle vehicle = this.getVehicle();
		if (vehicle != null)
		b.append(vehicle.toString());
		return b.toString();
	}
	
}
