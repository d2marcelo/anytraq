package com.brtracker.services.netty;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CheckBytes {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
//		byte[] b = {0x0, 0xa, 0x2, 0x0, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x31, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20,20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x41, 0x4e, 0x59, 0x54, 0x52, 0x41, 0x51, 0x20};
//		String s = new String(b);
//		System.out.println(s);
		
           // get a datagram socket
       DatagramSocket socket = new DatagramSocket();

           // send request
       byte[] buf = {0x0, 0xA, 0x1, 0x1};
       //074.126.080.228, 066.018.199.228
       //074.126.088.004, 199.245.180.013
       // 10.082.037.019
       //74.126.88.4 41804
       InetAddress address = InetAddress.getByName("74.126.88.4");
       DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 41804);
       socket.send(packet);
    
       socket.close();
       System.out.println("Packet sent");
	}

}
