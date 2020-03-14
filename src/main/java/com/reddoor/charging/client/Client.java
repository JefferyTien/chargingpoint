package com.reddoor.charging.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.reddoor.charging.client.action.ClientSocketAction;
import com.reddoor.charging.common.Constants;


public class Client extends Thread{

	private Socket socket;
	private final ExecutorService pool;
	
	private long deviceId;
	
	public Client() {
		this.pool = Executors.newFixedThreadPool(2);
	}
	
	@Override
	public void run(){
		try {
			this.socket = new Socket(Constants.SERVER_IP, Constants.SERVER_PORT);
			System.out.println("Connected to server");
			pool.execute(new SocketListener(socket, new ClientSocketAction()));
			
			// start heart beat
			startHeartBeat();
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startHeartBeat(){
		pool.execute(new HeartBeat(socket, deviceId));
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
}
