package com.reddoor.charging.server.action;

import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.EndChargingMessage;
import com.reddoor.charging.common.message.MessageType;
import com.reddoor.charging.common.message.PrepareChargingMessage;
import com.reddoor.charging.common.observe.Publisher;
import com.reddoor.charging.server.ServerFrame;

public class ServerFrameAction implements Observer{
	
	private ServerFrame serverFrame;
	
	public ServerFrameAction(Observable o){
		o.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		BaseMessage message = ((Publisher)o).getData();
		
		String commandContent = "";
		switch(message.getType()){
		case MessageType.START_CHARGING:
			commandContent = message.getFrom() + " start charging";
			break;
		case MessageType.FULLY_CHARGED:
			commandContent = message.getFrom() + " fully charged";
			break;
		case MessageType.TERMINATE_CHARGING:
			commandContent = message.getFrom() + " terminate charging";
			break;
		case MessageType.HEART_BEAT:
			commandContent = "~~~~heartbeat~~~ from " + message.getFrom();
			
			serverFrame.updateClientList(message.getFrom());
			break;
		}
		
		// update command line
		serverFrame.appendCommandLine(commandContent);
	}
	
	public void doPrepareCharging(Socket socket){
		if(null == socket){
			return;
		}
		PrepareChargingMessage msg = new PrepareChargingMessage();
		MessageHelper.sendMsg(socket, msg);
	}
	
	public void doEndCharging(Socket socket){
		if(null == socket){
			return;
		}
		EndChargingMessage msg = new EndChargingMessage();
		MessageHelper.sendMsg(socket, msg);
	}

	public ServerFrame getServerFrame() {
		return serverFrame;
	}

	public void setServerFrame(ServerFrame serverFrame) {
		this.serverFrame = serverFrame;
	}
}
