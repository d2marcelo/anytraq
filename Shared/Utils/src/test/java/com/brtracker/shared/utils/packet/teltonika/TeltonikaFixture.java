package com.brtracker.shared.utils.packet.teltonika;

import com.brtracker.shared.utils.packet.PacketWriterController;

public class TeltonikaFixture {

	
	public short[] getTeltonikaPacket3() {
		short[] packet = new short[] {
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x2, 0xf1, 0x8, 0x19, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xfd, 0xa4, 0xfd, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3f, 0x60, 0x0, 0x7, 0x1, 0x26, 0xb, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xfc, 0xba, 0x25, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3f, 0x20, 0x0, 0x8, 0x1, 0x26, 0xb, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xfb, 0xcf, 0x4e, 0x0, 0xb7, 0x1b, 0x0, 0x0, 0x16, 0x8b, 0x3e, 0xc0, 0x0, 0x8, 0x1, 0x26, 0xb, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xfa, 0xe4, 0x76, 0x0, 0xb7, 0x1b, 0x0, 0x80, 0x16, 0x8b, 0x3e, 0xc0, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf9, 0xf9, 0x9f, 0x0, 0xb7, 0x1b, 0x0, 0x80, 0x16, 0x8b, 0x3f, 0x20, 0x0, 0x8, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf9, 0xe, 0xc7, 0x0, 0xb7, 0x1b, 0x0, 0x80, 0x16, 0x8b, 0x3f, 0x60, 0x0, 0x8, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf8, 0x23, 0xf0, 0x0, 0xb7, 0x1b, 0x1, 0x0, 0x16, 0x8b, 0x3f, 0x80, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf7, 0x39, 0x19, 0x0, 0xb7, 0x1b, 0x1, 0x0, 0x16, 0x8b, 0x3f, 0x60, 0x0, 0xa, 0x1, 0x26, 0x9, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf6, 0x4e, 0x41, 0x0, 0xb7, 0x1b, 0x1, 0x0, 0x16, 0x8b, 0x3f, 0x60, 0x0, 0xa, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf5, 0x63, 0x6a, 0x0, 0xb7, 0x1b, 0x0, 0x80, 0x16, 0x8b, 0x3f, 0x40, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf4, 0x78, 0x94, 0x0, 0xb7, 0x1b, 0x0, 0x0, 0x16, 0x8b, 0x3f, 0x20, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf3, 0x8d, 0xbb, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3f, 0x20, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf2, 0xa2, 0xe3, 0x0, 0xb7, 0x1a, 0xff, 0x0, 0x16, 0x8b, 0x3f, 0x20, 0x0, 0x8, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf1, 0xb8, 0xc, 0x0, 0xb7, 0x1a, 0xff, 0x0, 0x16, 0x8b, 0x3f, 0x0, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xf0, 0xcd, 0x34, 0x0, 0xb7, 0x1a, 0xff, 0x0, 0x16, 0x8b, 0x3e, 0x80, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xef, 0xe2, 0x5d, 0x0, 0xb7, 0x1a, 0xff, 0x0, 0x16, 0x8b, 0x3e, 0x60, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xee, 0xf7, 0x85, 0x0, 0xb7, 0x1a, 0xff, 0x0, 0x16, 0x8b, 0x3e, 0xa0, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xee, 0xc, 0xae, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3e, 0xc0, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xed, 0x21, 0xd6, 0x0, 0xb7, 0x1a, 0xff, 0x0, 0x16, 0x8b, 0x3e, 0xc0, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xec, 0x36, 0xff, 0x0, 0xb7, 0x1a, 0xff, 0x0, 0x16, 0x8b, 0x3e, 0x80, 0x0, 0x9, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xeb, 0x4c, 0x27, 0x0, 0xb7, 0x1a, 0xff, 0x0, 0x16, 0x8b, 0x3e, 0x60, 0x0, 0xa, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xea, 0x61, 0x50, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3e, 0xa0, 0x0, 0xa, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xe9, 0x76, 0x79, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3e, 0xc0, 0x0, 0xa, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xe8, 0x8b, 0xa1, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3e, 0xc0, 0x0, 0xa, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x31, 0x8b, 0xe7, 0xa0, 0xc9, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3e, 0x80, 0x0, 0xb, 0x1, 0x26, 0xa, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x19, 0x0, 0x0, 0x39, 0x1c				
		};
		PacketWriterController writer = new PacketWriterController();
		packet = writer.prependHeader("h01", "3002", packet);
		// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 123423452345 0
		packet = writer.prependHeader("h02", "123423452345", packet);
		return packet;
	};
	
	public short[] getTeltonikaPacket2() {
		short[] packet = new short[] {
				
				0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x2, 0xf1, 
				0x8, 
				0x19, 
				
				0x0, 0x0, 0x1, 0x31, 0x83, 0x7a, 0x86, 0x13, 
				0x0, 
				0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3c, 0x60, 0x0, 0x0, 0x0, 0x76, 0x8, 0x0, 0x0, 
				0x0, 
				0x0, 
				0x0, 
				0x0, 
				0x0, 
				0x0, 
				
				0x0, 0x0, 0x1, 0x31, 0x83, 0x79, 0x9b, 0x3b, 0x0, 0xb7, 0x1a, 0xfe, 0x80, 0x16, 0x8b, 0x3c, 0xa0, 0x0, 0x0, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x78, 0xb0, 0x63, 0x0, 0xb7, 0x1b, 0x0, 0x80, 0x16, 0x8b, 0x3c, 0x60, 0x0, 0x0, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x77, 0xc5, 0x8a, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3c, 0xc0, 0x0, 0x0, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x76, 0xda, 0xb2, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3e, 0x40, 0x0, 0x0, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x75, 0xef, 0xda, 0x0, 0xb7, 0x1a, 0xfd, 0x80, 0x16, 0x8b, 0x3c, 0xe0, 0x0, 0x4, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x75, 0x5, 0x1, 0x0, 0xb7, 0x1a, 0xfd, 0x80, 0x16, 0x8b, 0x3d, 0xc0, 0x0, 0x4, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x74, 0x1a, 0x2a, 0x0, 0xb7, 0x1a, 0xfd, 0x0, 0x16, 0x8b, 0x3f, 0x40, 0x0, 0x5, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x73, 0x2f, 0x51, 0x0, 0xb7, 0x1a, 0xfe, 0x0, 0x16, 0x8b, 0x3d, 0xc0, 0x0, 0x2, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x72, 0x44, 0x79, 0x0, 0xb7, 0x1a, 0xfe, 0x0, 0x16, 0x8b, 0x3c, 0xa0, 0x0, 0x3, 0x0, 0x76, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x71, 0x59, 0xdd, 0x0, 0xb7, 0x1a, 0xfe, 0x0, 0x16, 0x8b, 0x3e, 0x80, 0x0, 0x4, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x70, 0x6f, 0x4, 0x0, 0xb7, 0x1a, 0xfa, 0x80, 0x16, 0x8b, 0x40, 0x20, 0x0, 0x2, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x6f, 0x84, 0x2c, 0x0, 0xb7, 0x1a, 0xfb, 0x0, 0x16, 0x8b, 0x3f, 0x40, 0x0, 0x2, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x6e, 0x99, 0x54, 0x0, 0xb7, 0x1a, 0xfc, 0x0, 0x16, 0x8b, 0x3f, 0xc0, 0x0, 0x5, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x6d, 0xae, 0x7c, 0x0, 0xb7, 0x1a, 0xfe, 0x0, 0x16, 0x8b, 0x3e, 0x60, 0x0, 0x5, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x6c, 0xc3, 0xa4, 0x0, 0xb7, 0x1a, 0xfe, 0x0, 0x16, 0x8b, 0x3e, 0xc0, 0x0, 0x1, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x6b, 0xd8, 0xcb, 0x0, 0xb7, 0x1b, 0x0, 0x80, 0x16, 0x8b, 0x3f, 0xc0, 0x0, 0x4, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x6a, 0xed, 0xf3, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x3e, 0x80, 0x0, 0xb, 0x0, 0x76, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x6a, 0x3, 0x1b, 0x0, 0xb7, 0x1b, 0x0, 0x0, 0x16, 0x8b, 0x3e, 0x40, 0x0, 0x11, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x69, 0x18, 0x45, 0x0, 0xb7, 0x1b, 0x1, 0x80, 0x16, 0x8b, 0x3d, 0xe0, 0x0, 0x15, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x68, 0x2d, 0x6b, 0x0, 0xb7, 0x1b, 0x4, 0x0, 0x16, 0x8b, 0x3e, 0x60, 0x0, 0x18, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x67, 0x42, 0x92, 0x0, 0xb7, 0x1b, 0x0, 0x80, 0x16, 0x8b, 0x3e, 0x80, 0x0, 0x13, 0x0, 0x76, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x66, 0x57, 0xba, 0x0, 0xb7, 0x1a, 0xff, 0x80, 0x16, 0x8b, 0x40, 0x80, 0x0, 0x12, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x65, 0x6c, 0xe2, 0x0, 0xb7, 0x1b, 0x0, 0x0, 0x16, 0x8b, 0x3f, 0x60, 0x0, 0x14, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0x83, 0x64, 0x82, 0xa, 0x0, 0xb7, 0x1a, 0xfe, 0x0, 0x16, 0x8b, 0x3e, 0xa0, 0x0, 0xc, 0x0, 0x76, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				
				0x19, 
				
				0x0, 0x0, 0xb5, 0xc3,
		};
		
		PacketWriterController writer = new PacketWriterController();
		packet = writer.prependHeader("h01", "3002", packet);
		// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 123423452345 0
		packet = writer.prependHeader("h02", "123423452345", packet);
		return packet;

	}

	public short[] getTeltonikaPacket4() {
		short[] packet = new short[] {
				0x68, 0x30, 0x31, 0x0, 0x33, 0x30, 0x30, 0x32, 0x0, 
				0x68, 0x30, 0x32, 0x0, 0x33, 0x35, 0x34, 0x33, 0x33, 0x30, 0x30, 0x33, 0x30, 0x36, 0x31, 0x36, 0x36, 0x30, 0x34, 0x0, 
				0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x3, 0xd6, 
				0x8, 
				0xb, 
				0x0, 0x0, 0x1, 0x33, 0x21, 0x7f, 0xdf, 0xc6, 
				0x0, 
				0xb7, 0x58, 0xd5, 0x0, 0x16, 0x48, 0xb1, 0xc0, 0x0, 0x18, 0x1, 0x3f, 0xb, 0x0, 0x0, 
				0x0, 
				0x10, 
				0x3, 
				0x15, 
				0x3, 
				0xf0, 
				0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0x13, 0x43, 0x24, 0x1c, 0x44, 0x0, 0x0, 0x42, 0x2f, 0xcd, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x61, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x33, 0x21, 0x7f, 0x67, 0x9, 0x0, 0xb7, 0x58, 0xd5, 0x0, 0x16, 0x48, 0xb1, 0xe0, 0x0, 0x18, 0x1, 0x3f, 0xb, 0x0, 0x0, 0x0, 0x10, 0x3, 0x15, 0x3, 0xf0, 0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0xf, 0x43, 0x24, 0x9, 0x44, 0x0, 0x0, 0x42, 0x2f, 0x40, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x60, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x33, 0x21, 0x7e, 0xee, 0x4a, 0x0, 0xb7, 0x58, 0xd4, 0x80, 0x16, 0x48, 0xb1, 0xe0, 0x0, 0x18, 0x1, 0x3f, 0xb, 0x0, 0x0, 0x0, 0x10, 0x3, 0x15, 0x3, 0xf0, 0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0x0, 0x43, 0x24, 0x4, 0x44, 0x0, 0x0, 0x42, 0x2f, 0x7b, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x60, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x33, 0x21, 0x7e, 0x75, 0x8d, 0x0, 0xb7, 0x58, 0xd4, 0x80, 0x16, 0x48, 0xb1, 0xe0, 0x0, 0x17, 0x1, 0x3f, 0xb, 0x0, 0x0, 0x0, 0x10, 0x3, 0x15, 0x3, 0xf0, 0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0x1, 0x43, 0x24, 0x1a, 0x44, 0x0, 0x0, 0x42, 0x2f, 0xa0, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x60, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x33, 0x21, 0x7d, 0xfc, 0xd0, 0x0, 0xb7, 0x58, 0xd4, 0x80, 0x16, 0x48, 0xb1, 0xa0, 0x0, 0x17, 0x1, 0x3f, 0xb, 0x0, 0x0, 0x0, 0x10, 0x3, 0x15, 0x3, 0xf0, 0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0xa, 0x43, 0x24, 0xf, 0x44, 0x0, 0x0, 0x42, 0x2f, 0x47, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x61, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x33, 0x21, 0x7d, 0x84, 0x11, 0x0, 0xb7, 0x58, 0xd4, 0x80, 0x16, 0x48, 0xb2, 0x0, 0x0, 0x17, 0x1, 0x3f, 0xb, 0x0, 0x0, 0x0, 0x10, 0x3, 0x15, 0x3, 0xf0, 0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0x0, 0x43, 0x24, 0x4, 0x44, 0x0, 0x0, 0x42, 0x2f, 0x49, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x62, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x33, 0x21, 0x7d, 0xb, 0x5d, 0x0, 0xb7, 0x58, 0xd4, 0x80, 0x16, 0x48, 0xb2, 0x0, 0x0, 0x18, 0x1, 0x3f, 0xb, 0x0, 0x0, 0x0, 0x10, 0x3, 0x15, 0x3, 0xf0, 0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0x14, 0x43, 0x24, 0x19, 0x44, 0x0, 0x0, 0x42, 0x2f, 0x96, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x61, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x33, 0x21, 0x7c, 0x92, 0x9e, 0x0, 0xb7, 0x58, 0xd4, 0x0, 0x16, 0x48, 0xb2, 0x20, 0x0, 0x18, 0x1, 0x3f, 0xb, 0x0, 0x0, 0x0, 0x10, 0x3, 0x15, 0x3, 0xf0, 0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0x3, 0x43, 0x24, 0x4, 0x44, 0x0, 0x0, 0x42, 0x2f, 0x64, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x61, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x33, 0x21, 0x7c, 0x19, 0xe0, 0x0, 0xb7, 0x58, 0xd4, 0x0, 0x16, 0x48, 0xb2, 0x0, 0x0, 0x17, 0x1, 0x3f, 0xb, 0x0, 0x0, 0x0, 0x10, 0x3, 0x15, 0x3, 0xf0, 0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0xa, 0x43, 0x24, 0x12, 0x44, 0x0, 0x0, 0x42, 0x2f, 0x7b, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x62, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x33, 0x21, 0x7b, 0xa1, 0x22, 0x0, 0xb7, 0x58, 0xd4, 0x0, 0x16, 0x48, 0xb2, 0x20, 0x0, 0x18, 0x1, 0x3f, 0xb, 0x0, 0x0, 0x0, 0x10, 0x3, 0x15, 0x3, 0xf0, 0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0x3, 0x43, 0x24, 0x8, 0x44, 0x0, 0x0, 0x42, 0x2f, 0xb1, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x61, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x33, 0x21, 0x7b, 0x28, 0x29, 0x0, 0xb7, 0x58, 0xd4, 0x0, 0x16, 0x48, 0xb2, 0x80, 0x0, 0x17, 0x1, 0x3f, 0xb, 0x0, 0x0, 0x0, 0x10, 0x3, 0x15, 0x3, 0xf0, 0x0, 0x91, 0x0, 0x6, 0x9, 0x0, 0xe, 0x43, 0x24, 0x13, 0x44, 0x0, 0x0, 0x42, 0x2f, 0x7e, 0x45, 0x0, 0x1, 0x18, 0x0, 0x0, 0x7, 0x17, 0x0, 0x0, 0x0, 0x61, 0x4c, 0x0, 0x0, 0x0, 0x0, 0xc7, 0x0, 0x0, 0x0, 0x0, 0x95, 0x0, 0x0, 0x0, 0x0, 0x94, 0x0, 0x0, 0x0, 0x0, 0x93, 0x0, 0x0, 0x0, 0x0, 0x92, 0x0, 0x0, 0x0, 0x0, 0x0, 0xb, 0x0, 0x0, 0xa7, 0x9a, 0x0		
		};
		return packet;
	}

	public short[] getTeltonikaPacket() {
		short[] packet = new short[] {
				0x0, 0x0, 0x0, 0x0, // four zeros 
				0x0, 0x0, 0x2, 0xf1, // data length
				0x8, // codeid
				0x19, // # of data elements
				
				// first AVL packet
				0x0, 0x0, 0x1, 0x31, 0xa, 0x32, 0xb7, 0xee, // timestamp
				0x0, // priority
				0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc9, 0x0, 0x0, 0x20, 0x0, 0x9f, 0x7, 0x0, 0x0, // GPS element 
				0x0, // event id
				0x0, // total number of properties
				0x0, // total number of properties of 1 byte length
				0x0, // total number of properties of 2 byte length
				0x0, // total number of properties of 4 byte length
				0x0, // total number of properties of 8 byte length
				
				// second AVL packet
				0x0, 0x0, 0x1, 0x31, 0xa, 0x31, 0xcd, 0x16, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc8, 0xc0, 0x0, 0x20, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x30, 0xe2, 0x66, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc8, 0xc0, 0x0, 0x20, 0x0, 0x9f, 0x6, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x2f, 0xf7, 0x8d, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc8, 0xc0, 0x0, 0x20, 0x0, 0x9f, 0x6, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x2f, 0xc, 0xb5, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc8, 0xa0, 0x0, 0x20, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x2e, 0x21, 0xdd, 0x0, 0xb7, 0x58, 0xe0, 0x0, 0x16, 0x48, 0xc8, 0x60, 0x0, 0x1f, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x2d, 0x37, 0x5, 0x0, 0xb7, 0x58, 0xe0, 0x0, 0x16, 0x48, 0xc8, 0x40, 0x0, 0x1f, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x2c, 0x4c, 0x2d, 0x0, 0xb7, 0x58, 0xe0, 0x0, 0x16, 0x48, 0xc8, 0x40, 0x0, 0x1f, 0x0, 0x9f, 0x6, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x2b, 0x61, 0x54, 0x0, 0xb7, 0x58, 0xe0, 0x0, 0x16, 0x48, 0xc8, 0x20, 0x0, 0x1f, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x2a, 0x76, 0x7c, 0x0, 0xb7, 0x58, 0xe0, 0x0, 0x16, 0x48, 0xc7, 0xe0, 0x0, 0x1f, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x29, 0x8b, 0xa4, 0x0, 0xb7, 0x58, 0xe0, 0x0, 0x16, 0x48, 0xc7, 0xc0, 0x0, 0x20, 0x0, 0x9f, 0x6, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x28, 0xa0, 0xcc, 0x0, 0xb7, 0x58, 0xe0, 0x0, 0x16, 0x48, 0xc7, 0xc0, 0x0, 0x20, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x27, 0xb5, 0xf3, 0x0, 0xb7, 0x58, 0xe0, 0x0, 0x16, 0x48, 0xc7, 0xc0, 0x0, 0x1f, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x26, 0xcb, 0x57, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc7, 0xe0, 0x0, 0x20, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x25, 0xe0, 0x7f, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc7, 0xe0, 0x0, 0x20, 0x0, 0x9f, 0x6, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x24, 0xf5, 0xa7, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc7, 0xe0, 0x0, 0x21, 0x0, 0x9f, 0x6, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x1, 0x31, 0xa, 0x24, 0xa, 0xcf, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc7, 0xe0, 0x0, 0x21, 0x0, 0x9f, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x23, 0x1f, 0xf6, 0x0, 0xb7, 0x58, 0xdf, 0x0, 0x16, 0x48, 0xc7, 0xe0, 0x0, 0x21, 0x0, 0x9f, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x22, 0x35, 0x1e, 0x0, 0xb7, 0x58, 0xdf, 0x0, 0x16, 0x48, 0xc7, 0xc0, 0x0, 0x21, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x21, 0x4a, 0x46, 0x0, 0xb7, 0x58, 0xdf, 0x0, 0x16, 0x48, 0xc7, 0xe0, 0x0, 0x21, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x20, 0x5f, 0x6e, 0x0, 0xb7, 0x58, 0xdf, 0x0, 0x16, 0x48, 0xc8, 0x20, 0x0, 0x21, 0x0, 0x9f, 0x6, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x1f, 0x74, 0x95, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc8, 0x40, 0x0, 0x21, 0x0, 0x9f, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x1e, 0x89, 0xbd, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc8, 0x80, 0x0, 0x21, 0x0, 0x9f, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x1d, 0x9e, 0xe5, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc8, 0xa0, 0x0, 0x21, 0x0, 0x9f, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				0x0, 0x0, 0x1, 0x31, 0xa, 0x1c, 0xb4, 0xd, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc8, 0x80, 0x0, 0x22, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 
				
				0x19, // # of data elements

				0x0, 0x0, 0xe4, 0x3c, // crc
		};
		
		PacketWriterController writer = new PacketWriterController();
		packet = writer.prependHeader("h01", "3002", packet);
		// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 123423452345 0
		packet = writer.prependHeader("h02", "123423452345", packet);
		return packet;

	}

	public short[] getTeltonikaPacketWithFakeFuel() {
		
		short[] packet = new short[] {
				0x0, 0x0, 0x0, 0x0, // four zeros 
				0x0, 0x0, 0x2, 0xf1, // data length
				0x8, // codeid
				0x02, // # of data elements
				
				// first AVL packet
				0x0, 0x0, 0x1, 0x31, 0xa, 0x32, 0xb7, 0xee, // timestamp
				0x0, // priority
				0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc9, 0x0, 0x0, 0x20, 0x0, 0x9f, 0x7, 0x0, 0x0, // GPS element 
				0x0, // event id
				0x1, // total number of properties
				0x1, // total number of properties of 1 byte length
				0x1C, // 28 (self defined) fuel level
				0x17, 
				0x0, // total number of properties of 2 byte length
				0x3, // total number of properties of 4 byte length
				0x1D, // 29 (self defined) total fuel
				0x00, 0x00, 0x00, 0x21,
				0x1B, // 27 (self defined) fuel economy 
				0x00, 0x00, 0x00, 0x22,
				0x1A, // 26 (self defined) total distance
				0x00, 0x00, 0x00, 0x23,
				0x0, // total number of properties of 8 byte length
				
				// second AVL packet
				0x0, 0x0, 0x1, 0x31, 0xa, 0x31, 0xcd, 0x16, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc8, 0xc0, 0x0, 0x20, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				
				0x19, // # of data elements

				0x0, 0x0, 0xe4, 0x3c, // crc
		};
		
		PacketWriterController writer = new PacketWriterController();
		packet = writer.prependHeader("h01", "3002", packet);
		// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 123423452345 0
		packet = writer.prependHeader("h02", "123423452345", packet);
		return packet;

	}

	
	
	public short[] getTeltonikaPacketWithRealFuel2() {
		
		short[] packet = new short[] {
				0x0,0x0,0x0,0x0,0x0,0x0,0x3,0xc3,0x8,0xc,0x0,0x0,0x1,0x31,0xc5,0xe3,0xc2,0x1,0x0,0xb7,0x40,0xa7,0x0,0x16,0xa6,0xd9,0x80,0x0,0xb,0x0,0xf3,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0x1d,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6d,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xe2,0xd4,0x1d,0x0,0xb7,0x40,0xa7,0x0,0x16,0xa6,0xd9,0x80,0x0,0xb,0x0,0xf3,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x2,0xf0,0x0,0x92,0x0,0x3,0x43,0x22,0x89,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6c,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xe1,0xe6,0x3a,0x0,0xb7,0x40,0xa7,0x0,0x16,0xa6,0xd9,0x80,0x0,0xb,0x0,0xf3,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x2,0xf0,0x1,0x92,0x0,0x3,0x43,0x23,0x2,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6b,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xe0,0xf8,0x56,0x0,0xb7,0x40,0xa7,0x80,0x16,0xa6,0xd9,0x80,0x0,0xb,0x0,0x80,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x2,0xf0,0x0,0x92,0x0,0x3,0x43,0x22,0x8a,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6d,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xe0,0xa,0x72,0x0,0xb7,0x40,0xa8,0x0,0x16,0xa6,0xd9,0xa0,0x0,0xc,0x1,0x40,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0x45,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6c,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xdf,0x1c,0x8f,0x0,0xb7,0x40,0xa8,0x0,0x16,0xa6,0xd9,0x80,0x0,0xc,0x1,0x40,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0x31,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6d,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xde,0x2e,0xab,0x0,0xb7,0x40,0xa8,0x0,0x16,0xa6,0xd9,0x80,0x0,0xc,0x1,0xa,0x8,0x0,0x0,0x0,0xd,0x3,0x15,0x2,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0x1c,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6c,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xdd,0x40,0xc7,0x0,0xb7,0x40,0xa8,0x0,0x16,0xa6,0xd9,0x80,0x0,0xd,0x1,0xa,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x0,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0x4,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6c,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xdc,0x52,0xe4,0x0,0xb7,0x40,0xa8,0x0,0x16,0xa6,0xd9,0x80,0x0,0xd,0x1,0xa,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x22,0xd6,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6c,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xdb,0x65,0x0,0x0,0xb7,0x40,0xa8,0x80,0x16,0xa6,0xd9,0xa0,0x0,0xd,0x1,0xa,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x22,0x95,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6c,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xda,0x78,0x16,0x0,0xb7,0x40,0xa8,0x80,0x16,0xa6,0xd9,0xe0,0x0,0xd,0x0,0x14,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x21,0xe3,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6b,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xc5,0xd9,0x8a,0x32,0x0,0xb7,0x40,0xa8,0x80,0x16,0xa6,0xd9,0xe0,0x0,0xe,0x0,0x14,0x9,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x21,0xc0,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6c,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0xc,0x0,0x0,0x5b,0xe8
		};
		
		PacketWriterController writer = new PacketWriterController();
		packet = writer.prependHeader("h01", "3002", packet);
		// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 123423452345 0
		packet = writer.prependHeader("h02", "123423452345", packet);
		return packet;

	}

	public short[] getTeltonikaPacketWithRealFuel() {
		
		short[] packet = new short[] {
				
				0x0,0x0,0x0,0x0,
				0x0,0x0,0x3,0xc3,
				0x8,
				0xc,
				0x0,0x0,0x1,0x31,0xbc,0x89,0x4e,0x85,
				0x0,
				0xb7,0x40,0x9a,0x0,0x16,0xa6,0xd9,0x80,0x0,0x1e,0x0,0x0,0x4,0x0,0x0,
				0x0,
				0xd,
				0x3,
				0x15,0x3,
				0xf0,0x0,
				0x92,0x0,
				0x3,
				0x43,0x21,0x75,
				0x45,0x0,0x1,
				0x18,0x0,0x0,
				0x7,
				0x17,0x0,0x0,0x0,0x77,
				0x4c,0x0,0x0,0x0,0x0,
				0xc7,0x0,0x0,0x0,0x0,
				0x95,0x0,0x0,0x0,0x0,
				0x94,0x0,0x0,0x0,0x0,
				0x93,0x0,0x0,0x0,0x0,
				0x91,0x0,0x0,0x0,0x0,
				0x0,
				
				0x0,0x0,0x1,0x31,0xbc,0x63,0x1c,0x3,
				0x0,
				0xb7,0x36,0x6,0x80,0x16,0x75,0x3c,0xe0,0x0,0x8,0x1,0x33,0xa,0x0,0x5c,
				0x0,
				0xd,
				0x3,
				0x15,0x4,
				0xf0,0x1,
				0x92,0x0,
				0x3,
				0x43,0x1d,0xa8,
				0x45,0x0,0x1,
				0x18,0x0,0x0,
				0x7,
				0x17,0x0,0x0,0x0,0x74,
				0x4c,0x0,0x0,0x0,0x0,
				0xc7,0x0,0x0,0x3,0xf5,
				0x95,0x0,0x0,0x0,0x0,
				0x94,0x0,0x0,0x0,0x0,
				0x93,0x0,0x0,0x0,0x0,
				0x91,0x0,0x0,0x0,0x0,
				0x0,

				0x0,0x0,0x1,0x31,0xbc,0x62,0x87,0xf7,
				0x0,
				0xb7,0x37,0x4f,0x80,0x16,0x74,0x4b,0x0,0x0,0x11,0x1,0x45,0xa,0x0,0x60,
				0x0,
				0xd,
				0x3,
				0x15,0x4,
				0xf0,0x1,
				0x92,0x0,
				0x3,
				0x43,0x1d,0xf4,
				0x45,0x0,0x1,
				0x18,0x0,0x0,
				0x7,
				0x17,0x0,0x0,0x0,0x78,
				0x4c,0x0,0x0,0x0,0x0,
				0xc7,0x0,0x0,0x3,0xf4,
				0x95,0x0,0x0,0x0,0x0,
				0x94,0x0,0x0,0x0,0x0,
				0x93,0x0,0x0,0x0,0x0,
				0x91,0x0,0x0,0x0,0x0,
				0x0,
				
				0x0,0x0,0x1,0x31,0xbc,0x61,0xf0,0x2,0x0,0xb7,0x38,0x1c,0x80,0x16,0x73,0xe,0xc0,0x0,0xd,0x1,0x4d,0xa,0x0,0x5f,0x0,0xd,0x3,0x15,0x4,0xf0,0x1,0x92,0x0,0x3,0x43,0x1e,0x6e,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x75,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x3,0xec,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,
				0x0,0x0,0x1,0x31,0xbc,0x61,0x5b,0xf6,0x0,0xb7,0x38,0xdf,0x80,0x16,0x71,0xd1,0x60,0x0,0x10,0x1,0x4d,0xa,0x0,0x5c,0x0,0xd,0x3,0x15,0x4,0xf0,0x1,0x92,0x0,0x3,0x43,0x1e,0xb0,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x78,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x3,0xf8,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,
				0x0,0x0,0x1,0x31,0xbc,0x60,0xc4,0x2,0x0,0xb7,0x39,0xac,0x0,0x16,0x70,0x93,0x40,0x0,0xe,0x1,0x4a,0xa,0x0,0x5b,0x0,0xd,0x3,0x15,0x4,0xf0,0x1,0x92,0x0,0x3,0x43,0x1e,0xfc,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x75,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x3,0xeb,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,
				0x0,0x0,0x1,0x31,0xbc,0x60,0x2c,0xe,0x0,0xb7,0x3a,0x86,0x0,0x16,0x6f,0x5f,0xe0,0x0,0x17,0x1,0x49,0xa,0x0,0x5b,0x0,0xd,0x3,0x15,0x4,0xf0,0x1,0x92,0x0,0x3,0x43,0x1f,0x46,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x73,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x3,0xfc,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,
				0x0,0x0,0x1,0x31,0xbc,0x5f,0x8c,0x4a,0x0,0xb7,0x3b,0x67,0x80,0x16,0x6e,0x29,0x0,0x0,0x9,0x1,0x49,0xa,0x0,0x57,0x0,0xd,0x3,0x15,0x4,0xf0,0x1,0x92,0x0,0x3,0x43,0x1f,0x8a,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x74,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x3,0xff,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,
				0x0,0x0,0x1,0x31,0xbc,0x5e,0xec,0x86,0x0,0xb7,0x3c,0x47,0x0,0x16,0x6c,0xf0,0x20,0x0,0x3,0x1,0x4a,0xa,0x0,0x57,0x0,0xd,0x3,0x15,0x4,0xf0,0x1,0x92,0x0,0x3,0x43,0x1f,0x81,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x76,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x3,0xf2,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,
				0x0,0x0,0x1,0x31,0xbc,0x5e,0x50,0xaa,0x0,0xb7,0x3d,0x23,0x0,0x16,0x6b,0xbb,0x20,0x0,0x4,0x1,0x4a,0xa,0x0,0x5b,0x0,0xd,0x3,0x15,0x4,0xf0,0x1,0x92,0x0,0x3,0x43,0x20,0x35,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x74,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x3,0xf0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,
				0x0,0x0,0x1,0x31,0xbc,0x5d,0xb8,0xb6,0x0,0xb7,0x3d,0xff,0x80,0x16,0x6a,0x86,0xa0,0x0,0xd,0x1,0x4a,0xa,0x0,0x5a,0x0,0xd,0x3,0x15,0x4,0xf0,0x1,0x92,0x0,0x3,0x43,0x20,0x69,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x73,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x3,0xeb,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,
				0x0,0x0,0x1,0x31,0xbc,0x5d,0x1c,0xd9,0x0,0xb7,0x3e,0xda,0x0,0x16,0x69,0x53,0x40,0x0,0x8,0x1,0x4a,0x9,0x0,0x5c,0x0,0xd,0x3,0x15,0x4,0xf0,0x1,0x92,0x0,0x3,0x43,0x20,0x9e,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x77,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x3,0xeb,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,
				0xc,
				0x0,0x0,0x15,0x50
				
				
//				0x0, 0x0, 0x0, 0x0, // four zeros 
//				0x0, 0x0, 0x2, 0xf1, // data length
//				0x8, // codeid
//				0x02, // # of data elements
//				
//				// first AVL packet
//				0x0, 0x0, 0x1, 0x31, 0xa, 0x32, 0xb7, 0xee, // timestamp
//				0x0, // priority
//				0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc9, 0x0, 0x0, 0x20, 0x0, 0x9f, 0x7, 0x0, 0x0, // GPS element 
//				0x0, // event id
//				0x1, // total number of properties
//				0x1, // total number of properties of 1 byte length
//				0x1C, // 28 (self defined) fuel level
//				0x17, 
//				0x0, // total number of properties of 2 byte length
//				0x3, // total number of properties of 4 byte length
//				0x1D, // 29 (self defined) total fuel
//				0x00, 0x00, 0x00, 0x21,
//				0x1B, // 27 (self defined) fuel economy 
//				0x00, 0x00, 0x00, 0x22,
//				0x1A, // 26 (self defined) total distance
//				0x00, 0x00, 0x00, 0x23,
//				0x0, // total number of properties of 8 byte length
//				
//				// second AVL packet
//				0x0, 0x0, 0x1, 0x31, 0xa, 0x31, 0xcd, 0x16, 0x0, 0xb7, 0x58, 0xdf, 0x80, 0x16, 0x48, 0xc8, 0xc0, 0x0, 0x20, 0x0, 0x9f, 0x7, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
//				
//				0x19, // # of data elements
//
//				0x0, 0x0, 0xe4, 0x3c, // crc
//				0x0,0x0,0x0,0x0,0x0,0x0,0x3,0xc3,0x8,0xc,0x0,0x0,0x1,0x31,0xbc,0x35,0xc9,0xca,0x0,0xb7,0x4b,0x10,0x0,0x16,0x5c,0xad,0xe0,0x0,0x10,0x0,0xd4,0x8,0x0,0x2a,0x0,0xd,0x3,0x15,0x3,0xf0,0x1,0x92,0x0,0x3,0x43,0x21,0xa7,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x66,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x2,0x4,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x34,0xdb,0xe5,0x0,0xb7,0x4a,0x9d,0x80,0x16,0x5d,0x21,0x40,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x1,0x92,0x0,0x3,0x43,0x22,0xa6,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x67,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x33,0xed,0xf7,0x0,0xb7,0x4a,0x9d,0x0,0x16,0x5d,0x21,0x60,0x0,0x16,0x1,0x50,0x8,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0xf1,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x6a,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x33,0x0,0x13,0x0,0xb7,0x4a,0x9d,0x0,0x16,0x5d,0x21,0x60,0x0,0x15,0x1,0x50,0x8,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0xda,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x67,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x32,0x12,0x2f,0x0,0xb7,0x4a,0x9d,0x80,0x16,0x5d,0x21,0x60,0x0,0x14,0x1,0x50,0x8,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0xbe,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x68,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x31,0x24,0x4b,0x0,0xb7,0x4a,0x9d,0x80,0x16,0x5d,0x21,0x60,0x0,0x14,0x1,0x50,0x8,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0xa6,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x64,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x30,0x36,0x67,0x0,0xb7,0x4a,0x9e,0x80,0x16,0x5d,0x21,0x60,0x0,0x15,0x1,0x50,0x7,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x1,0x92,0x0,0x3,0x43,0x23,0x8d,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x67,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x3,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x2f,0x48,0x83,0x0,0xb7,0x4a,0x9e,0x0,0x16,0x5d,0x21,0xa0,0x0,0x14,0x1,0x50,0x8,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0x73,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x69,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x2e,0x5a,0x94,0x0,0xb7,0x4a,0x9e,0x0,0x16,0x5d,0x22,0x0,0x0,0x15,0x1,0x50,0x7,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x23,0x51,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x67,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x2d,0x6c,0xb0,0x0,0xb7,0x4a,0x9e,0x80,0x16,0x5d,0x21,0xc0,0x0,0x15,0x1,0x50,0x8,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x1,0x92,0x0,0x3,0x43,0x23,0x30,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x68,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x2c,0x7e,0xcc,0x0,0xb7,0x4a,0x9e,0x0,0x16,0x5d,0x22,0x40,0x0,0x15,0x1,0x50,0x7,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x1,0x92,0x0,0x3,0x43,0x23,0x10,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x68,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x31,0xbc,0x2b,0x94,0x58,0x0,0xb7,0x4a,0x9e,0x80,0x16,0x5d,0x22,0x40,0x0,0x15,0x1,0x50,0x8,0x0,0x0,0x0,0xd,0x3,0x15,0x3,0xf0,0x0,0x92,0x0,0x3,0x43,0x22,0xf0,0x45,0x0,0x1,0x18,0x0,0x0,0x7,0x17,0x0,0x0,0x0,0x67,0x4c,0x0,0x0,0x0,0x0,0xc7,0x0,0x0,0x0,0x0,0x95,0x0,0x0,0x0,0x0,0x94,0x0,0x0,0x0,0x0,0x93,0x0,0x0,0x0,0x0,0x91,0x0,0x0,0x0,0x0,0x0,0xc,0x0,0x0,0x64,0x7a
				
		};
		
		PacketWriterController writer = new PacketWriterController();
		packet = writer.prependHeader("h01", "3002", packet);
		// Netty adds this header (header id, delimiter, header value, delimiter): h02 0 123423452345 0
		packet = writer.prependHeader("h02", "123423452345", packet);
		return packet;

	}

	public short[] getTeltonikaPresentationPacket() {
		
		short[] packet = new short[] {
				0, 15, 51, 53, 52, 51, 51, 48, 48, 51, 48, 54, 49, 54, 54, 48, 52
		};
		return packet;

	}

}
