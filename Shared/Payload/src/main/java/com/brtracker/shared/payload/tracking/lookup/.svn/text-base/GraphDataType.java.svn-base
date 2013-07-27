package com.brtracker.shared.payload.tracking.lookup;


public enum GraphDataType {

	SPEED (1),
	FUEL (2),
	DISTANCE (3);
	
	private final int id; 
	GraphDataType(int id) {
       this.id = id;
    }
	
    public int getId()   { return id; }
    
    public static GraphDataType getGraphDataType(int id)  {
    	for (GraphDataType vt : GraphDataType.values()){
    		if (vt.getId() == id) return vt;
    	}
    	return null;
    }
}
