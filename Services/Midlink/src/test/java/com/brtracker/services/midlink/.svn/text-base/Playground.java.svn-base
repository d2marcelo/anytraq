package com.brtracker.services.midlink;

import com.brtracker.services.midlink.processing.command.CommandEventEntity;
import com.brtracker.services.midlink.processing.command.CommandTemplateEntity;
import com.brtracker.services.midlink.processing.command.PicoloStxCommand;
import com.brtracker.services.midlink.processing.command.PicoloStxCommand.SetIoStateBuilder;
import com.brtracker.shared.payload.controller.lookup.DeviceModel;
import com.brtracker.shared.payload.midlink.CommandRequest;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.packet.DefaultTrackingMessage;
import com.brtracker.shared.utils.packet.PacketToString;
import com.brtracker.shared.utils.packet.TrackingMessage;

public class Playground {

	
	
	public static void main(String[] args) {
		
		try {
			CommandRequest request = new CommandRequest();
			request.setCommandTemplateId(1L);
			request.setAccountUserId(11L);
			request.setAffiliateUserId(22L);
			request.setDeviceAddress("045110233013");
			request.setDeviceModel(DeviceModel.WILUS_PICCOLO_ATX);
			
			String string = JSONMapper.toString(request);
//			System.out.println(string);
			
			SetIoStateBuilder b = new SetIoStateBuilder();
			CommandTemplateEntity template = new CommandTemplateEntity();
			template.setJsonPayload("{\"outputId\":\"1\", \"outputValue\":\"1\"}");
//			template.setJsonPayload("{\"outputId\":\"1\", \"outputValue\":\"0\"}");

			short[] payload = b.buildPayload(template, 116);
			System.out.println(PacketToString.getJsonArrayHexString(payload));
			
//			GetGpsBuilder b2 = new GetGpsBuilder();
//			short[] gpsMessage = b2.buildPayload(template, 200);
//			System.out.println(PacketToString.getJsonArrayHexString(gpsMessage));
			
		} catch (JSONMapperException e) {
			e.printStackTrace();
		}
	}
	
	public static void testVerify() {
		PicoloStxCommand command = new PicoloStxCommand();
		
		CommandEventEntity event = new CommandEventEntity();
		event.setJsonPayload("{\"outputId\":\"1\", \"outputValue\":\"1\"}");

		TrackingMessage tm = new DefaultTrackingMessage();
		String json = "";
		tm.deserialize(json);
		
		command.verifyCommand(event, tm);
	}
}
