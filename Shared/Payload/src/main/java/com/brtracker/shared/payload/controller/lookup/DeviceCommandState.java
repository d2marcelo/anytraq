package com.brtracker.shared.payload.controller.lookup;

public enum DeviceCommandState {

	APPROVAL_PENDING(1),
	PENDING_DELIVERY(2),
	DELIVERED(3),
	VERIFIED(4),
	CANCELED(5),
	FAILED(6);

	private final int id; 
	DeviceCommandState (int id) {
       this.id = id;
    }
    public int getId()   { return id; }

}
