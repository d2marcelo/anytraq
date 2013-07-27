package com.brtracker.services.appapi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LocationGenerator {

	double latitude;
	double longitude;
	
	public LocationGenerator()
	{
		latitude = (Math.random()*180);
		longitude = (Math.random()*360);
		
		//this.WIDTH = 400;
	}
	
	public Map<String, String> retrive()
	
	{	Map<String, String> map = new HashMap<String, String>();
	  try{
		  FileInputStream fstream = new FileInputStream("lat");
		  DataInputStream in = new DataInputStream(fstream);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  String lat = null;
		  while ((strLine = br.readLine()) != null)   {
			  String[] arr = strLine.split("   ");
			 
		  if (strLine.startsWith("Latitude")){
			  lat =arr[1].trim();
		  } 
		  if (strLine.startsWith("Longitude")){
			  map.put(lat, arr[1].trim());
		  }
		  }
		  in.close();
		 }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		 }
	return map;
	}
}
