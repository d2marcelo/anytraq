package com.brtracker.shared.payload.controller.lookup;


public enum DeviceType {

	DATA_COMMUNICATOR (1),
	TRACKING (2),
	CAMERA (3),
	IBUTTON (4),
	SMARTPHONE (5),
	TABLET (6),
	MONITORING(7);

	private final int id; 
	DeviceType(int id) {
       this.id = id;
    }
    public int getId()   { return id; }
    
    
     public static DeviceType getDeviceType(int id)  {
    	for (DeviceType vt : DeviceType.values()){
    		if (vt.getId() == id) return vt;
    	}
    	return null;
    }
    
}
