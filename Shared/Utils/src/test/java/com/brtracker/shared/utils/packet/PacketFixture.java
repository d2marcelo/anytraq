package com.brtracker.shared.utils.packet;

import java.util.HashMap;
import java.util.Map;

public class PacketFixture {
	
	public short[] getIoService() {
		return new short[] {
				0x68, 0x30, 0x31, 0x0, 0x33, 0x30, 0x30, 0x31, 0x0, 0x68, 0x30, 0x32, 0x0, 0x30, 0x34, 0x35, 0x31, 0x31, 0x30, 0x32, 0x33, 0x33, 0x30, 0x31, 0x36, 0x0, 0x68, 0x30, 0x33, 0x0, 0x31, 0x33, 0x32, 0x36, 0x38, 0x35, 0x38, 0x34, 0x30, 0x38, 0x38, 0x30, 0x31, 0x0, 0x2, 0x31, 0x0, 0xdb, 0xd2, 0x0, 0x81, 0xc6, 0x3c, 0xcb, 0x0, 0x1, 0x0, 0x33, 0x37, 0x31, 0x39, 0x0, 0xdb, 0xd2, 0x0, 0x30, 0x0, 0xdb, 0xd3, 0x0, 0x30, 0x0, 0x6, 0x0, 0x30, 0x0, 0x7, 0x0, 0x30, 0x0, 0xc, 0x0, 0x32, 0x35, 0x32, 0x0, 0xd, 0x0, 0x30, 0x0, 0x10, 0x0, 0x30, 0x33, 0x3a, 0x34, 0x36, 0x3a, 0x31, 0x34, 0x0, 0x12, 0x0, 0x30, 0x31, 0x2f, 0x31, 0x38, 0x2f, 0x31, 0x32, 0x0, 0xf6, 0x0, 0x30, 0x2c, 0x34, 0x2c, 0x31, 0x37, 0x33, 0x30, 0x2c, 0x34, 0x35, 0x39, 0x0, 0xf7, 0x0, 0x38, 0x30, 0x39, 0x31, 0x36, 0x38, 0x0, 0xf8, 0x0, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0, 0xf9, 0x0, 0x31, 0x0, 0xfa, 0x0, 0x38, 0x34, 0x0, 0xfb, 0x0, 0x31, 0x39, 0x37, 0x0, 0xfc, 0x0, 0x32, 0x34, 0x32, 0x0, 0xff, 0x0, 0x31, 0x33, 0x32, 0x36, 0x38, 0x35, 0x38, 0x33, 0x37, 0x34, 0x0, 0x3, 0x0				
		};
	}

	public short[] getGps1() {
		
		return new short[] {
				0x68, 0x30, 0x31, 0x0, 0x33, 0x30, 0x30, 0x31, 0x0, 0x68, 0x30, 0x32, 0x0, 0x30, 0x31, 0x33, 0x31, 0x31, 0x30, 0x31, 0x33, 0x33, 0x30, 0x35, 0x37, 0x0, 0x68, 0x30, 0x33, 0x0, 0x31, 0x33, 0x32, 0x36, 0x38, 0x35, 0x32, 0x35, 0x31, 0x37, 0x39, 0x37, 0x33, 0x0, 0x2, 0x31, 0xd, 0x91, 0x0, 0x86, 0x26, 0xe0, 0xc9, 0x0, 0x1, 0x0, 0x34, 0x0, 0x5, 0x0, 0xff, 0x0, 0x10, 0xff, 0x6c, 0x77, 0x3d, 0xfe, 0x45, 0x8c, 0x7e, 0x0, 0xdb, 0xdd, 0x90, 0x7f, 0xc1, 0x2c, 0xdb, 0xd3, 0xd0, 0x0, 0x6, 0x0, 0x36, 0x0, 0x9, 0x0, 0x32, 0x34, 0x31, 0x33, 0x30, 0x34, 0x35, 0x37, 0x0, 0x13, 0x0, 0x35, 0x30, 0x39, 0x30, 0x39, 0x0, 0x14, 0x0, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0, 0xf6, 0x0, 0x31, 0x2c, 0x34, 0x2c, 0x32, 0x38, 0x35, 0x32, 0x2c, 0x34, 0x32, 0x37, 0x0, 0xf7, 0x0, 0x38, 0x30, 0x36, 0x32, 0x36, 0x37, 0x0, 0xf8, 0x0, 0x31, 0x32, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0, 0xf9, 0x0, 0x31, 0x0, 0xfa, 0x0, 0x31, 0x30, 0x37, 0x0, 0xfb, 0x0, 0x32, 0x35, 0x34, 0x0, 0xfc, 0x0, 0x32, 0x35, 0x34, 0x0, 0xff, 0x0, 0x31, 0x33, 0x32, 0x36, 0x38, 0x35, 0x32, 0x35, 0x32, 0x38, 0x0, 0x3, 0x0
		};
	}

	public short[] getGps2() {
		
		return new short[] {
				0x68, 0x30, 0x31, 0x0, 0x33, 0x30, 0x30, 0x31, 0x0, 0x68, 0x30, 0x32, 0x0, 0x30, 0x31, 0x33, 0x31, 0x31, 0x30, 0x31, 0x33, 0x33, 0x30, 0x35, 0x37, 0x0, 0x68, 0x30, 0x33, 0x0, 0x31, 0x33, 0x32, 0x36, 0x38, 0x35, 0x32, 0x36, 0x31, 0x38, 0x30, 0x36, 0x37, 0x0, 0x2, 0x31, 0xd, 0x92, 0x0, 0x86, 0xc2, 0x14, 0xc9, 0x0, 0x1, 0x0, 0x34, 0x0, 0x5, 0x0, 0xff, 0x0, 0x10, 0xff, 0x6c, 0x7e, 0x60, 0xfe, 0x45, 0xc2, 0x76, 0x1, 0xb4, 0x90, 0x81, 0xc1, 0x2c, 0xdb, 0xd2, 0x6c, 0x0, 0x6, 0x0, 0x36, 0x0, 0x9, 0x0, 0x32, 0x34, 0x31, 0x33, 0x39, 0x30, 0x36, 0x38, 0x0, 0x13, 0x0, 0x35, 0x30, 0x39, 0x31, 0x30, 0x0, 0x14, 0x0, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x31, 0x0, 0xf6, 0x0, 0x31, 0x2c, 0x34, 0x2c, 0x32, 0x38, 0x36, 0x31, 0x2c, 0x34, 0x32, 0x35, 0x0, 0xf7, 0x0, 0x38, 0x30, 0x36, 0x32, 0x36, 0x37, 0x0, 0xf8, 0x0, 0x31, 0x32, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0, 0xf9, 0x0, 0x31, 0x0, 0xfa, 0x0, 0x31, 0x30, 0x37, 0x0, 0xfb, 0x0, 0x32, 0x35, 0x34, 0x0, 0xfc, 0x0, 0x32, 0x35, 0x34, 0x0, 0xff, 0x0, 0x31, 0x33, 0x32, 0x36, 0x38, 0x35, 0x32, 0x36, 0x34, 0x38, 0x0, 0x3, 0x0
		};
	}
	
	public short[] getApplicationDataPacket() {
		
		return new short[] {
				104, 48, 49, 0, 
				51, 48, 48, 49, 0, 
				104, 48, 50, 0, 
				48, 52, 53, 48, 57, 50, 48, 52, 55, 49, 52, 54, 0, 
				2, 
				49, 5, 54, 0, 
				110, 170, 157, 201, 0, 
				1, 0, 
				52, 0, 
				5, 0, 
				255, 0, 
				16, 1, 86, 80,
				 63, 251, 162, 180,
				 75, 0, 0,
				 59, 203, 182, 8, 11, 118, 0,
				 6, 0,
				 51, 0,
				 8, 0,
				 49, 54, 58, 48, 56, 58, 48, 56, 0,
				 9, 0,
				 52, 49, 50, 49, 54, 48, 52, 44, 52, 52, 52, 0,
				 19, 0,
				 56, 52, 51, 0,
				 247, 0,
				 55, 48, 50,
				 52, 50,
				 52, 0,
				 248, 0,
				 51, 51, 44, 48, 44, 48, 44, 48, 44, 48, 0,
				 249, 0,
				 49, 0,
				 251, 0,
				 49, 55, 50,
				 0,
				 252, 0,
				 49, 56, 49, 0,
				 255, 0,
				 49, 51, 48, 55, 52, 54, 51, 48, 54, 52, 0,
				 3
		};
	}
	
	public short[] getApplicationPacketWithWriter() {
		
		short[] unitPacket = {
				// some bogus data to check the drop until feature
				0x01, 0x01,
				// actual message
				0x02, 
				0x31, 
				0xe, 0x8e, 
				0x0, 0x85, 
				0x90, 0xe9, 
				0xc9, 0x0, 
				0x1, 0x0, 
				0x34, 0x0, 
				0x5, 0x0, 
					0xff, // binary field marker 
					0x0, 0x10, // size   
					0xff, 0x45, 0xb5, 0x69, // latitude 
					0xfe, 0x42, 0x93, 0xe3, // longitude
					0x0,  0xdb, 0xd2, // speed over ground
					0xbd, 0x9f, // time field
					0xb5, // month
					0x24, // second 
					0x0, 0x70, // course 
					0x0, 
				0x6, 0x0, 
				0x36, 0x0, 
				0x9, 0x0, 
				0x31, 0x33, 0x35, 0x39, 0x30, 0x39, 0x30, 0x38, 0x0, 
				0x13, 0x0, 
				0x33, 0x39, 0x34, 0x38, 0x0, 
				0x14, 0x0, 
				0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0, 
				0xf6, 0x0, 
				0x31, 0x2c, 0x34, 0x2c, 0x32, 0x38, 0x36, 0x31, 0x2c, 0x32, 0x31, 0x32, 0x0, 
				0xf7, 0x0, 
				0x38, 0x30, 0x36, 0x32, 0x36, 0x37, 0x0, 
				0xf8, 0x0, 
				0x31, 0x32, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0, 
				0xf9, 0x0, 
				0x31, 0x0, 
				0xfa, 0x0, 
				0x31, 0x31, 0x32, 0x0, 
				0xfb, 0x0, 
				0x32, 0x35, 0x34, 0x0, 
				0xfc, 0x0, 
				0x32, 0x35, 0x34, 0x0, 
				0xff, 0x0, 
				0x31, 0x33, 0x30, 0x36, 0x31, 0x39, 0x35, 0x31, 0x39, 0x34, 0x0, 
				0x03
		};
		
		PacketWriterController writer = new PacketWriterController();
		
		Map<String, String> headers = new HashMap<String, String>();
		// Netty adds this header (header id, delimiter, header value, delimiter): h01 0 3001 0
		headers.put("h01", "3001");
		// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 123423452345 0
		headers.put("h02", "123423452345");
		short[] header = writer.buildHeader(headers);
		
		short[] packet = new short[header.length + unitPacket.length];
		
		for (int i=0; i<header.length; i++) {
			packet[i] = header[i];
		}
		
		for (int i=0; i<unitPacket.length; i++) {
			packet[header.length + i] = unitPacket[i];
		}
		return packet;
	}

	public short[] getApplicationSample1() {
		short[] packet = new short[] {
				 0x2, 
				 0x31, 0x0,
				 0x8, 0x0,
				 0x6f, 0x9a, 0x34, 0xa, 0x0,
				 0x1, 0x0,
				 0x34, 0x0,
				 0x2, 0x0,
				 0x32, 0x32, 0x3a, 0x34, 0x36, 0x3a, 0x34, 0x31, 0x0,
				 0x3, 0x0,
				 0x34, 0x0,
				 0x4, 0x0,
				 0xff, 0x0,
				 0x10, 0x1, 0x58, 0x2f, 0x5a, 0xfb, 0x9f, 0xa6, 0x51, 0x0,
				 0x0,
				 0x5d, 0x56, 0xb6, 0x2a, 0x0,
				 0x0,
				 0x0,
				 0x5, 0x0,
				 0x37, 0x31, 0x0,
				 0x6, 0x0,
				 0x30, 0x36, 0x2f, 0x31, 0x31, 0x2f, 0x31, 0x31, 0x0,
				 0xf7, 0x0,
				 0x37, 0x30, 0x32, 0x34, 0x32, 0x34, 0x0,
				 0xf8, 0x0,
				 0x33, 0x35, 0x31, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0,
				 0xf9, 0x0,
				 0x31, 0x0,
				 0xfb, 0x0,
				 0x31, 0x37, 0x38, 0x0,
				 0xfc, 0x0,
				 0x31, 0x37, 0x35, 0x0,
				 0xff, 0x0,
				 0x31, 0x33, 0x30, 0x37, 0x38, 0x33, 0x32, 0x34, 0x30, 0x31, 0x0,
				 0x3
		};
		PacketWriterController writer = new PacketWriterController();
		packet = writer.prependHeader("h01", "3001", packet);
		// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 123423452345 0
		packet = writer.prependHeader("h02", "123423452345", packet);
		return packet;
	}

	public short[] getIOServicePacket() {
		return new short[] {
				 0x68, 0x30, 0x31, 0x0,
				 0x33, 0x30, 0x30, 0x31, 0x0,
				 0x68, 0x30, 0x32, 0x0,
				 0x30, 0x34, 0x35, 0x30, 0x39, 0x32, 0x30, 0x34, 0x37, 0x31, 0x34, 0x36, 0x0,
				 0x2, 
				 0x31, 
				 0x5, 0x61, 0x0, 0x56, 0xe, 0x8a, 
				 0xcb, 0x0,
				 0x1, 0x0,
				 0x35, 0x35, 0x37, 0x0,
				 0x6, 0x0,
				 0x31, 0x0,
				 0x10, 0x0,
				 0x30, 0x30, 0x3a, 0x33, 0x32, 0x3a, 0x31, 0x36, 0x0,
				 0x12, 0x0,
				 0x30, 0x36, 0x2f, 0x30, 0x39, 0x2f, 0x31, 0x31, 0x0,
				 0xf7, 0x0,
				 0x37, 0x30, 0x32, 0x34, 0x32, 0x34, 0x0,
				 0xf8, 0x0,
				 0x31, 0x36, 0x36, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0,
				 0xf9, 0x0,
				 0x31, 0x0,
				 0xfb, 0x0,
				 0x31, 0x37, 0x32, 0x0,
				 0xfc, 0x0,
				 0x31, 0x37, 0x33, 0x0,
				 0xff, 0x0,
				 0x31, 0x33, 0x30, 0x37, 0x35, 0x37, 0x39, 0x35, 0x33, 0x36, 0x0,
				 0x3	
		};
	}
	
	public short[] getAlarmApplicationPacket() {
		return new short[] {
				 0x68, 0x30, 0x31, 0x0, 
				 0x33, 0x30, 0x30, 0x31, 0x0, 
				 0x68, 0x30, 0x32, 0x0, 
				 0x30, 0x34, 0x35, 0x30, 0x39, 0x32, 0x30, 0x34, 0x37, 0x31, 0x34, 0x36, 0x0, 
				 0x2, 0x31, 0x0, 
				 0xdb, 0xd3, 0x0, 
				 0x4f, 0x50, 0x98, 
				 0xa, 0x0, 
				 0x1, 0x0, 
				 0x34, 0x0, 
				 0xdb, 0xd2, 0x0, 
				 0x32, 0x32, 0x3a, 0x33, 0x37, 0x3a, 0x32, 0x37, 0x0, 
				 0x6, 0x0, 
				 0x30, 0x36, 0x2f, 0x31, 0x31, 0x2f, 0x31, 0x31, 0x0, 
				 0xf7, 0x0, 
				 0x37, 0x30, 0x32, 0x34, 0x32, 0x34, 0x0, 
				 0xf8, 0x0, 
				 0x30, 0x2c, 0x33, 0x31, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0, 
				 0xf9, 0x0, 
				 0x31, 0x0, 
				 0xfb, 0x0, 
				 0x31, 0x37, 0x38, 0x0, 
				 0xfc, 0x0, 
				 0x31, 0x37, 0x37, 0x0, 
				 0xff, 0x0, 
				 0x31, 0x33, 0x30, 0x37, 0x38, 0x33, 0x31, 0x38, 0x34, 0x37, 0x0, 
				 0x3		
		};
	}
	
	//68 30 31 0 33 30 30 31 0 68 30 32 0 30 34 35 30 39 32 30 34 37 31 34 36 0 2 31 0 db d3 0 4f 50 98 a 0 1 0 34 0 db d2 0 32 32 3a 33 37 3a 32 37 0 6 0 30 36 2f 31 31 2f 31 31 0 f7 0 37 30 32 34 32 34 0 f8 0 30 2c 33 31 2c 30 2c 30 2c 30 0 f9 0 31 0 fb 0 31 37 38 0 fc 0 31 37 37 0 ff 0 31 33 30 37 38 33 31 38 34 37 0 3
	public short[] getPresentationPacket() {
		return new short[] {
				// Netty adds this header (header id, delimiter, header value, delimiter): h01 0 01 0
				0x68, 0x30, 0x31, 0x00, 
				0x33, 0x30, 0x30, 0x31, 0x00,
				// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 1234 0
				0x68, 0x30, 0x32, 0x00, 
				0x31, 0x32, 0x33, 0x34, 0x00,
				// some bogus data to check the drop until feature
				0x01, 0x01,
				0x02, 
				0x32, 
				0x77, 0x6c, 0x69, 0x3a,
				0x30, 0x31, 0x32, 0x31, 
				0x32, 0x33, 0x34, 0x31, 
				0x32, 0x32, 0x35, 0x33, 
				0x33, 0x35, 0x32, 0x30, 
				0x03
		};
	}

	public short[] getPresentationPacketNoHeader() {
		return new short[] {
				// some bogus data to check the drop until feature
				0x01, 0x01,
				0x02, 
				0x32, 
				0x77, 0x6c, 0x69, 0x3a,
				0x30, 0x31, 0x32, 0x31, 
				0x32, 0x33, 0x34, 0x31, 
				0x32, 0x32, 0x35, 0x33, 
				0x33, 0x35, 0x32, 0x30, 
				0x03
		};
	}

	public short[] getPresentationPacketNoHeader2() {
		// 250119108105584852534857504852574848483
		return new short[] {
				// some bogus data to check the drop until feature
				2, 
				50, 
				119, 108, 105, 58, 48, 49, 51, 49, 49, 48, 49, 51, 51, 47, 53, 55, 3
		};
	}

	public short[] getJBusPacket3() {
		return new short[] {
				0x68,0x30,0x31,0x0,
				0x33,0x30,0x30,0x31,0x0,
				0x68,0x30,0x32,0x0,
				0x30,0x34,0x35,0x30,0x39,0x32,0x30,0x34,0x39,0x30,0x30,0x30,0x0,
				0x2,0x31,0x5,0xfd,0x0,
				0x7b,0x6d,0x45,0x1e,0x0,
				0x1,0x0, // message number
				0x39,0x37,0x30,0x0, 
				0xdb,0xd2,0x0, // report type 
				0x31,0x0, // type = 1
				0xdb,0xd3,0x0, // trip state
				0x34,0x0, // on route
				0x4,0x0, // current date
				0x30,0x37,0x2f,0x31,0x34,0x2f,0x31,0x31,0x0,
				0x5,0x0, // current time
				0x32,0x30,0x3a,0x30,0x34,0x3a,0x30,0x31,0x0,
				0x8,0x0, // total distance 
				0x33,0x32,0x33,0x35,0x38,0x34,0x39,0x0,
				0xe,0x0, // road speed 
				0x31,0x31,0x37,0x0,
				0xf,0x0, // jbus type
				0x33,0x0,
				0x11,0x0, // current engine speed
				0x35,0x30,0x37,0x38,0x0,
				0x12,0x0, // reserved
				0x37,0x30,0x0,
				0xe7,0x0, // gps format
				0x34,0x0,
				0xe8,0x0, // gps data
				0xff,0x0, // binary
				0x10,0x1,0x5f,0x97,0xe,0xfb,0xa3,0x89,0x66,0x1,0xf5,0x74,0xb3,0xb7,0x38,0x0,
				0x6a,0x0, // not used
				0xe9,0x0, // gps distance
				0x33,0x32,0x30,0x31,0x36,0x36,0x30,0x0,
				0xea,0x0, // gps no move flag
				0x30,0x0,
				0xff,0x0, // unit time
				0x31,0x33,0x31,0x30,0x36,0x37,0x33,0x38,0x34,0x31,0x0,
				0x3
			};
	}
	
	public short[] getJBusPacket2() {
		return new short[] {
				0x68,0x30,0x31,0x0,
				0x33,0x30,0x30,0x31,0x0,
				0x68,0x30,0x32,0x0,
				0x30,0x34,0x35,0x30,0x39,0x32,0x30,0x34,0x39,0x30,0x30,0x30,0x0,
				0x2,0x31,0x8,0xd4,0x0,
				0x7d,0x77,0xbf,0x1e,0x0,
				0x1,0x0,
				0x31,0x34,0x35,0x32,0x0,
				0xdb,0xd2,0x0,
				0x31,0x0,
				0xdb,0xd3,0x0,
				0x34,0x0,
				0x4,0x0,
				0x30,0x37,0x2f,0x31,0x35,0x2f,0x31,0x31,0x0,
				0x5,0x0,
				0x30,0x35,0x3a,0x30,0x33,0x3a,0x30,0x33,0x0,
				0x8,0x0,
				0x33,0x32,0x33,0x39,0x38,0x36,0x37,0x0,
				0xe,0x0,
				0x31,0x31,0x35,0x0,
				0xf,0x0,
				0x33,0x0,
				0x11,0x0,
				0x34,0x39,0x36,0x38,0x0,
				0x12,0x0,
				0x31,0x30,0x32,0x0,
				0xe7,0x0,
				0x34,0x0,
				0xe8,0x0,
				0xff,0x0,0x10,
				0x1,0x66,0x87,0xec,0xfb,0xa1,0xb6,0x52,0x1,0xec,0x79,0x2e,0xb7,0x3a,0x6,0xb,0x0,
				0xe9,0x0,
				0x35,0x32,0x37,0x36,0x34,0x38,0x38,0x0,
				0xea,0x0,
				0x30,0x0,
				0xff,0x0,
				0x31,0x33,0x31,0x30,0x37,0x30,0x36,0x31,0x38,0x33,0x0,
				0x3
			};
	}

	/**
	 * Message sample that cause issue on July 19th 2011
	 */
	public short[] getIOServicePacket2() {
		return new short[] {
				0x68,0x30,0x31,0x0,
				0x33,0x30,0x30,0x31,0x0,
				0x68,0x30,0x32,0x0,
				0x30,0x31,0x33,0x31,0x31,0x30,0x31,0x33,0x33,0x30,0x35,0x37,0x0,
				0x2,
				0x31,
				0x1c,0x84,
				0x0,0x91,
				0x56,0x93,
				0xcb,0x0,
				0x1,0x0,
				0x39,0x36,0x36,0x0,
				0x6,0x0,
				0x30,0x0,
				0x10,0x0,
				0x30,0x30,0x3a,0x32,0x30,0x3a,0x30,0x30,0x0,
				0x12,0x0,
				0x30,0x37,0x2f,0x32,0x30,0x2f,0x31,0x31,0x0,
				0x20,0x0,
				0x34,0x0,
				0x21,0x0,
				0xff,0x0,
				0x10,0xff,0x2c,0x58,0x93,0xfe,0x4f,0xc2,0x4e,0x0,0x0,0xa0,0x12,0xb7,0x21,0x6,
				0xf4,0x0,
				0x22,0x0,
				0x34,0x36,0x36,0x34,0x39,0x38,0x35,0x38,0x0,
				0xf6,0x0,
				0x31,0x2c,0x34,0x2c,0x32,0x36,0x39,0x37,0x2c,0x34,0x32,0x35,0x0,
				0xf7,0x0,
				0x38,0x30,0x36,0x32,0x36,0x37,0x0,
				0xf8,0x0,
				0x31,0x37,0x36,0x31,0x2c,0x30,0x2c,0x30,0x2c,0x30,0x2c,0x30,0x0,
				0xf9,0x0,
				0x31,0x0,
				0xfa,0x0,
				0x31,0x30,0x38,0x0,
				0xfb,0x0,
				0x32,0x35,0x34,0x0,
				0xfc,0x0,
				0x32,0x35,0x34,0x0,
				0xff,0x0,
				0x31,0x33,0x31,0x31,0x31,0x32,0x31,0x32,0x30,0x30,0x0,
				0x3
		};
	}

	public short[] getIOServicePacket3() {
		return new short[] {
				0x68,0x30,0x31,0x0,
				0x33,0x30,0x30,0x31,0x0,
				0x68,0x30,0x32,0x0,
				0x30,0x31,0x33,0x31,0x31,0x30,0x31,0x33,0x33,0x30,0x35,0x37,0x0,
				0x2,
				0x31,
				0x1c,0x84,
				0x0,0x91,
				0x56,0x93,
				0xcb, 0x0, 0x1, 0x0, 0x32, 0x33, 0x38, 0x0, 0xd, 0x0, 0x30, 0x0, 0x10, 0x0, 0x31, 0x36, 0x3a, 0x33, 0x36, 0x3a, 0x30, 0x32, 0x0, 0x12, 0x0, 0x30, 0x37, 0x2f, 0x30, 0x38, 0x2f, 0x31, 0x32, 0x0, 0x20, 0x0, 0x34, 0x0, 0x21, 0x0, 0xff, 0x0, 0x10, 0xff, 0x66, 0xb1, 0x4c, 0xfe, 0x3c, 0xdd, 0xcc,0, 0x0, 0x43, 0xe3, 0xc7, 0x3a, 0x1, 0xf9, 0x0, 0x22, 0x0, 0x33, 0x30, 0x36, 0x33, 0x36, 0x36, 0x0, 0xf6, 0x0, 0x30, 0x2c, 0x34, 0x2c, 0x31, 0x32, 0x33, 0x39, 0x2c, 0x33, 0x30, 0x39, 0x0, 0xf7, 0x0, 0x38, 0x30, 0x39, 0x31, 0x36, 0x36, 0x0, 0xf8, 0x0, 0x32, 0x38, 0x36, 0x33, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0, 0xf9, 0x0, 0x31, 0x0, 0xfa, 0x0, 0x31, 0x30, 0x36, 0x0, 0xfb, 0x0, 0x31, 0x37, 0x30, 0x0, 0xfc, 0x0, 0x31, 0x37, 0x33, 0x0, 0xff, 0x0, 0x31, 0x33, 0x34, 0x31, 0x37, 0x36, 0x35, 0x33, 0x36, 0x32, 0x0, 0x3
		};
	}

	public short[] getJBus2() {
		
		return new short[] {
				0x68,0x30,0x31,0x0,0x33,0x30,0x30,0x31,0x0,0x68,0x30,0x32,0x0,0x30,0x34,0x35,0x30,0x39,0x32,0x30,0x34,0x37,0x31,0x34,0x36,0x0,0x2,0x31,0x0,0x16,0x0,0x5e,0x65,0xb9,
				
				0x1e,0x0,
				0x1,0x0,
				0x31,0x31,0x39,0x0,
				0xdb,0xd2,0x0,
				0x33,0x0,
				0xdb,0xd3,0x0,
				0x31,0x0,
				0x4,0x0,0x30,0x38,0x2f,0x32,0x37,0x2f,0x31,0x31,0x0,0x5,0x0,0x31,0x37,0x3a,0x33,0x34,0x3a,0x35,0x34,0x0,0x6,0x0,0x30,0x38,0x2f,0x32,0x37,0x2f,0x31,0x31,0x0,0x7,0x0,0x31,0x37,0x3a,0x32,0x38,0x3a,0x31,0x37,0x0,0xe,0x0,0x30,0x0,0xf,0x0,0x33,0x0,0x11,0x0,0x30,0x0,0x12,0x0,0x30,0x0,0xe6,0x0,0x33,0x35,0x0,0xff,0x0,0x31,0x33,0x31,0x34,0x34,0x36,0x36,0x34,0x39,0x37,0x0,0x3,0x0
		};
	}
	public short[] getJBusPacket() {
		
		return new short[] {
				// Netty adds this header (header id, delimiter, header value, delimiter): h01 0 01 0
				0x68, 0x30, 0x31, 0x00, 
				0x33, 0x30, 0x30, 0x31, 0x00,
				// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 1234 0
				0x68, 0x30, 0x32, 0x00, 
				0x31, 0x32, 0x33, 0x34, 0x00,
				// some bogus data to check the drop until feature
				0x01, 0x01,
				// actual message
				 0x02, 
				 0x31, 
				 0x1,  0x24, // message sequence number 
				 0x0,  0x78, // length
				 0x88,  0x10, // word check sum
				 0x1e,  0x0, 
				 0x1,  0x0, 
				 0x32,  0x32,  0x39,  0x0, 
				 0xdb, 0xd2,  0x0, 
				 0x31,  0x0, 
				 0xdb, 0xd3,  0x0, 
				 0x33,  0x0, 
				 0x4,  0x0, 
				 0x30,  0x35,  0x2f,  0x31,  0x31,  0x2f,  0x31,  0x31,  0x0, 
				 0x5,  0x0, 
				 0x32,  0x30,  0x3a,  0x33,  0x37,  0x3a,  0x33,  0x34,  0x0, 
				 0x8,  0x0, 
				 0x35,  0x35,  0x34,  0x33,  0x32,  0x39,  0x38,  0x0, 
				 0xe,  0x0, 
				 0x30,  0x0, 
				 0xf,  0x0, 
				 0x33,  0x0, 
				 0x11,  0x0, 
				 0x35,  0x38,  0x36,  0x30,  0x0, 
				 0x12,  0x0, 
				 0x38,  0x32,  0x0, 
				 0xe7,  0x0, 
				 0x34,  0x0, 
				 0xe8,  0x0, 
				 0xff,  0x0, 
				 0x10,  0xff,  0x5c,  0x5a,  0x42,  0xfe,  0x3a,  0xb8,  0x54,  0x1,  0xeb,  0x5c,  0xd5,  0xb5,  0x1d,  0x6,  0x40,  0x0,
				 0xe9,  0x0, 
				 0x37,  0x39,  0x37,  0x36,  0x37,  0x31,  0x0, 
				 0xea,  0x0, 
				 0x30,  0x0, 
				 0xff,  0x0, 
				 0x31,  0x33,  0x30,  0x35,  0x31,  0x34,  0x36,  0x32,  0x35,  0x34,  0x0,
				 0x03
		};
	}

	public short[] getKeepAlive() {
		return new short[] {0x68, 0x30, 0x31, 0x0, 0x33, 0x30, 0x30, 0x31, 0x0, 0x68, 0x30, 0x32, 0x0, 0x30, 0x34, 0x35, 0x30, 0x39, 0x32, 0x30, 0x34, 0x37, 0x31, 0x34, 0x36, 0x0, 0x2, 0x34, 0x3};
	}

	public short[] getNoValidGps() {
		return new short[] {
				// Netty adds this header (header id, delimiter, header value, delimiter): h01 0 01 0
				0x68, 0x30, 0x31, 0x00, 
				0x33, 0x30, 0x30, 0x31, 0x00,
				// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 1234 0
				0x68, 0x30, 0x32, 0x00, 
				0x31, 0x32, 0x33, 0x34, 0x00,
				// some bogus data to check the drop until feature
				0x01, 0x01,
				0x2, 0x31, 0x0, 0x73, 0x0, 0xb1, 0xf5, 0xea, 0xe4, 0x0, 0x1, 0x0, 0x31, 0x31, 0x32, 0x0, 0xdb, 0xd2, 0x0, 0x32, 0x33, 0x3a, 0x35, 0x31, 0x3a, 0x34, 0x37, 0x0, 0xdb, 0xd3, 0x0, 0x30, 0x31, 0x2f, 0x30, 0x39, 0x2f, 0x31, 0x32, 0x0, 0x4, 0x0, 0x31, 0x31, 0x0, 0x5, 0x0, 0x0, 0x6, 0x0, 0x30, 0x0, 0xa, 0x0, 0x33, 0x31, 0x30, 0x0, 0xb, 0x0, 0x32, 0x36, 0x30, 0x0, 0xc, 0x0, 0x33, 0x32, 0x38, 0x0, 0xd, 0x0, 0x32, 0x39, 0x30, 0x33, 0x33, 0x0, 0xe, 0x0, 0x37, 0x0, 0xf, 0x0, 0x37, 0x0, 0x14, 0x0, 0x34, 0x34, 0x0, 0x15, 0x0, 0x37, 0x37, 0x38, 0x0, 0x16, 0x0, 0x37, 0x38, 0x38, 0x0, 0x17, 0x0, 0x34, 0x0, 0x18, 0x0, 0x31, 0x30, 0x38, 0x0, 0x19, 0x0, 0x30, 0x0, 0xf6, 0x0, 0x33, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x33, 0x35, 0x35, 0x0, 0xf7, 0x0, 0x38, 0x30, 0x35, 0x32, 0x33, 0x32, 0x0, 0xf8, 0x0, 0x30, 0x2c, 0x31, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x2c, 0x30, 0x0, 0xf9, 0x0, 0x33, 0x0, 0xfa, 0x0, 0x39, 0x30, 0x0, 0xfb, 0x0, 0x31, 0x38, 0x35, 0x0, 0xfc, 0x0, 0x31, 0x38, 0x31, 0x0, 0xff, 0x0, 0x31, 0x33, 0x32, 0x36, 0x31, 0x35, 0x33, 0x31, 0x30, 0x37, 0x0, 0x3
		};
	}
	
	public short[] getEnforaKeepAlivePacket() {
		return EnforaFixtures.getEnforaKeepAlivePacket();
	}
	
	public short[] getProdMsg1() {
		return new short[] {
				// Netty adds this header (header id, delimiter, header value, delimiter): h01 0 01 0
				0x68, 0x30, 0x31, 0x00, 
				0x33, 0x30, 0x30, 0x31, 0x00,
				// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 1234 0
				0x68, 0x30, 0x32, 0x00, 
				0x31, 0x32, 0x33, 0x34, 0x00,
				0x2, 0x31, 0x1, 0x45, 0x0, 0xaa, 0xdb, 0xd3, 0xdb, 0xd3, 0xc9, 0x0, 0x1, 0x0, 0x34, 0x0, 0x5, 0x0, 0xff, 0x0, 0x10, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x0, 0x6, 0x0, 0x31, 0x0, 0x9, 0x0, 0x33, 0x39, 0x32, 0x35, 0x33, 0x33, 0x0, 0x13, 0x0, 0x34, 0x36, 0x31, 0x0, 0x1b, 0x0, 0x30, 0x0, 0x1d, 0x0, 0x30, 0x0, 0x23, 0x0, 0x37, 0x32, 0x34, 0x0, 0x24, 0x0, 0x31, 0x36, 0x0, 0x25, 0x0, 0x36, 0x32, 0x31, 0x34, 0x0, 0x26, 0x0, 0x33, 0x36, 0x37, 0x33, 0x0, 0x27, 0x0, 0x32, 0x31, 0x0, 0x28, 0x0, 0x30, 0x0, 0xf6, 0x0, 0x30, 0x2c, 0x34, 0x2c, 0x31, 0x31, 0x39, 0x38, 0x2c, 0x33, 0x30, 0x30, 0x0, 0xf7, 0x0, 0x38, 0x30, 0x39, 0x31, 0x36, 0x32, 0x0, 0xf8, 0x0, 0x30, 0x2c, 0x31, 0x35, 0x36, 0x37, 0x32, 0x37, 0x2c, 0x39, 0x32, 0x2c, 0x32, 0x36, 0x31, 0x2c, 0x30, 0x2c, 0x30, 0x0, 0xf9, 0x0, 0x31, 0x0, 0xfa, 0x0, 0x31, 0x30, 0x36, 0x0, 0xfb, 0x0, 0x31, 0x37, 0x30, 0x0, 0xfc, 0x0, 0x31, 0x36, 0x37, 0x0, 0xff, 0x0, 0x31, 0x33, 0x34, 0x32, 0x33, 0x30, 0x35, 0x30, 0x39, 0x36, 0x0, 0x3
		};		
	}
}
