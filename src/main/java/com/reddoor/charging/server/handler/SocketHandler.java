package com.reddoor.charging.server.handler;

import java.net.Socket;

import com.reddoor.charging.common.message.BaseMessage;

public interface SocketHandler {
	public Object handle(Socket socket, BaseMessage message);
}
