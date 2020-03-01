package com.reddoor.charging.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.reddoor.charging.common.Constants;
import com.reddoor.charging.server.action.ServerSocketAction;

public class Server extends Thread{
	private ExecutorService pool;
	
	private ServerSocket serverSocket;
	
	public Server(){
		try {
			serverSocket = new ServerSocket(Constants.SERVER_PORT);
			pool = Executors.newFixedThreadPool(Constants.SERVER_POOL_SIZE); 
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void run(){
		try {
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("A client connected:"+socket.getRemoteSocketAddress());
				pool.execute(new SocketDispatcher(socket, new ServerSocketAction()));
			}
		}
		catch (IOException e) {
			pool.shutdown();
		}
	}
}
