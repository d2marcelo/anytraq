package com.brtracker.shared.payload.controller.lookup;

public enum DeviceState {

	 TRIP_STARTED (1),
	 MOVING (2),
	 STOPPED (3),
	 TRIP_ENDED (4),
	 NO_GPS (5),
	 OFF_LINE (6),
	 TRIP_END_START (7);
	 
	 
	 private final int id; 
	 DeviceState (int id) {
	       this.id = id;
	    }
	    public int getId()   { return id; }
	    
	    public static DeviceState getDeviceState(int id)  {
	    	for (DeviceState vt : DeviceState.values()){
	    		if (vt.getId() == id) return vt;
	    	}
	    	return null;
	    }
	    public static DeviceState getDeviceState(String state)  {
	    	for (DeviceState vt : DeviceState.values()){
	    		if (vt.name().equals(state)) return vt;
	    	}
	    	return null;
	    }
}
