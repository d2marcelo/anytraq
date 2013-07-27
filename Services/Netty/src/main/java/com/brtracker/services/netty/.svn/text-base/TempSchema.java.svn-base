package com.brtracker.services.netty;

public class TempSchema {

	
	public static String message ="{\"schema_ns\" : \"tracking.wlink.picolo\",\"preprocessor\" : \"picolo_packet_preprocessor\",\"unit_identifier_propery\":\"presentationMessage.unitId\"," +
"\"message_root\" : {\"header\": {\"type\": \"keyed_field_set\",\"optional\":\"true\"," +
"\"field_delim\": \"0\",\"field_key\": {\"type\":\"string\", \"max_length\":\"3\"},\"fields\":{" +
"\"h01\" : {\"type\":\"string\", \"property\":\"manufacturerId\"},\"h02\" : {\"type\":\"string\", \"property\":\"unitId\"}" +
"}},\"stx\":{\"type\":\"drop_until\", \"value\":\"2\", \"store\":\"false\"},\"packet_class\": {" +
"\"type\":\"selector\",\"key_type\":\"int\",\"key_length\":\"1\",\"mapping\" : {" +
"\"0x31\": \"appMessage\",\"0x32\": \"presentationMessage\",\"0x34\": \"keepAliveMessage\"" +
"}}},\"appMessage\": {\"sequenceNumber\": {\"type\":\"ushort\"}," +
"\"messageLength\": {\"type\":\"ushort\"}, \"messageChksum\": {\"type\":\"ushort\"}," +
"\"msg_type\": {\"type\":\"selector\", \"key_type\":\"int\",\"key_length\":\"2\"," +
" \"mapping\": { \"0xc9\" : \"gpsInfo\", \"0x0a\" : \"emergency\",\"0x1e\" : \"jBus\"" +
"} }},\"presentationMessage\": { \"message_name\":\"PicoloPresentationMessage\"," +
"\"keyword\": {\"type\":\"string\", \"length\":\"4\"},\"unitId\": {\"type\":\"string\", \"length\":\"12\"}" +
"},\"gpsInfo\": { \"type\": \"keyed_field_set\",\"message_name\":\"PicoloApplicationMessage\"," +
"\"field_delim\": \"0\",\"field_key\": {\"type\":\"binary_int\"},\"fields\":{" +
"\"1\": {\"type\":\"int\", \"property\":\"dataformat\"}," +
"\"2\": {\"type\":\"picolo_gps_nmea_data\", \"property\":\"gpsNmeaData\"}," +
"\"5\": {\"type\":\"picolo_gps_ext_bin_data\", \"property\":\"gpsextendedbinarydata\"}," +
"\"6\": {\"type\":\"int\", \"property\":\"reason\"}," +
"\"9\": {\"type\":\"float\", \"tokenized\":\"true\", \"multiply\":\"0.3048\", \"property\":\"distance\"}," +
"\"10\": {\"type\":\"int\", \"property\":\"atmode\"},\"19\": {\"type\":\"int\", \"property\":\"messageNumber\"}," +
"\"20\": {\"type\":\"int\", \"tokenized\":\"true\", \"length\": \"7\", \"property\":\"alarmCounters\"}," +
"\"22\": {\"type\":\"float\", \"property\":\"a2dInput\", \"multiply\":\"0.025\"}," +
"\"26\": {\"type\":\"int\", \"property\":\"engineSwitchState\"}," +
"\"255\": {\"type\":\"time32\", \"property\":\"currentunittime\"}," +
"\"246\": {\"type\":\"picolo_pw_report\", \"property\":\"extendedPowerReport\"}," +
"\"247\": {\"type\":\"string\", \"property\":\"unitSerialNumber\"}," +
"\"248\": {\"type\":\"string\"}," +
"\"249\": {\"type\":\"int\", \"property\":\"batteryConversion\"}," +
"\"250\": {\"type\":\"int\", \"property\":\"internalTemp\", \"multiply\":\"1.76\", \"add\":\"76\"}," +
"\"251\": {\"type\":\"int\", \"property\":\"batteryAtStart\"}," +
"\"252\": {\"type\":\"int\", \"property\":\"batteryNow\"}}}," +
"\"jBus\": {\"type\": \"keyed_field_set\"," +
"\"message_name\":\"PicoloJBusMessage\"," +
"\"field_delim\": \"0\"," +
"\"field_key\": {\"type\":\"binary_int\"}," +
"\"fields\":{" +
"\"1\": {\"type\":\"int\", \"property\":\"messageNumber\"}," +
"\"2\": {\"type\":\"int\", \"property\":\"reportType\"}," +
"\"3\": {\"type\":\"int\", \"property\":\"tripState\"}," +
"\"8\": {\"type\":\"float\", \"property\":\"totalDistance\"}," +
"\"9\": {\"type\":\"string\", \"property\":\"fuelLevelPri\"}," +
"\"10\": {\"type\":\"string\", \"property\":\"fuelLevelSec\"}," +
"\"11\": {\"type\":\"string\", \"property\":\"avgFuelLevel\"}," +
"\"17\": {\"type\":\"string\", \"property\":\"currentSpeed\"}," +
"\"18\": {\"type\":\"string\", \"property\":\"currentEndgineLoad\"}" +
"}}}";
		
}

