package com.reddoor.charging.client.action;

import java.net.Socket;

import com.reddoor.charging.client.handler.impl.EndChargingHandler;
import com.reddoor.charging.client.handler.impl.PrepareChargingHandler;
import com.reddoor.charging.client.handler.impl.Resp4HeartBeatHandler;
import com.reddoor.charging.client.handler.impl.SetHeartbeatIntervalHandler;
import com.reddoor.charging.common.Action;
import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.MessageType;
import com.reddoor.charging.common.observe.PublisherHolder;

public class ClientSocketAction implements Action{

	@Override
	public void doAction(Socket socket, byte[] data) {
		BaseMessage message = MessageHelper.transformFromByte(data);
		switch(message.getType()){
		case MessageType.PREPARE_CHARGING:
			(new PrepareChargingHandler()).handle(socket, message);
			break;
		case MessageType.END_CHARGING:
			(new EndChargingHandler()).handle(socket, message);
			break;
		case MessageType.SET_HEARTBEAT_INTERVAL:
			(new SetHeartbeatIntervalHandler()).handle(socket, message);
			break;
		case MessageType.HEART_BEAT_RESP:
			(new Resp4HeartBeatHandler()).handle(socket, message);
			break;
		}
		
		// publish to observer, UI react accordingly
		PublisherHolder.getPublisher().publishCmd(socket, message);
	}

}
