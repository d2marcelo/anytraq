package com.brtracker.services.appapi;


import com.brtracker.services.appapi.endpoint.GraphDataService;
import com.brtracker.shared.payload.appapi.AppAPIInterfaceLookup;
import com.brtracker.shared.payload.tracking.GraphDataRequest;
import com.brtracker.shared.payload.tracking.GraphDataResponse;
import com.brtracker.shared.payload.tracking.lookup.GraphDataType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ClientCall;

public class GraphDataTest extends BaseAppAPITest  {

	
	GraphDataService graphDataService = new GraphDataService();
	
	public void testGraphDataList () throws JSONMapperException {
		GraphDataRequest request = new GraphDataRequest();
		request.setDriverId(new Long(252));
		request.setDuration(30);
		request.setGraphDataType(GraphDataType.FUEL);
		String out = graphDataService.list(JSONMapper.toString(request));
		System.out.println(out);
		GraphDataResponse response= (GraphDataResponse) JSONMapper.toObject(out, GraphDataResponse.class);
		//super.assertEquals(true, response.getGraphDataPointsList());
	}
}
