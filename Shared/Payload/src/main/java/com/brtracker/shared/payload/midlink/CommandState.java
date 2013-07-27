package com.brtracker.shared.payload.midlink;

import com.brtracker.shared.payload.controller.lookup.DeviceType;

public enum CommandState {
	APPROVAL_PENDING (1),
	PENDING_DELIVERY (2),
	DELIVERED (3),
	VERIFIED (4),
	CANCELED(5),
	FAILED(6);
	
	private final int id; 
	CommandState(int id) {
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

