package com.brtracker.shared.payload.controller.lookup;

public enum AccountType {

	 CONSUMER (1),
	 FLEET (2);
	 
	 private final int id; 
	 AccountType (int id) {
	       this.id = id;
	    }
	    public int getId()   { return id; }
	    
	    public static AccountType getAccountType(int id)  {
	    	for (AccountType vt : AccountType.values()){
	    		if (vt.getId() == id) return vt;
	    	}
	    	return null;
	    }

}
