package com.brtracker.services.reporting.endpoint;

import com.brtracker.shared.utils.wsutils.StartJetty;



/**
 * Seperate startup class for people that want to run the examples
 * directly.
 */
public class Start {

	public static void main(String[] args) {
			try {
				StartJetty.run(8081);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
