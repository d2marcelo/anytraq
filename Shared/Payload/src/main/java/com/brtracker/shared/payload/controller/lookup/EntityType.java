package com.brtracker.shared.payload.controller.lookup;

public enum EntityType {

	VEHICLE (1),
	ASSET (2),
	PERSONAL(3);
	
	private final int id; 
	EntityType(int id) {
       this.id = id;
    }
    public int getId()   { return id; }
    
     public static EntityType getEntityType(int id)  {
    	for (EntityType vt : EntityType.values()){
    		if (vt.getId() == id) return vt;
    	}
    	return null;
    }
    
}
