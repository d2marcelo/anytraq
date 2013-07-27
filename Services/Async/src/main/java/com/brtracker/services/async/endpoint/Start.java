package com.brtracker.services.async.endpoint;

import com.brtracker.shared.utils.wsutils.StartJetty;

public class Start {

	public static void main(String[] args) {
		try {
			StartJetty.run(8081);
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
