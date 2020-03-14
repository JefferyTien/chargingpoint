package com.reddoor.charging.client.handler.impl;

import java.net.Socket;

import com.reddoor.charging.client.handler.Handler;
import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.transform.Responsive;

public class SetHeartbeatIntervalHandler implements Handler {

	@Override
	public Object handle(Socket socket, BaseMessage message) {
		
		
		if(message instanceof Responsive){
			BaseMessage resp = ((Responsive) message).getRespMessage();
			MessageHelper.sendCmd(socket, resp);
			return resp;
		}
		
		return null;
	}

}
