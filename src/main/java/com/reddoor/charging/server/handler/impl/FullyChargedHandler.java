package com.reddoor.charging.server.handler.impl;

import java.net.Socket;

import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.transform.Responsive;
import com.reddoor.charging.server.handler.SocketHandler;
import com.reddoor.charging.util.Logger;

public class FullyChargedHandler implements SocketHandler{
	@Override
	public Object handle(Socket socket, BaseMessage message) {
		Logger.log("Client Fully Charged");
		
		if(message instanceof Responsive){
			BaseMessage resp = ((Responsive) message).getRespMessage();
			MessageHelper.sendCmd(socket, resp);
			return resp;
		}
		return null;
	}
}
