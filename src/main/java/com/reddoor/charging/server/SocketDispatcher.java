package com.reddoor.charging.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.reddoor.charging.common.Action;

public class SocketDispatcher implements Runnable{

	private Socket socket;
	private Action action;
	
	public SocketDispatcher(Socket socket, Action action){
		this.socket = socket;
		this.action = action;
	}
	
	@Override
	public void run() {
		try {
			while(null != socket && !socket.isClosed()){
				InputStream is = socket.getInputStream();
				String line = null;
				StringBuffer sb = null;
				
//				BufferedReader bufr = new BufferedReader(new InputStreamReader(is));
//				line = bufr.readLine();
				
//				sb = new StringBuffer();
//				while((line = bufr.readLine()) != null){
//					sb.append(line);
//				}
				
				// 读取字节流
				byte[] data = new byte[100];
				int len = is.read(data);
				
				action.doAction(socket, data);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
