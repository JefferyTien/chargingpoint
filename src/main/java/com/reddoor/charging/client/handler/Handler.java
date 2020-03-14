package com.reddoor.charging.client.handler;

import java.net.Socket;

import com.reddoor.charging.common.message.BaseMessage;

public interface Handler {
	public Object handle(Socket socket, BaseMessage message);
}
