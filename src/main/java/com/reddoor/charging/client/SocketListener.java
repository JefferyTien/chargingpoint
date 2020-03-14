package com.reddoor.charging.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.reddoor.charging.common.Action;

public class SocketListener implements Runnable{
	
	private Socket socket;
	private Action action;
	
	public SocketListener(Socket socket, Action action){
		this.socket = socket;
		this.action = action;
	}
	
	@Override
	public void run() {
		if(null != socket){
			while(!socket.isClosed()){
				try {
					InputStream is = socket.getInputStream();
					String line = null;
					StringBuffer sb = null;
					
					if(is.available() > 0){
//						BufferedReader bufr = new BufferedReader(new InputStreamReader(is));
//						line = bufr.readLine();
						
//						sb = new StringBuffer();
//						while((is.available() > 0) && (line = bufr.readLine()) != null){
//							sb.append(line);
//						}
						
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
	}

}
