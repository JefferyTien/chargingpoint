package com.reddoor.charging.common;

import java.net.Socket;

public interface Action {
	public void doAction(Socket socket, Object data);
}
