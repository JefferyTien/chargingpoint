package com.reddoor.charging.server.handler;

import java.net.Socket;

public interface SocketHandler {
	public Object handle(Socket socket, Object data);
}
