package com.brtracker.services.netty.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.MessageEvent;

import com.brtracker.services.netty.DeviceConnection;
import com.brtracker.services.netty.JMSConnector;
import com.brtracker.shared.payload.tracking.DeviceMessageAttribute;
import com.brtracker.shared.utils.activemq.JmsUtilsException;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.wsutils.ServiceResponse;


// TODO: Auto-generated Javadoc
/**
 * The Class VehicleService.
 */
@Path("rest")
public class NettyService  {

	
		/** The logger. */
		private static MyLogger logger = new MyLogger(NettyService.class);
		/**
		 * Adds the.
		 *
		 * @param payload the payload
		 * @return the string
		 */
		@GET
		@Path("devices/{affiliateId}")
		@Produces(MediaType.APPLICATION_JSON)
		public String listDevices (@PathParam("affiliateId")Long affiliateId)  {
		logger.logInfo("listDevices");
		return DeviceConnection.getDeviceInfo();
		}

		// hack until we have full support for mobile devices //midlink and async
		@POST
		@Path("device/location")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String deviceLocation (String payload)  {
			try {
				DeviceMessageAttribute msg = (DeviceMessageAttribute) JSONMapper.toObject(payload, DeviceMessageAttribute.class);
				String deviceAddress = msg.getUnitAddress();
				if (deviceAddress == null)
					return ServiceResponse.getServiceResponse(false,  "Device Address is required");
				JMSConnector.getJmsSender().sendTextMessage(payload, "ASYNC");
				return ServiceResponse.getServiceResponse(true, "Message Received Successfuly");
			} catch (JmsUtilsException e) {
				return ServiceResponse.getSafeServiceResponse(false,  "Unable to Process Message. "+e.getMessage());
			} catch (JSONMapperException e) {
				return ServiceResponse.getSafeServiceResponse(false,  "Invalid Message Payload. "+e.getMessage());
			}
		}
		
		
		@POST
		@Path("send")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@SuppressWarnings({"rawtypes", "unchecked"})
		public String send (String message)  {
			logger.logInfo("sending message to unit");
			try {

				Map map = (Map) JSONMapper.toObject(message, Map.class);
				MessageEvent messageEvent = DeviceConnection.getMessageEvent(map.get("deviceId").toString());
				if (messageEvent == null){
					return ServiceResponse.getServiceResponse(false, "device not found.");
				}
				Channel out = messageEvent.getChannel();
				String payloadStr = (String) map.get("payload");
				List<String> payload = (List<String>) JSONMapper.toObject(payloadStr, List.class);
				byte[] b = new byte[1500];
				// convert int to bytes
				for(int i = 0; i < payload.size() ; i++) {
					logger.logInfo("processing hex : "+payload.get(i));
					short sh =  Short.valueOf(payload.get(i), 16);
					b[i]= (byte) sh;
				}
				// send the message..
				ChannelBuffer ch = ChannelBuffers.wrappedBuffer(b);
				out.write(ch);
				logger.logInfo("message sent successfuly");
			
				return ServiceResponse.getServiceResponse(true, "message sent successfully");
				
			} catch (Exception e) {
				return ServiceResponse.getSafeServiceResponse(false, "Unexpected exception" + e.getMessage());
			}
		}
		
		
		@GET
		@Path("pokeDevice")
		@Produces(MediaType.APPLICATION_JSON)
		public String pokeDevice (@QueryParam("deviceId") String deviceId)  {
		try {
		logger.logInfo("request device location");
		List<String> message = new ArrayList<String>();
		message.add("2");
		message.add("31");
		message.add("0");
		message.add("0");
		message.add("0");
		message.add("6");
		message.add("fe");
		message.add("0");
		message.add("c9");
		message.add("0");
		message.add("1");
		message.add("0");
		message.add("34");
		message.add("0");
		message.add("3");
		Map<String,String> map = new HashMap<String,String>();
		map.put("payload", JSONMapper.toString(message));
		map.put("deviceId", deviceId);
		return send(JSONMapper.toString(map));
		} catch (JSONMapperException e) {
			return "error "+e.getMessage();
		}
		}

}


