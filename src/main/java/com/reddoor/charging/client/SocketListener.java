package com.reddoor.charging.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
						BufferedReader bufr = new BufferedReader(new InputStreamReader(is));
//						sb = new StringBuffer();
//						while((is.available() > 0) && (line = bufr.readLine()) != null){
//							sb.append(line);
//						}
						line = bufr.readLine();
						
						action.doAction(socket, line);
					}
					
					
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
