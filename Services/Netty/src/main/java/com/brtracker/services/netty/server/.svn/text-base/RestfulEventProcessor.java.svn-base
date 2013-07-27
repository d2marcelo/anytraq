package com.brtracker.services.netty.server;

import static com.brtracker.shared.utils.SystemConfiguration.NETTY;
import static org.jboss.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.COOKIE;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.SET_COOKIE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.util.Set;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.http.Cookie;
import org.jboss.netty.handler.codec.http.CookieDecoder;
import org.jboss.netty.handler.codec.http.CookieEncoder;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;

import com.brtracker.shared.utils.activemq.JmsSender;
import com.brtracker.shared.utils.logging.MyLogger;

public class RestfulEventProcessor implements MessageEventProcessor, ChannelHandler {

	private MyLogger logger = new MyLogger(RestfulEventProcessor.class);
	private JmsSender jmsSender;

	@Override
	public void processMessage(MessageEvent event, int port) {

		try {
			logger.logInfo("Handling restul message ");
			
	        HttpRequest request = (HttpRequest) event.getMessage();
	        ChannelBuffer content = request.getContent();
	        String payload = content.toString(CharsetUtil.UTF_8);
	        
	        logger.logInfo("Sending message " + payload);
	        
	        jmsSender.sendTextMessage(payload, NETTY);
	        
	        sendOkResponse(event);
	        
	        logger.logInfo("Finished restful message processing ");
	        
		} catch (Exception ex) {
			logger.logError("Unexpcted exception while processing message", ex);
		}

	}

	private void sendOkResponse(MessageEvent event) {
        StringBuilder outBuf = new StringBuilder();
        outBuf.append("{\"status\":\"OK\"}");
        writeResponse(event, outBuf);
	}
	
    private void writeResponse(MessageEvent e, StringBuilder buf) {
    	
    	HttpRequest request = (HttpRequest) e.getMessage();
    	
        // Decide whether to close the connection or not.
        boolean keepAlive = isKeepAlive(request);

        // Build the response object.
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
        response.setContent(ChannelBuffers.copiedBuffer(buf.toString(), CharsetUtil.UTF_8));
        response.setHeader(CONTENT_TYPE, "text/plain; charset=UTF-8");

        if (keepAlive) {
            // Add 'Content-Length' header only for a keep-alive connection.
            response.setHeader(CONTENT_LENGTH, response.getContent().readableBytes());
        }

        // Encode the cookie.
        String cookieString = request.getHeader(COOKIE);
        if (cookieString != null) {
            CookieDecoder cookieDecoder = new CookieDecoder();
            Set<Cookie> cookies = cookieDecoder.decode(cookieString);
            if(!cookies.isEmpty()) {
                // Reset the cookies if necessary.
                CookieEncoder cookieEncoder = new CookieEncoder(true);
                for (Cookie cookie : cookies) {
                    cookieEncoder.addCookie(cookie);
                }
                response.addHeader(SET_COOKIE, cookieEncoder.encode());
            }
        }

        // Write the response.
        ChannelFuture future = e.getChannel().write(response);

        // Close the non-keep-alive connection after the write operation is done.
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

	public void setJmsSender(JmsSender jmsSender) {
		this.jmsSender = jmsSender;
	}

	@Override
	public void handleException(ChannelHandlerContext ctx, ExceptionEvent e) {
		logger.logInfo("Ignoring exception for Restful message");
	}

}
