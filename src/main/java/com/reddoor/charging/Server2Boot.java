package com.reddoor.charging;

import com.reddoor.charging.common.observe.Publisher;
import com.reddoor.charging.common.observe.PublisherHolder;
import com.reddoor.charging.server.Server;
import com.reddoor.charging.server.ServerFrame;
import com.reddoor.charging.server.action.ServerFrameAction;

public class Server2Boot {
	public static void main(String[] args) {
		
		Publisher publisher = new Publisher();
		PublisherHolder.setPublisher(publisher);
		ServerFrameAction frameAction = new ServerFrameAction(publisher);
		
		ServerFrame serverFrame = new ServerFrame();
		
		// 双向关联
		serverFrame.setAction(frameAction);
		frameAction.setServerFrame(serverFrame);
		
		serverFrame.setLocation(700,100);
		serverFrame.setVisible(true);
		Server server = new Server();
		serverFrame.setServer(server);
	}
}
