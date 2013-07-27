package com.brtracker.services.dataseed;

import java.util.ArrayList;
import java.util.List;

public class RouteDef {

	public String name;
	public String startAddress;
	public String endAddress;
	public GeoPoint startPoint = new GeoPoint();
	public GeoPoint endPoint = new GeoPoint();
	public List<GeoPoint> routeSketch = new ArrayList<GeoPoint>();
	public boolean enabled;
	
	public RouteDef(String name, boolean enabled, String startAddress, String endAddress) {
		this.name = name;
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.enabled = enabled;
	}

}
