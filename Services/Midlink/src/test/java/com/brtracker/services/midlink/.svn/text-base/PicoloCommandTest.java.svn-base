package com.brtracker.services.midlink;

import junit.framework.TestCase;

import com.brtracker.services.midlink.processing.command.CommandTemplateEntity;
import com.brtracker.services.midlink.processing.command.PicoloStxCommand.GetGpsBuilder;
import com.brtracker.services.midlink.processing.command.PicoloStxCommand.GetJBusBuilder;
import com.brtracker.services.midlink.processing.command.PicoloStxCommand.InlineBufferCommandBuilder;
import com.brtracker.services.midlink.processing.command.PicoloStxCommand.ResetDeviceBuilder;
import com.brtracker.services.midlink.processing.command.PicoloStxCommand.SetIoStateBuilder;
import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.packet.PacketToString;

public class PicoloCommandTest extends TestCase {

	public static void main(String[] args) {
		checkGeneric();
	}
	public void testCheckSum() {
		checkReset();
//		checkGetGPSCommand();
//		checkSetIOCommand();
//		checkJbusCommand();
	}
	
	public static void checkGeneric() {
		
		InlineBufferCommandBuilder resetBuilder = new InlineBufferCommandBuilder();
		CommandTemplateEntity inlineBufferTemplate = new CommandTemplateEntity();
		String inlineBufferResetJsonPayload = "{\"inlineBuffer\": [\"02\", \"31\", \"00\", \"00\", \"00\", \"10\", \"E6\", \"31\", \"18\", \"00\", \"01\", \"00\", \"31\", \"31\", \"35\", \"00\", \"DB\", \"D2\", \"00\", \"32\", \"00\", \"DB\", \"D3\", \"00\", \"30\", \"00\", \"03\"]}";
		inlineBufferTemplate.setJsonPayload(inlineBufferResetJsonPayload);
		
		int[] resetBuffer = resetBuilder.buildInlineBuffer(Json.read(inlineBufferResetJsonPayload), "ANY_COMMAND");
		System.out.println("generic command " + PacketToString.getHexString(resetBuffer));
	}
	
	public static void checkReset() {
		
		int seqNum = 0;
		ResetDeviceBuilder resetBuilder = new ResetDeviceBuilder();
		CommandTemplateEntity templateGetGps = new CommandTemplateEntity();
		String getGpsJsonPayload = "{}";
		templateGetGps.setJsonPayload(getGpsJsonPayload);
		
		short[] getGpsBuffer = resetBuilder.buildPayload(templateGetGps, seqNum);
		System.out.println("get gps command " + PacketToString.getHexString(getGpsBuffer));
	}
	
	public static void checkGetGPSCommand() {
		int seqNum = 0;
		GetGpsBuilder getGpsBuilder = new GetGpsBuilder();
		CommandTemplateEntity templateGetGps = new CommandTemplateEntity();
		String getGpsJsonPayload = "{}";
		templateGetGps.setJsonPayload(getGpsJsonPayload);
		
		short[] getGpsBuffer = getGpsBuilder.buildPayload(templateGetGps, seqNum);
		System.out.println("get gps command " + PacketToString.getHexString(getGpsBuffer));
	}

	public static void checkSetIOCommand() {
		SetIoStateBuilder builder = new SetIoStateBuilder();
		CommandTemplateEntity template = new CommandTemplateEntity();
		String jsonPayload = "{\"output_state_1\":\"1\", \"output_state_2\":\"0\"}";
		template.setJsonPayload(jsonPayload);
		int seqNum = 123452344;
		short[] buildPayload = builder.buildPayload(template, seqNum);
		System.out.println("set io command " + PacketToString.getHexString(buildPayload));
	}
	
	public static void checkJbusCommand() {
		GetJBusBuilder builder = new GetJBusBuilder();
		CommandTemplateEntity template = new CommandTemplateEntity();
		String jsonPayload = "{}";
		template.setJsonPayload(jsonPayload);
		short[] buffer = builder.buildPayload(template, 123);
		System.out.println("get jbus command " + PacketToString.getHexString(buffer));
	}
	
}
