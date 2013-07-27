package com.brtracker.shared.payload.reporting;

import java.util.List;

import com.brtracker.shared.payload.tracking.Coordinate;
import com.brtracker.shared.payload.tracking.lookup.UnitType;

public class ReportResponse {

	private String value;
	private UnitType unitType;
	private List<Coordinate> coordinates;
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public UnitType getUnitType() {
		return unitType;
	}
	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}
	public List<Coordinate> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
