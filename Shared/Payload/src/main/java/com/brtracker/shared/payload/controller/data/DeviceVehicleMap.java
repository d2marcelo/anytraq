package com.brtracker.shared.payload.controller.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brtracker.shared.payload.controller.ServiceAuth;

@Entity
@Table(name="device_vehicle_map")
public class DeviceVehicleMap extends ServiceAuth implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private Device device;
	private Vehicle vehicle;
	private Date dateCreated;
	@SuppressWarnings("unused")
	private Timestamp lastUpdated;
	
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
    @JoinColumn(name="device_id" ,nullable=false)
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	@ManyToOne
    @JoinColumn(name="vehicle_id", nullable=false)
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	@Column(name="date_created", nullable=false)
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Column(name="last_updated", nullable=false)
	public Timestamp getLastUpdated() {
		return new Timestamp(new Date().getTime());
	}
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
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
