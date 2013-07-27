package com.brtracker.shared.payload.midlink;


public enum MidlinkInterfaceLookup {

	command_TEMPLATE ("midlink/command/template"),
	command_REQUEST ("midlink/command/request"),
	command_LIST ("midlink/command/list"),
	command_APPROVE ("midlink/command/approve"),
	command_CANCEL ("midlink/command/cancel"),
	command_IOCONFIG ("midlink/command/io-config");
	
	private final String path; 
    MidlinkInterfaceLookup(String path) {
       this.path = path;
    }
    public String getPath()   { return path; }
    
    public static String getURLPath (MidlinkInterfaceLookup type , String ip) {
    	return ip +"/"+type.getPath();
    }
    
	
}
