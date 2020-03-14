package com.reddoor.charging.server.action;

import java.net.Socket;

import com.reddoor.charging.common.Action;
import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.MessageType;
import com.reddoor.charging.common.observe.PublisherHolder;
import com.reddoor.charging.server.handler.impl.FullyChargedHandler;
import com.reddoor.charging.server.handler.impl.HeartBeatHandler;
import com.reddoor.charging.server.handler.impl.StartChargingHandler;
import com.reddoor.charging.server.handler.impl.TerminateChargingHandler;

public class ServerSocketAction implements Action{

	@Override
	public void doAction(Socket socket, byte[] data) {
		BaseMessage message = MessageHelper.transformFromByte(data);
		switch(message.getType()){
		case MessageType.START_CHARGING:
			(new StartChargingHandler()).handle(socket, message);
			break;
		case MessageType.FULLY_CHARGED:
			(new FullyChargedHandler()).handle(socket, message);
			break;
		case MessageType.TERMINATE_CHARGING:
			(new TerminateChargingHandler()).handle(socket, message);
			break;
		case MessageType.HEART_BEAT:
			(new HeartBeatHandler()).handle(socket, message);
			break;
		}
		
		// publish to observer, UI react accordingly
		PublisherHolder.getPublisher().publishCmd(socket, message);
	}
	
}
