package com.reddoor.charging.server.handler.impl;

import java.net.Socket;

import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.server.handler.SocketHandler;

public class Resp4EndChargingHandler implements SocketHandler{

	@Override
	public Object handle(Socket socket, BaseMessage message) {
		return null;
	}

}
