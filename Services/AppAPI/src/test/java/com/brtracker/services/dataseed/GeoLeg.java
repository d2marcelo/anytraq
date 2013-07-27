package com.brtracker.services.dataseed;

public class GeoLeg {
	public GeoPoint p0;
	public GeoPoint p1;
	public double distance;
	public long duration;
	
	public GeoLeg() {
		
	}
	public GeoLeg(GeoPoint p0, GeoPoint p1, double distance) {
		this.p0 = p0;
		this.p1 = p1;
		this.distance = distance;
	}
}
