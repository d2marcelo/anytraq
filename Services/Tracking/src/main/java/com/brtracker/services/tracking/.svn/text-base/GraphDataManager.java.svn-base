package com.brtracker.services.tracking;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.brtracker.services.tracking.dao.GraphDataDAOImpl;
import com.brtracker.shared.payload.tracking.AggregatedTotal;
import com.brtracker.shared.payload.tracking.GraphCoordinate;
import com.brtracker.shared.payload.tracking.GraphDataRequest;
import com.brtracker.shared.payload.tracking.GraphDataResponse;
import com.brtracker.shared.payload.tracking.data.GraphData;
import com.brtracker.shared.payload.tracking.lookup.GraphDataType;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class GraphDataManager extends GraphDataDAOImpl {
	
	private MyLogger logger = new MyLogger(GraphDataManager.class);
	
	public String list (String payload) {
		try {
			GraphDataRequest graphDataRequest = (GraphDataRequest) JSONMapper.toObject(payload, GraphDataRequest.class);
			Long driverId =graphDataRequest.getDriverId();
			int duration =graphDataRequest.getDuration();
			GraphDataType type = graphDataRequest.getGraphDataType();
			List<GraphData> list = getGraphData(driverId, type.getId(), DateUtil.getDiffInHours(duration));
			return JSONMapper.toString(getGraphCoordinates(type, list));
		} catch (JSONMapperException e) {
			return ServiceResponseException.generateResponse(e);
		} catch (ParseException e) {
			return ServiceResponseException.generateResponse(e);
		}
		
	}
	
	private GraphDataResponse getGraphCoordinates (GraphDataType type, List<GraphData> graphDataList) throws ParseException{
		List<GraphCoordinate>  points = new ArrayList<GraphCoordinate>();
		GraphDataResponse response = new GraphDataResponse();
		DeviceGraphData graphData =  getGraphDataSummary(type);
		for (GraphData data: graphDataList){
			GraphCoordinate coordinate = new GraphCoordinate();
			String x = data.getxData();
			String y = data.getyData();
			String latitude = data.getLatitude();
			String longitude = data.getLongitude();
			Date dateCreated = data.getDataCreated();
			Date deviceTime = data.getDeviceTime();
			coordinate.setX(graphData.processX(x));
			coordinate.setY(graphData.processY(y));
			coordinate.setLatitude(latitude);
			coordinate.setLongitude(longitude);
			coordinate.setCourse(data.getCourse());
			coordinate.setDeviceTime(deviceTime.toString());
			coordinate.setDateCreated(dateCreated.toString());
			graphData.add(x, y, latitude, longitude, dateCreated);
			points.add(coordinate);
		}
		response.setCoordinates(points);
		response.setTotalCount(points.size());
		List<AggregatedTotal> aggregatedTotals = graphData.getTotals();
		response.setAggregatedTotals(aggregatedTotals);
		response.setxUnit(graphData.getXunit());
		response.setyUnit(graphData.getYunit());
		return response;
	}
	
	private DeviceGraphData getGraphDataSummary (GraphDataType type) {
		if (type.equals(GraphDataType.FUEL)){
			return new FuelGraphDataHandler();
		}
		if (type.equals(GraphDataType.SPEED)){
			return new SpeedGraphDataHandler();
		} else return null;
	}
	
	private List<GraphCoordinate> getGraphTotals (List<GraphData> graphData) throws ParseException{
		List<GraphCoordinate>  points = new ArrayList<GraphCoordinate>();
		int counter=  0;
		Float previous= null;
		Float total_previous = null;
		Float last_diff = null;
		for (GraphData data: graphData){
			GraphCoordinate coordinate = new GraphCoordinate();
			// this assumes x is always distance. need to change
			NumberFormat nf = NumberFormat.getInstance(Locale.getDefault()); // make locale-specific
			Float value = nf.parse(data.getxData()).floatValue();
			Float current;
			if (counter == 0){
				previous = value;
				current = new Float(0);
			} else {
				current = value - previous;
				previous = value;
			}
			if (current == null || last_diff == null) continue;
			total_previous = current + last_diff;
			last_diff = last_diff + current;
			counter++;
			
			coordinate.setX(total_previous.toString());
			// ===========
			coordinate.setY(data.getyData());
			coordinate.setLatitude(data.getLatitude());
			coordinate.setLongitude(data.getLongitude());
			coordinate.setDeviceTime(data.getDeviceTime().toString());
			coordinate.setDateCreated(data.getDataCreated().toString());
			points.add(coordinate);
			counter++;
		}
		return points;
	}
	
	

}
