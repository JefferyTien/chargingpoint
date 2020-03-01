package com.reddoor.charging.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.reddoor.charging.common.Constants;
import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.HeartBeatMessage;

public class HeartBeat extends Thread{
	
	private Socket socket;
	private String from;
	
	private String ip;
	
	public HeartBeat(Socket socket, String from, String ip){
		this.socket = socket;
		this.from = from;
		this.ip = ip;
	}
	
	@Override
    public void run() {
		while(null != socket && !socket.isClosed()){
			try {
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				HeartBeatMessage message = new HeartBeatMessage();
				message.setFrom(from);
				message.setIp(ip);
				pw.println(MessageHelper.transform(message));
				pw.flush();
				
				Thread.sleep(Constants.HEART_BEAT_PERIOD * 1000);
			}
			catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
