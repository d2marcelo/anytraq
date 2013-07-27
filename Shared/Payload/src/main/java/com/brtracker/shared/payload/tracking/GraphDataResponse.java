package com.brtracker.shared.payload.tracking;

import java.io.Serializable;
import java.util.List;


import com.brtracker.shared.payload.reporting.AbstractSearchResponse;
import com.brtracker.shared.payload.tracking.lookup.UnitType;


public class GraphDataResponse  extends AbstractSearchResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<GraphCoordinate> coordinates;
	private List<AggregatedTotal> aggregatedTotals;
	private String xUnit;
	private String yUnit;
	
	public List<GraphCoordinate> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<GraphCoordinate> coordinates) {
		this.coordinates = coordinates;
	}
	public String getxUnit() {
		return xUnit;
	}
	public void setxUnit(String xUnit) {
		this.xUnit = xUnit;
	}
	public String getyUnit() {
		return yUnit;
	}
	public void setyUnit(String yUnit) {
		this.yUnit = yUnit;
	}
	public List<AggregatedTotal> getAggregatedTotals() {
		return aggregatedTotals;
	}
	public void setAggregatedTotals(List<AggregatedTotal> aggregatedTotals) {
		this.aggregatedTotals = aggregatedTotals;
	}
	
	

	
}
