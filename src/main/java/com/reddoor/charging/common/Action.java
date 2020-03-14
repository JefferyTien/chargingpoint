package com.reddoor.charging.common;

import java.net.Socket;

public interface Action {
	public void doAction(Socket socket, byte[] data);
	
	// 可用于字符流场景, 如聊天等
//	public void doAction(Socket socket, Object data);
}
