package com.reddoor.charging.client.action;

import java.net.Socket;

import com.reddoor.charging.client.handler.impl.EndChargingHandler;
import com.reddoor.charging.client.handler.impl.PrepareChargingHandler;
import com.reddoor.charging.common.Action;
import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.MessageType;
import com.reddoor.charging.common.observe.PublisherHolder;

public class ClientSocketAction implements Action{

	@Override
	public void doAction(Socket socket, Object data) {
		BaseMessage message = MessageHelper.parseObject(data.toString());
		switch(message.getType()){
		case MessageType.PREPARE_CHARGING:
			(new PrepareChargingHandler()).handle(data);
			break;
		case MessageType.END_CHARGING:
			(new EndChargingHandler()).handle(data);
			break;
		}
		
		// publish to observer, UI react accordingly
		PublisherHolder.getPublisher().publishMsg(socket, data);
	}

}
