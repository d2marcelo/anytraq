package com.brtracker.services.tracking.endpoint;

import com.brtracker.shared.utils.wsutils.StartJetty;

public class Start {

	public static void main(String[] args) {
		try {
			StartJetty.run(8083);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
