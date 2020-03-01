package com.reddoor.charging.server.handler.impl;

import java.net.Socket;

import com.reddoor.charging.server.handler.SocketHandler;
import com.reddoor.charging.util.Logger;

public class FullyChargedHandler implements SocketHandler{
	@Override
	public Object handle(Socket socket, Object data) {
		Logger.log("Client Fully Charged");
		return null;
	}
}
