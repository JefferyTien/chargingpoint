package com.reddoor.charging.client.handler.impl;

import java.net.Socket;

import com.reddoor.charging.client.handler.Handler;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.util.Logger;

public class Resp4FullyChargedHandler implements Handler{
	
	@Override
	public Object handle(Socket socket, BaseMessage data) {
		Logger.log("Server Fully Charged resp");
		
		
		return null;
	}
}