package com.brtracker.shared.payload.tracking;

import com.brtracker.shared.payload.tracking.lookup.GraphDataType;

public class GraphDataRequest extends AbstractReportRequest {
	
	private static final long serialVersionUID = 1L;
	private GraphDataType graphDataType;
	
	public GraphDataType getGraphDataType() {
		return graphDataType;
	}
	public void setGraphDataType(GraphDataType graphDataType) {
		this.graphDataType = graphDataType;
	}
	
	
	
	
	
	
	
	

}
