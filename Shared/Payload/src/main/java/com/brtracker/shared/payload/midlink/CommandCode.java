package com.brtracker.shared.payload.midlink;

public enum CommandCode {
	LOCK(1),
	GET_GPS(3),
	UNLOCK(2),
	RESET(4);

	private final int id;

	CommandCode(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
