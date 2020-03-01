package com.reddoor.charging;

import com.reddoor.charging.client.Client;
import com.reddoor.charging.client.ClientFrame;
import com.reddoor.charging.client.ClientHolder;
import com.reddoor.charging.client.action.ClientFrameAction;
import com.reddoor.charging.common.observe.Publisher;
import com.reddoor.charging.common.observe.PublisherHolder;
import com.reddoor.charging.util.IDUtil;

public class Client2Boot {
	public static void main(String[] args) {
		// create a random name for client
		String clientName = "Client-"+IDUtil.jvmPid();
				
		Client client = new Client();
		ClientHolder.setClient(client);
		client.setFrom(clientName);
		
		Publisher publisher = new Publisher();
		PublisherHolder.setPublisher(publisher);
		ClientFrameAction frameAction = new ClientFrameAction(publisher);
		
		ClientFrame clientFrame = new ClientFrame();
		
		// 双向关联
		clientFrame.setAction(frameAction);
		frameAction.setClientFrame(clientFrame);
		
		clientFrame.setTitle("客户端                "+clientName);
		clientFrame.setLocation(100,150);
		
		clientFrame.setVisible(true);
		
	}
}
