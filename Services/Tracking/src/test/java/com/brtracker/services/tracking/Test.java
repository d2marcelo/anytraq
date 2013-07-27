package com.brtracker.services.tracking;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] test = new int[]{44,57,58,62, 74, 89, 99, 120, 133};
		int counter=  0;
		int previous= 0;
		int total_previous = 0;
		int last_diff = 0;
		for ( int t : test ){
			int current = 0;
			if ( counter ==0){
				current = 0;
				previous = t;
			} else {
				current = t - previous;
				previous = t;
			}
			total_previous = current + last_diff;
			last_diff = last_diff + current;
			counter++;
			System.out.println(total_previous);
		}

	}

}
