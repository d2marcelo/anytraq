package com.brtracker.services.midlink.processing;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.brtracker.services.midlink.processing.application.MessageAuditLogger;
import com.brtracker.shared.utils.ioutils.FileCopyUtil;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.PacketReaderContext;
import com.brtracker.shared.utils.packet.PacketReaderController;
import com.brtracker.shared.utils.packet.PacketReaderException;
import com.brtracker.shared.utils.packet.PacketToString;
import com.brtracker.shared.utils.packet.TrackingMessage;

/**
 * Draft initial implementation, current known limitations:
 * - Unbounded number of threads could be created by the ExecutorService (created via newCachedThreadPool). 
 * 		A better option might be ThreadPoolExecutor using the right parameters once we get more familiar with 
 * 		the load of the system. 
 * - MessageTypeHandler does not return anything now, we might change this to allow for retries, and other use cases.
 * -  
 *
 */
public class PacketDispatcher {

	private MyLogger logger = new MyLogger(PacketDispatcher.class);
	
	private Map<String, MessageTypeHandler> messageTypeHandlers;
	private PacketReaderController readerController;
	private ExecutorService executorService = Executors.newCachedThreadPool();
	private MessageAuditLogger messageAuditLogger;
	
	public void dispatchPacket(short[] packet) {
		
		String date = getDateString();
		
		try {

			TrackingMessage trackingMessage = readerController.readPacket(packet);
			
			String messageType = trackingMessage.getMessageType();
			
			messageAuditLogger.auditMessage(trackingMessage, packet);
			
			MessageTypeHandler messageTypeHandler = messageTypeHandlers.get(messageType);
			
			if (messageTypeHandler != null) {
				logger.logInfo("PacketDispatcher message type handler found for " + messageType);
				FileCopyUtil.appendHexArrayToFile(messageType, packet, "message-examples-" + date + ".txt");
				executorService.execute(
						new MessageHandlerTask(messageTypeHandler, trackingMessage));
				logger.logInfo("Just waiting ...." + messageType);
			} else {
				FileCopyUtil.appendHexArrayToFile(messageType, packet, "no-handler-examples-" + date + ".txt");
				logger.logInfo("Message handle not found for message type " + messageType);
			}
			
		} catch (PacketReaderException e){
			try {
				FileCopyUtil.appendHexArrayToFile("failed Message", packet, "failed-examples-" + date + ".txt");
			} catch (IOException e1) {
			}
			PacketReaderContext context = e.getContext();
			if (context != null) {
				logger.logError("Unexpected PacketReaderException processing packet " + e.getContext().toString(), e);
			} else {
				logger.logError("Unexpected PacketReaderException processing packet " + PacketToString.getHexString(packet), e);
			}
			
		} catch (Exception e) {
			logger.logError("Unexpected exception processing packet " + PacketToString.getHexString(packet), e);
		}
		
	}

	public String getDateString() {
		Calendar cal = GregorianCalendar.getInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(cal.get(Calendar.MONTH));
		sb.append(cal.get(Calendar.DAY_OF_MONTH));
		sb.append(cal.get(Calendar.YEAR));
		return sb.toString();
	}
	public void setMessageTypeHandlers(
			Map<String, MessageTypeHandler> messageTypeHandlers) {
		this.messageTypeHandlers = messageTypeHandlers;
	}

	public void setReaderController(PacketReaderController readerController) {
		this.readerController = readerController;
	}

	public void setMessageAuditLogger(MessageAuditLogger messageAuditLogger) {
		this.messageAuditLogger = messageAuditLogger;
	}

}
