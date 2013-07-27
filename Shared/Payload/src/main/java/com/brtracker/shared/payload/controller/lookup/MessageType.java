package com.brtracker.shared.payload.controller.lookup;


public enum MessageType {

	PicoloGpsInfoMessage (1),
	PicoloJBusMessage (2),
	PicoloIOServiceMessage (3),
	TeltonikaAVLMessage (4),
	EnforaMT3000Message (5),
	XirgoMessage (6),
	MonitoringMessage(7);

	private final int id; 
	MessageType(int id) {
       this.id = id;
    }
    public int getId()   { return id; }
    
    
     public static MessageType getDeviceType(int id)  {
    	for (MessageType vt : MessageType.values()){
    		if (vt.getId() == id) return vt;
    	}
    	return null;
    }
    
}
