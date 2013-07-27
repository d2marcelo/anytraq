package com.brtracker.services.dataseed;

import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brtracker.shared.utils.ioutils.FileCopyUtil;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONPropertyAccessor;
import com.brtracker.shared.utils.wsutils.ClientCall;

@SuppressWarnings("unchecked")
public class GenerateGeoRoutes {

	private static final List<RouteDef> startStopAddresses;
	private static final String geoCodingUrlApi = "http://maps.googleapis.com/maps/api/geocode/json?address={0}&sensor=false";
	private static final String directionsUrlApi = "http://maps.googleapis.com/maps/api/directions/json?origin={0},{1}&destination={2},{3}&sensor=false&language=en";
	private JSONPropertyAccessor jsonAccessor = new JSONPropertyAccessor();
	private String routesDir = "src/test/resources/";
	
	static {
		startStopAddresses = new ArrayList<RouteDef>();
/*
		RouteDef d1 = new RouteDef("route-1", true,
				"Sao Miguel do Araguaia - Goias, Brazil", "Chapada dos Guimaraes - Mato Grosso");
		RouteDef d2 = new RouteDef("route-2", false,
				"Sao Miguel do Araguaia - Goias, Brazil", "Confresa - Mato Grosso, Brazil");
		RouteDef d3 = new RouteDef("route-3", false,
				"Madison ave, New York, NY", "James J Walker Park, New York, NY"); */
		RouteDef d1 = new RouteDef("fermin.house.palm", true,
				"1104 Lautrec Terrace, Sunnyvale, CA", "950 West Maude Avenue, Sunnyvale, CA");
		RouteDef d2 = new RouteDef("marcelo.house.barn", true,
				"1431 cedarmeadow ct, san jose, CA", "80 evans rd, Milpitas, CA");
		RouteDef d3 = new RouteDef("kerry.house.geo", true,
				"133 Alhambra Ave, Santa Cruz, CA", "80 evans rd, Milpitas, CA");
				
		startStopAddresses.add(d1);
		startStopAddresses.add(d2);
		startStopAddresses.add(d3);
	}

	public static void main(String[] args) {

		try {
			
			GenerateGeoRoutes genRoutes = new GenerateGeoRoutes();
			genRoutes.generateRouteSketch();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, List<GeoLeg>> getGeoPointCollection() throws Exception {

		Map<String, String> files = new HashMap<String, String>();
		for (RouteDef d : startStopAddresses) {
			if (!d.enabled) {
				continue;
			}
			files.put(d.name, routesDir + d.name + ".json");
		}
		Map<String, List<GeoLeg>> routeSketches = getRouteSketches(files);
		return routeSketches;
	}
	
	public Map<String, List<GeoLeg>> getRouteSketches(Map<String, String> routeFiles) 
		throws Exception {
		Map<String, List<GeoLeg>> points = new HashMap<String, List<GeoLeg>>();
		for (String k : routeFiles.keySet()) {
			List<GeoLeg> routeSketch = getRouteSketch(routeFiles.get(k));
			points.put(k, routeSketch);
		}
		return points;
	}
	
	public List<GeoLeg> getRouteSketch(String routeFile) throws Exception {
		
		Map<String, Object> responseMap = (Map<String, Object>) JSONMapper.load(routeFile, Map.class);
		List<Object> routes = (List<Object>) responseMap.get("routes");
		Map<String, Object> route = (Map<String, Object>) routes.get(0);
		List<Object> legs = (List<Object>) route.get("legs");
		Map<String, Object> leg = (Map<String, Object>) legs.get(0);
		List<Map<String, Object>> steps = (List<Map<String, Object>>) leg.get("steps");
		List<GeoLeg> sketch = new ArrayList<GeoLeg>();
		
		for (Map<String, Object> step : steps) {
			
			Double startLat = jsonAccessor.getProperty(Double.class, "start_location.lat", step);
			Double startLng = jsonAccessor.getProperty(Double.class, "start_location.lng", step);
			GeoPoint p1 = new GeoPoint(startLat, startLng);
			System.out.println("start " + p1);

			Double endLat = jsonAccessor.getProperty(Double.class, "end_location.lat", step);
			Double endLng = jsonAccessor.getProperty(Double.class, "end_location.lng", step);
			GeoPoint p2 = new GeoPoint(endLat, endLng);
			System.out.println("end " + p1);

			Double distance = jsonAccessor.getProperty(Double.class, "distance.value", step);
			GeoLeg l = new GeoLeg(p1, p2, distance);
			
			Long duration = jsonAccessor.getProperty(Long.class, "duration.value", step);
			l.duration = duration;
			
			sketch.add(l);
		}
		return sketch;
	}
	
	public void generateRouteSketch() throws Exception {
		
		for (RouteDef d : startStopAddresses) {

			if (!d.enabled) {
				System.out.println("Skipping route " + d.name);
				continue;
			}
			GeoPoint startPoint = geoCodeAddress(d.startAddress);
			if (startPoint == null) {
				System.out.println("Address " + d.startAddress + " could not be geo decoded");
				continue;
			} else {
				d.startPoint = startPoint;
			}
			System.out.println("start address processed " + d.startPoint);
			
			GeoPoint endPoint = geoCodeAddress(d.endAddress);
			if (endPoint == null) {
				System.out.println("Address " + d.endAddress + " could not be geo decoded");
				continue;
			} else {
				d.endPoint = endPoint;
			}
			System.out.println("end address processed " + d.endPoint);
			saveDirections(d);
		}
	}
	
	public void saveDirections(RouteDef d) throws Exception {

		String requestDirectionsUrl = MessageFormat.format(directionsUrlApi, 
				d.startPoint.lat, d.startPoint.lng, d.endPoint.lat, d.endPoint.lng);
		
		String response = ClientCall.get(requestDirectionsUrl);
		
		Map<String, Object> jsonResponse = (Map<String, Object>) JSONMapper.toObject(response, Map.class);
		List<Object> routes = (List<Object>) jsonResponse.get("routes");
		
		if (routes != null && routes.size() > 0) {
			FileCopyUtil.saveToFile(response, routesDir + d.name + ".json");
		} else {
			System.out.println("Invalid response for directions of " + d.name);
		}
		
		
	}
	
	public GeoPoint geoCodeAddress(String address) throws Exception {

		String start = URLEncoder.encode(address, "UTF-8");
		String requestUrl = MessageFormat.format(geoCodingUrlApi, start);
		String response = ClientCall.get(requestUrl);
		
		Map<String, Object> responseMap = (Map<String, Object>) 
			JSONMapper.toObject(response, Map.class);
		
		Object results = responseMap.get("results");
		if (results == null) {
			System.out.println("Address " + address + " could not be geo decoded, no results");
		}
		if (results instanceof List<?>) {
			List<Object> resultCollection = (List<Object>) results;
			Map<String, Object> r = (Map<String, Object>) resultCollection.get(0);
			
			if (r == null || r.size() == 0) {
				System.out.println("Address " + address + " could not be geo decoded, no result property");
			}
			JSONPropertyAccessor json = new JSONPropertyAccessor();
			
			Double lat = json.getProperty(Double.class, "geometry.location.lat", r);
			if (lat == null) {
				System.out.println("Address " + address + " could not be geo decoded, no lat");
				return null;
			}
			Double lng = json.getProperty(Double.class, "geometry.location.lng", r);
			if (lng == null) {
				System.out.println("Address " + address + " could not be geo decoded, no lng");
				return null;
			}
			return new GeoPoint(lat, lng);
		}
		return null;
	}

}
