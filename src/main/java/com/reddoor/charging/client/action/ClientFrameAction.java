package com.reddoor.charging.client.action;

import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import com.reddoor.charging.client.ClientFrame;
import com.reddoor.charging.client.ClientHolder;
import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.FullyChargedMessage;
import com.reddoor.charging.common.message.MessageType;
import com.reddoor.charging.common.message.StartChargingMessage;
import com.reddoor.charging.common.message.TerminateChargingMessage;
import com.reddoor.charging.common.observe.Publisher;

public class ClientFrameAction implements Observer{
	
	private Socket socket;
	
	private ClientFrame clientFrame;
	
	public ClientFrameAction(Observable o){
		o.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		BaseMessage message = ((Publisher)o).getData();
		
		String commandContent = "";
		switch(message.getType()){
		case MessageType.PREPARE_CHARGING:
			commandContent = "[from server] prepare charging";
			break;
		case MessageType.END_CHARGING:
			commandContent = "[from server] end charging";
			break;
		}
		
		// update UI command line
		clientFrame.appendCommandLine(commandContent);
	}
	
	public void doStartCharging(){
		if(!checkSocket()){
			return;
		}
		StartChargingMessage msg = new StartChargingMessage();
		msg.setFrom(ClientHolder.getClient().getFrom());
		MessageHelper.sendMsg(socket, msg);
		System.out.println("handleStartCharging");
		
		clientFrame.appendCommandLine("开始充电");
	}
	
	public void doFullyCharged(){
		if(!checkSocket()){
			return;
		}
		FullyChargedMessage msg = new FullyChargedMessage();
		msg.setFrom(ClientHolder.getClient().getFrom());
		MessageHelper.sendMsg(socket, msg);
		System.out.println("handleFullyCharged");
		
		clientFrame.appendCommandLine("充满");
	}

	public void doTerminateCharging(){
		if(!checkSocket()){
			return;
		}
		TerminateChargingMessage msg = new TerminateChargingMessage();
		msg.setFrom(ClientHolder.getClient().getFrom());
		MessageHelper.sendMsg(socket, msg);
		System.out.println("handleTerminateCharging");
		
		clientFrame.appendCommandLine("终止充电");
	}
	
	private boolean checkSocket(){
		if(null == socket){
			socket = ClientHolder.getClient().getSocket();
			if(null == socket){
				return false;
			}
		}
		return true;
	}

	public ClientFrame getClientFrame() {
		return clientFrame;
	}

	public void setClientFrame(ClientFrame clientFrame) {
		this.clientFrame = clientFrame;
	}

}
