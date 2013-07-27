package com.brtracker.services.tracking;

import com.brtracker.shared.payload.tracking.GraphDataRequest;
import com.brtracker.shared.payload.tracking.GraphDataResponse;
import com.brtracker.shared.payload.tracking.lookup.GraphDataType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;


public class GraphDataTest extends BaseTrackingTest {

	GraphDataManager graphDataManager = new GraphDataManager();
	
	public void testGraphDataList () throws JSONMapperException {
		GraphDataRequest request = new GraphDataRequest();
		request.setDriverId(new Long(252));
		request.setDuration(24);
		request.setGraphDataType(GraphDataType.FUEL);
		String out = graphDataManager.list(JSONMapper.toString(request));
		System.out.println(out);
		GraphDataResponse response= (GraphDataResponse) JSONMapper.toObject(out, GraphDataResponse.class);
		//super.assertEquals(true, response.getGraphDataPointsList());
	}
	

}
