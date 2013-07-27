package com.brtracker.services.midlink.processing.command;

import static com.brtracker.shared.utils.SystemConfiguration.MIDLINK;
import static com.brtracker.shared.utils.SystemConfiguration.MIDLINK_MAX_RETRIES;
import static com.brtracker.shared.utils.SystemConfiguration.NETTY;
import static com.brtracker.shared.utils.packet.PacketDefinitionConstants.APP_CLASS;
import static com.brtracker.shared.utils.packet.PacketDefinitionConstants.ETX_END_WLI_PCK;
import static com.brtracker.shared.utils.packet.PacketDefinitionConstants.MAX_WLI_PCK_SIZE;
import static com.brtracker.shared.utils.packet.PacketDefinitionConstants.STX_START_WLI_PCK;
import static com.brtracker.shared.utils.packet.PicoloPacketPreprocessor.escapeOutboundDataInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.brtracker.services.midlink.processing.WsClient;
import com.brtracker.shared.payload.midlink.CommandCode;
import com.brtracker.shared.payload.midlink.CommandRequest;
import com.brtracker.shared.payload.midlink.CommandState;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.SystemConfiguration;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.PacketToString;
import com.brtracker.shared.utils.packet.TrackingMessage;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public class PicoloStxCommand implements DeviceCommandHandler {

	private static final String INLINE_BUF_PROP = "inlineBuffer";
	private WsClient wsClient;
	private SystemConfiguration systemConfiguration;
	private CommandEventDao commandEventDao;
	
	private HashCodeBuilder messageIdBuilder = new HashCodeBuilder(17,37);
	private final int MSG_MAX = Short.MAX_VALUE*2; 
	
	private static MyLogger logger = new MyLogger(PicoloStxCommand.class);
	
	private Map<String, PayloadBuilder> commandBuilderLookup = 
			new HashMap<String, PicoloStxCommand.PayloadBuilder>();
	
	private static InlineBufferCommandBuilder inlineBufferCommandBuilder = new InlineBufferCommandBuilder();
	
	public PicoloStxCommand() {
		commandBuilderLookup.put(CommandCode.LOCK.name(), new SetIoStateBuilder());
		commandBuilderLookup.put(CommandCode.UNLOCK.name(), new SetIoStateBuilder());
		commandBuilderLookup.put(CommandCode.GET_GPS.name(), new GetGpsBuilder());
		commandBuilderLookup.put(CommandCode.RESET.name(), new ResetDeviceBuilder());
	}
	
	@Override
	public void sendCommand(CommandTemplateEntity template, CommandRequest request) {
		
		String command = template.getCommand();
		try {
			
			short[] rawPayload = new short[0];
			
			CommandEventEntity event = createEvent(template, request);
			boolean sendCommand = true;
			
			PayloadBuilder builder = commandBuilderLookup.get(command);
			if (builder != null) {
				
				rawPayload = builder.buildPayload(template, event.getMessageId());
				
			} else {
				
				Json json = getJsonFromTemplate(template);
				
				// inline buffer commands are processed by a special builder
				if (json.has(INLINE_BUF_PROP)) {
					
					rawPayload = convertToShortArray(
							inlineBufferCommandBuilder.buildInlineBuffer(json, command));
					
				} else {
					logger.logError("Unable to find command builder for command " + command);
					sendCommand = false;
				}
			}
			
			if (sendCommand) {
				event.setRawPayload(PacketToString.getJsonArrayHexString(rawPayload));
				
				if (CommandState.PENDING_DELIVERY.name().equals(event.getState())) {
					doSendCommand(event);
				} else {
					logger.logInfo("Command request is not pending for delivery... not sending it now [state=" + event.getState() + "]");
				}
				commandEventDao.save(event);
			}
			
		} catch (Exception e) {
			logger.logError("Unexpected exception while sending command " + command, e);
		}
	}

	private Json getJsonFromTemplate(CommandTemplateEntity template) {
		try {
			return Json.read(template.getJsonPayload());
		} catch (Exception e) {
			return Json.read("");
		}
	}
	private void doSendCommand(CommandEventEntity event) {
		
		try {
			String nettyBaseUrl = systemConfiguration.getEndpointURL(NETTY);
			String nettyEndpoint = nettyBaseUrl + "/netty/rest/send";
			
			Map<String, String> request = new HashMap<String, String>();
			request.put("payload", event.getRawPayload());
			request.put("deviceId", event.getDeviceId());
			
			wsClient.postRequest(request, nettyEndpoint);
			
			event.setState(CommanState.DELIVERED.state());
			
		} catch (Exception e) {
			logger.logError("Unexpected exception while sending device command", e);
			handleCommandSendFailure(event, e.getMessage());
		}

	}
	
	private void handleCommandSendFailure(CommandEventEntity event, String message) {
		
		String maxRetriesConfig = systemConfiguration.getConfigElement(MIDLINK, MIDLINK_MAX_RETRIES, "3");
		
		int maxRetries = Integer.valueOf(maxRetriesConfig);
		
		String state = event.getState();
		int retries = event.incrementRetriesDelivery();
		
		if (CommanState.PENDING_DELIVERY.state().equals(state)) {
			// max retries exceeded, failing the event
			if (retries > maxRetries) {
				event.setState(CommanState.FAILED.state());
			// rescheduling event in fifteen minutes
			} else {
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(event.getScheduledFor());
				cal.add(Calendar.MINUTE, 15);
				event.setScheduledFor(cal.getTime());
			}
		}
		
	}
	
	private CommandEventEntity createEvent(
			CommandTemplateEntity template, CommandRequest request) 
		throws HibernateHelperException {
		// get utc time
		Date now = DateUtil.getUTCDateAndTime(); //GregorianCalendar.getInstance().getTime();
		
		CommandEventEntity event = new CommandEventEntity();
		event.setCommand(template.getCommand());
		event.setCreatedOn(now);
		event.setDeviceModel(template.getDeviceModel());
		event.setFailureReason("NONE");
		event.setJsonPayload(template.getJsonPayload());
		event.setLastUpdateOn(DateUtil.getUTCDateAndTime());
		event.setNeedsVerification(template.getNeedsVerification());
		event.setRetriesDelivery(0);
		event.setRetriesVerify(0);
		event.setScheduledFor(now);
		
		String requestState = request.getState();
		if (requestState != null) {
			event.setState(requestState);
		} else {
			event.setState(CommanState.PENDING_DELIVERY.state());
		}
		
		String deviceId = request.getDeviceAddress();
		event.setDeviceId(deviceId);
		event.setRisk(template.getRisk());
		
		Long accountUserId = request.getAccountUserId();
		event.setAccountUserId(accountUserId );

		int messageId = generateMessageId(deviceId, accountUserId, now);
		event.setMessageId(messageId);
		
		event.setAffiliateUserId(request.getAffiliateUserId());
		event.setSecurityCode(request.getSecurityCode());
		
		return event;
	}
	
	private int generateMessageId(String deviceId, Long accountId, Date now) {
		
		int mid = Math.abs(messageIdBuilder
			.append(deviceId)
			.append(accountId)
			.append(now)
			.toHashCode());
		
		return mid%MSG_MAX;
	}
	
	@Override
	public void resendCommand(CommandEventEntity event) {
		
		try {

			logger.logInfo("About to resend command " + event.getId() + " for unit " + event.getDeviceId());
			doSendCommand(event);
			commandEventDao.save(event);
			logger.logInfo("Command successfully resent " + event.getId() + " for unit " + event.getDeviceId());

		} catch (HibernateHelperException e) {
			logger.logError("Unexpected exception while resending device command", e);
			handleCommandSendFailure(event, e.getMessage());
		}
		
	}

	@Override
	public boolean verifyCommand(CommandEventEntity event, TrackingMessage tm) {
		boolean verified = false;

		if ("PicoloIOServiceMessage".equals(tm.getMessageType())) {
			
			Integer actualOutput1 = tm.getProperty(Integer.class, "appMessage.ioService.output1");
			Integer actualOutput2 = tm.getProperty(Integer.class, "appMessage.ioService.output2");
			
			Json payload = Json.read(event.getJsonPayload());
			
			int outputId = payload.at("outputId").asInteger();
			int outputValue = payload.at("outputValue").asInteger();
			
			switch (outputId) {
				case 1:
					verified = outputValue == actualOutput1;
					break;
				case 2:
					verified = outputValue == actualOutput2;
					break;
				default:
					verified = false;
			}
		}
		return verified;
	}

	public static int getWliCheckSum(int buffer[], int messageSize) {
		return getWliCheckSum(buffer, 0, messageSize-1);
	}
	
	/**
	 * Taken from the vendor's documentation
	 */
	public static int getWliCheckSum(int buffer[], int startIdx, int endIdx) {
		
		int loopCounter; 
		long total = 0; 
		int addVal; 
		int msgSize = endIdx - startIdx + 1;
		
		// Add each couple of bytes as short number (network order).
		for(loopCounter=startIdx; loopCounter<endIdx; loopCounter+=2) { 
			total += (buffer[loopCounter] << 8) + buffer[loopCounter+1]; 
		}
		
		if(msgSize%2 != 0) { 
			total += buffer[endIdx] << 8;
		}
		
		// Make sure the result is not bigger than short (2 bytes)
		while (total > 0xFFFF) { 
			addVal = (int)((total >> 16) & 0xffff); 
			total = (long)(total & 0xFFFF) + addVal;
		}
		
		return (int)total;
	}
	
	public static interface PayloadBuilder {
		public short[] buildPayload(CommandTemplateEntity template, int seqNum);
	}
	
	public static abstract class BaseBuilderTemplate implements PayloadBuilder {
		
		@Override
		public short[] buildPayload(CommandTemplateEntity template, int seqNum) {
			
			int[] buffer = new int[MAX_WLI_PCK_SIZE];
			
			// start of the message
			buffer[0] = STX_START_WLI_PCK;
			
			// mark of the application class
			buffer[1] = APP_CLASS;
			
			// sequence number
			buffer[2] = (short) ((seqNum>>8) & 0xFF); 
			buffer[3] = (short) (seqNum & 0xFF);
			
			int[] commandBuf = buildCommandPayload(template, seqNum);
			int commandBufSize = commandBuf.length;
			// message length
			buffer[4] = (short) ((commandBufSize>>8) & 0xFF); 
			buffer[5] = (short) (commandBufSize & 0xFF);
			
			// check sum
			int wliCheckSum = getWliCheckSum(commandBuf, commandBufSize);
			buffer[6] = (short) ((wliCheckSum>>8) & 0xFF); 
			buffer[7] = (short) (wliCheckSum & 0xFF);
			
			// actual command 
			int[] escapedCommandBuf = escapeOutboundDataInt(commandBuf);
			for (int i=0; i<escapedCommandBuf.length; i++) {
				buffer[8+i] = escapedCommandBuf[i];
			}
			buffer[8+escapedCommandBuf.length] = ETX_END_WLI_PCK;
			
			return convertToShortArray(
					Arrays.copyOf(buffer, 8+escapedCommandBuf.length+1));
		}
		
		protected abstract int[] buildCommandPayload(
				CommandTemplateEntity template, int seqNum);
	}
	
	public static class ChangeDeviceIpBuilder extends BaseBuilderTemplate {

		private static final short APP_GPS_MSG = 0xC9;
		private static final short APP_GPS_MSG_LEN = 6;
		
		@Override
		protected int[] buildCommandPayload(CommandTemplateEntity template,
				int seqNum) {
			
			logger.logInfo("Building get gps packet for " + template.getCommand());
			
			int[] commandBuf = new int[APP_GPS_MSG_LEN];
			
			commandBuf[0] = APP_GPS_MSG;
			commandBuf[1] = 0x00; 
			commandBuf[2] = 0x01;
			commandBuf[3] = 0x00;
			commandBuf[4] = '4';
			commandBuf[5] = 0x00;
			return commandBuf;
		}
		
	}
	public static class SetIoStateBuilder extends BaseBuilderTemplate {

		@Override
		protected int[] buildCommandPayload(CommandTemplateEntity template, int seqNum) {

			logger.logInfo("Building set io state packet for " + template.getCommand());
			
			Json payload = Json.read(template.getJsonPayload());
			
			String seqNumStr = String.valueOf(seqNum);
			int seqNumLength = seqNumStr.length();
			
			//  
			int[] commandBuf = new int[2+ 2+ seqNumLength + 1 + 2 + 2 + 2 + 2];
			int k = 0;
			
			// set IO message type
			commandBuf[k++] = 0x13;
			commandBuf[k++] = 0x00;
			
			// field identifier #1
			commandBuf[k++] = 0x01;
			commandBuf[k++] = 0x00;
			
			// field value for field #1
			for (int i=0; i<seqNumLength; i++) {
				commandBuf[k++] = (short) seqNumStr.charAt(i);
			}
			commandBuf[k++] = 0;
			
			// field identifier #2
			commandBuf[k++] = 0x02;
			commandBuf[k++] = 0x00;
			
			commandBuf[k++] = (short) payload.at("outputId").asChar();
			commandBuf[k++] = 0x00;
			
			// field identifier #3
			commandBuf[k++] = 0x03;
			commandBuf[k++] = 0x00;

			commandBuf[k++] = (short) payload.at("outputValue").asChar();
			commandBuf[k++] = 0x00;
			
			return commandBuf;
		}
	}
	
	public static class GetGpsBuilder extends BaseBuilderTemplate {

		private static final short APP_GPS_MSG = 0xC9;
		private static final short APP_GPS_MSG_LEN = 6;
		
		@Override
		protected int[] buildCommandPayload(CommandTemplateEntity template,
				int seqNum) {
			
			logger.logInfo("Building get gps packet for " + template.getCommand());
			
			int[] commandBuf = new int[APP_GPS_MSG_LEN];
			
			commandBuf[0] = APP_GPS_MSG;
			commandBuf[1] = 0x00; 
			commandBuf[2] = 0x01;
			commandBuf[3] = 0x00;
			commandBuf[4] = '4';
			commandBuf[5] = 0x00;
			return commandBuf;
		}
	}

	public static class InlineBufferCommandBuilder extends BaseBuilderTemplate {

		@Override
		protected int[] buildCommandPayload(CommandTemplateEntity template,
				int seqNum) {
			Json json = Json.read(template.getJsonPayload());
			return buildInlineBuffer(json, template.getCommand()); 
		}
		
		public int[] buildInlineBuffer(Json json, String command) {
			try {
				
				logger.logInfo("Building inline payload for " + command);
				
				List<Integer> commandTokenList = new ArrayList<Integer>();
				List<Object> asList = json.at(INLINE_BUF_PROP).asList();
				if (asList != null) {
					for (Object o : asList) {
						String bufferItem = (String) o;
						commandTokenList.add(Integer.parseInt(bufferItem, 16));
					}
				}
				int[] commandBuf = new int[commandTokenList.size()];
				int i = 0;
				for (Integer v : commandTokenList) {
					commandBuf[i++] = v;
				}
				return commandBuf;
				
			} catch (Exception e) {
				logger.logError("Could not build inline buffer", e);
				return new int[0];
			}
		}
	}

	public static class ResetDeviceBuilder extends BaseBuilderTemplate {

		private static final short APP_GPS_MSG = 0x18;
		private static final short APP_GPS_MSG_LEN = 16;
		
		@Override
		protected int[] buildCommandPayload(CommandTemplateEntity template,
				int seqNum) {
			
			logger.logInfo("Building reset packet for " + template.getCommand());
			
			int[] commandBuf = new int[APP_GPS_MSG_LEN];
			int i = 0;
			// example: 18 00 01 00 31 31 35 00 DB D2 00 32 00 DB D3 00  30 00 
			commandBuf[i++] = APP_GPS_MSG;
			commandBuf[i++] = 0x00;
			commandBuf[i++] = 0x01;
			commandBuf[i++] = 0x00;
			commandBuf[i++] = 0x31;
			commandBuf[i++] = 0x31;
			commandBuf[i++] = 0x35;
			commandBuf[i++] = 0x00;
			commandBuf[i++] = 0x02;
			commandBuf[i++] = 0x00;
			commandBuf[i++] = 0x32;
			commandBuf[i++] = 0x00;
			commandBuf[i++] = 0x03;
			commandBuf[i++] = 0x00;
			commandBuf[i++] = 0x30;
			commandBuf[i++] = 0x00;
			return commandBuf;
		}
	}

	public static class GetJBusBuilder extends BaseBuilderTemplate {

		private static final short APP_JUBS_MSG = 0xE1;
		private static final short APP_JUBS_MSG_LEN = 9;
		
		@Override
		protected int[] buildCommandPayload(CommandTemplateEntity template, int seqNum) {
			
			logger.logInfo("Building get JBUS packet for " + template.getCommand());
			
			String seqNumStr = String.valueOf(seqNum);
			int seqNumLenght = seqNumStr.length();
			int[] commandBuf = new int[APP_JUBS_MSG_LEN + seqNumLenght];
			
			commandBuf[0] = APP_JUBS_MSG;
			commandBuf[1] = 0x00; 
			commandBuf[2] = 0x01;
			commandBuf[3] = 0x00;
			
			for (int i=0; i<seqNumLenght; i++) {
				commandBuf[4+i] = (int) seqNumStr.charAt(i);
			}
			commandBuf[4+seqNumLenght] =0x00;
			commandBuf[4+seqNumLenght+1] =0x02;
			commandBuf[4+seqNumLenght+2] =0x00;
			commandBuf[4+seqNumLenght+3] =0x00;
			commandBuf[4+seqNumLenght+4] =0x00;
			return commandBuf;
		}
		
	}

	// Kind of a hack just to reuse the integer-based Wireless link code and then convert it 
	// to our short-based classes
	private static short[] convertToShortArray(int[] a) {
		short[] b = new short[a.length];
		for (int i=0; i<a.length; i++) {
			b[i]= (short) a[i];
		}
		return b;
	}
	
	public WsClient getWsClient() {
		return wsClient;
	}
	public void setWsClient(WsClient wsClient) {
		this.wsClient = wsClient;
	}
	public void setSystemConfiguration(SystemConfiguration systemConfiguration) {
		this.systemConfiguration = systemConfiguration;
	}
	public void setCommandEventDao(CommandEventDao commandEventDao) {
		this.commandEventDao = commandEventDao;
	}
}
