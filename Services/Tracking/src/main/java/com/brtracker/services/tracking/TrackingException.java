package com.brtracker.services.tracking;

// TODO: Auto-generated Javadoc
/**
 * The Class TrackingException.
 */
public class TrackingException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new tracking exception.
	 *
	 * @param message the message
	 */
	public TrackingException (String message) {
         super(message);
     }
	
	/**
	 * Instantiates a new tracking exception.
	 *
	 * @param e the e
	 */
	public TrackingException (Throwable  e) {
         super(e);
    }
	
	
}