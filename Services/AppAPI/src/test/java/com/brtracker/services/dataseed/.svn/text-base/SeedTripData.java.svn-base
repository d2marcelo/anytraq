package com.brtracker.services.dataseed;

import static com.brtracker.shared.utils.SystemConfiguration.MIDLINK;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.brtracker.shared.payload.controller.ControllerInterfaceLookup;
import com.brtracker.shared.utils.SystemConfigurationUtils;
import com.brtracker.shared.utils.activemq.JmsSender;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;
import com.brtracker.shared.utils.wsutils.ServiceResponse;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

@SuppressWarnings("unchecked")
public class SeedTripData {

	
	private Map<String, TripState> tripState = new HashMap<String, SeedTripData.TripState>();
	private static final int maxUnits = 10;
	private Map<String, List<GeoLeg>> geoRoutes;
	private JmsSender jmsSender;
	private String controllerIp;
	
	public static void main(String[] args) {
		try {
			
			SeedTripData seedData = new SeedTripData();
			
			// reading the geolocation reoutes from the pre-calculated files.
			seedData.initGeoRoutes();
			
			// creating trips for all the devices available in the database.
			seedData.initTripState();
			
			// creating trips for specific device units 
//			seedData.initTripStateForSpecificUntis();
			
			// simulate all the points based on the sketched routes and available devices 
			seedData.generateTripData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public SeedTripData() throws Exception {
		
		SystemConfigurationUtils systemConfiguration = new SystemConfigurationUtils();
		String metadaServiceEndpoint = "http://107.20.202.100:8082/metadata/system/resources";
		systemConfiguration.setMetadaServiceEndpoint(metadaServiceEndpoint);
		systemConfiguration.afterPropertiesSet();
		
		controllerIp = systemConfiguration.getEndpointURL("ACCOUNT");
		
		jmsSender = new JmsSender();
		jmsSender.setSystemConfiguration(systemConfiguration);
		jmsSender.afterPropertiesSet();
	}
	
	private void initGeoRoutes() throws Exception {
		GenerateGeoRoutes routeGenerator = new GenerateGeoRoutes();
		geoRoutes = routeGenerator.getGeoPointCollection();
	}

	private void generateTripData() throws Exception {
		
		Set<String> deviceAddresses = tripState.keySet();
		
		for (String address: deviceAddresses) {
			TripState t = tripState.get(address);
			while (!t.fullyStopped) {
				// uncomment this if posting to the old endpoint is needed
//				updateTripWs(address, t);
				updateTripAmq(address, t);
				t.move();
			}
		}
	}
	
	private void updateTripAmq(String address, TripState t) {
		
		try {
			String payloadTemplate = "'{'\"attributes\":'{'\"course\":\"{0}\",\"sharp_turns\":\"0\",\"speed\":\"{1}\",\"sharp_lane_cross\":\"0\",\"sharp_breaking\":\"0\"," +
					"\"longitude\":\"{2}\",\"latitude\":\"{3}\",\"quick_accel\":\"0\",\"distance\":\"{4}\",\"timeMillis\":\"{5}\"},\"unitAddress\":\"{6}\"}";
			
			String payload = MessageFormat.format(payloadTemplate, 
					t.course, t.speed, t.lng, t.lat, t.distance, String.valueOf(t.cal.getTimeInMillis()), t.deviceAddress);
	
			System.out.println(payload);
			
			jmsSender.sendTextMessageToTopic(payload, MIDLINK);
			
			System.out.println("update trip on unit " + address + " succedeed ");
			
		} catch (Exception e) {
			System.out.println("update trip on unit " + address + " failed with exception " + e.getMessage());
		}
	}

	/*
	 * Create trips for each one of the available devices in the database
	 */
	private void initTripState() {

		try {

			String payload = "{\"fetchSize\":\"" + maxUnits + "\", \"startIndex\":\"0\", " +
					"\"device\" : {\"assigned\":\"true\"}}";
			
			String response = listDevices(payload);
			
			Map<String, Object> object = (Map<String, Object>) JSONMapper.toObject(response, Map.class);
			List<Map<String, Object>> devices = (List<Map<String, Object>>) object.get("deviceList");
			
			for (Map<String, Object> device : devices) {
				String address = (String) device.get("address");
				System.out.println("unit address " + address);
				TripState s = new TripState();
				s.deviceAddress = address;
				s.initializeRouteSketch();
				tripState.put(address, s);
			}
			
		} catch (JSONMapperException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Create trip objects for specific device units
	 */
	private void initTripStateForSpecificUntis() {
		String[] unitAddresses = {"354330030637675"};

		for (String address : unitAddresses) {
			System.out.println("unit address " + address);
			TripState s = new TripState();
			s.deviceAddress = address;
			s.initializeRouteSketch();
			tripState.put(address, s);
		}
		
	}
	public final class TripState {
		
		public static final double maxSpeed = 220.0F;
		public static final double minSpeed = 10.0F;
		
		public static final double speedStep = 5.0F;
		public static final double steps = 3F; 
		public static final double deltaSpeed = 10;
		
		public double course = 100;
		public Calendar cal = GregorianCalendar.getInstance();
		public double speed = 0.0F;
		public double distance = 0.0;
		public double lng;
		public double lat;
		public Random r = new Random(System.currentTimeMillis());
		public List<GeoLeg> routeSketch;
		public List<GeoPoint> routePoints = new ArrayList<GeoPoint>();
		public int currentRoutePoint = 0;
		public boolean fullyStopped = false;
		public String deviceAddress;
		
		public void initializeRouteSketch() {
			int numRoutes = geoRoutes.keySet().size();
			int routeIndex = r.nextInt(numRoutes);
			int i = 0;
			Iterator<String> iterator = geoRoutes.keySet().iterator();
			while (iterator.hasNext()) {
				String routeKey = iterator.next();
				if (i == routeIndex) {
					routeSketch = geoRoutes.get(routeKey);
					break;
				}
				i++;
			}
			double currentDistance = 0.0;
//			DateFormat df = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
			for (i=0; i<routeSketch.size(); i++) {
				
				GeoLeg leg = routeSketch.get(i);
				GeoPoint p0 = leg.p0;
				GeoPoint p1 = leg.p1;
				double legDistance = leg.distance;
				long legDuration = leg.duration;
				
				for (int j = 0; j<steps; j++) {
					double delta = j*(1.0/steps);
					double newLat = p0.lat + (p1.lat-p0.lat)*delta;
					double newLng = p0.lng + (p1.lng-p0.lng)*delta;
					double d = currentDistance + j*(legDistance /steps);
					
					cal.add(Calendar.SECOND, (int) Math.round(j*(legDuration /steps)));
					long time = cal.getTimeInMillis(); 
					routePoints.add(new GeoPoint(newLat, newLng, d, time));
				}
				currentDistance += legDistance;
			}
			GeoPoint p = routePoints.get(currentRoutePoint);
			lat = p.lat;
			lng = p.lng;
			distance = p.currentDistance;
		}
		
		public void move() {
			if (fullyStopped == true) {
				return;
			}
			
			double speedSelector = r.nextDouble();
			double speedChange = 0;
			// decreasing speed with .33 of prob
			if (speedSelector >= 0.0 && speedSelector <0.33) {
				speedChange -= speedStep;
			}
			
			// between .33 and .80 speed remains the same
			
			// increasing speed with .2 of prob
			if (speedSelector >= 0.80 && speedSelector <=1.0) {
				speedChange += speedStep;
			}
			if (speed + speedChange >= maxSpeed) {
				speed -= speedStep;
			} else if (speed + speedChange <= maxSpeed) {
				speed += speedStep;
			} else {
				speed += speedChange;
			}
			if (++currentRoutePoint >= routePoints.size()) {
				fullyStopped = true;
			} else {
				GeoPoint p = routePoints.get(currentRoutePoint);
				lat = p.lat;
				lng = p.lng;
				distance = p.currentDistance;
			}
		}
		
	}

	private String listDevices(String payload) {
		try {
			return ClientCall.post(ControllerInterfaceLookup.getURLPath(ControllerInterfaceLookup.device_LIST,  controllerIp), payload);
		} catch (ServiceException e) {
			return ServiceResponseException.generateResponse(e);
		}
	}

	/*
	 * Uses the old endpoint to update the trip state
	 */
	private void updateTripWs(String address, TripState t) {
		
		try {
			String payloadTemplate = "'{'\"attributes\":'{'\"course\":\"{0}\",\"sharp_turns\":\"0\",\"speed\":\"{1}\",\"sharp_lane_cross\":\"0\",\"sharp_breaking\":\"0\"," +
					"\"longitude\":\"{2}\",\"latitude\":\"{3}\",\"quick_accel\":\"0\",\"distance\":\"{4}\",\"timeMillis\":\"{5}\"},\"unitAddress\":\"{6}\"}";
			
			String payload = MessageFormat.format(payloadTemplate, 
					t.course, t.speed, t.lng, t.lat, t.distance, String.valueOf(t.cal.getTimeInMillis()), "045092049000");
	
			String upsertTripUrl = "http://ec2-107-20-247-207.compute-1.amazonaws.com:8081/tracking/deviceMessage/add"; 
			System.out.println(upsertTripUrl);
			System.out.println(payload);
			
			String response = ClientCall.post(upsertTripUrl, payload);
			ServiceResponse resp = (ServiceResponse) JSONMapper.toObject(response, ServiceResponse.class);
			if (!resp.isReturnValue()) {
				System.out.println("update trip on unit " + address + " failed with error " + resp.getStackTrace());
			}
		} catch (Exception e) {
			System.out.println("update trip on unit " + address + " failed with exception " + e.getMessage());
		}
	}


}
