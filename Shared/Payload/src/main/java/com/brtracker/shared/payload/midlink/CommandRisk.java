package com.brtracker.shared.payload.midlink;

import com.brtracker.shared.payload.controller.lookup.DeviceType;

public enum CommandRisk {
	HIGH (1),
	MEDIUM (2),
	LOW (3);
	
	
	private final int id; 
	CommandRisk(int id) {
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

