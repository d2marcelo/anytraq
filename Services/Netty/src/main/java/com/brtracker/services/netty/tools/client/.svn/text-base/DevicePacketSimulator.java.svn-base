package com.brtracker.services.netty.tools.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import edu.emory.mathcs.backport.java.util.Arrays;

public class DevicePacketSimulator {

    public static void main(String[] args) throws Exception {
    	
        if (args.length < 3) {
            System.err.println(
                    "Usage: " + DevicePacketSimulator.class.getSimpleName() +
                    " <host> <port> <filename>");
            return;
        }

        // Parse options.
        final String host = args[0];
        final int port = Integer.parseInt(args[1]);
        final String fileName = args[2];
        
        // Configure the client.
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Set up the pipeline factory.
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new PicoloClientHandler(fileName));
            }
        });

        // Start the connection attempt.
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));

        // Wait until the connection is closed or the connection attempt fails.
        future.getChannel().getCloseFuture().awaitUninterruptibly();

        // Shut down thread pools to exit.
        bootstrap.releaseExternalResources();
    }

    private static class PicoloClientHandler extends SimpleChannelUpstreamHandler {
    	
        private String fileName;
        private List<short[]> packets = new ArrayList<short[]>();
        private int currentPacket = 0;

        /**
         * Creates a client-side handler.
         * @throws Exception 
         */
        public PicoloClientHandler(String fileName) throws Exception {        	
        	this.fileName = fileName;
        	loadPackets();
        }

        private void loadPackets() throws Exception {
        	
    		BufferedReader in = null;
    		try {
    		    in = new BufferedReader(new FileReader(fileName));
    		    String line = "";
    		    while ((line = in.readLine()) != null) {
    		    	if (line.trim().length() == 0) {
    		    		continue;
    		    	}
    		    	int colonPos = line.indexOf(':');
    		    	if (colonPos != -1) {
    		    		line = line.substring(colonPos+1).trim();
    		    	}
    		    	short[] packet = new short[1500];
    		    	int packetIndx = 0;
    		    	StringTokenizer st = new StringTokenizer(line, " ");
    		    	while (st.hasMoreTokens()) {
    		    		String shortValue = st.nextToken();
    		    		packet[packetIndx++] = Short.valueOf(shortValue, 16);
    		    	}
    		    	packets.add(Arrays.copyOfRange(packet, 0, packetIndx));
    		    }
    		} finally {
    			if (in != null) {
    				in.close();
    			}
    		}
			
		}

		@Override
        public void channelConnected(
                ChannelHandlerContext ctx, ChannelStateEvent e) {
        	
			System.out.println("sending message ...");
			
			if (currentPacket < packets.size()) {
				short[] p = packets.get(currentPacket++);
				ChannelBuffer buffer = ChannelBuffers.buffer(p.length);
				for (int i = 0; i<p.length; i++) {
					buffer.writeByte(p[i]);
				}
				e.getChannel().write(buffer);
			} else {
				System.out.println("No more packets to send to Netty");
			}
        }

        @Override
        public void messageReceived(
                ChannelHandlerContext ctx, MessageEvent e) {
        	Object message = e.getMessage();
        	System.out.println("message received " + message);
        }

        @Override
        public void exceptionCaught(
                ChannelHandlerContext ctx, ExceptionEvent e) {
            e.getChannel().close();
        }
    	
    }
}
