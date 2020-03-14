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
			commandContent = "[from server] PREPARE_CHARGING";
			break;
		case MessageType.END_CHARGING:
			commandContent = "[from server] END_CHARGING";
			break;
		case MessageType.SET_HEARTBEAT_INTERVAL:
			commandContent = "[from server] SET_HEARTBEAT_INTERVAL";
			break;
		case MessageType.HEART_BEAT_RESP:
			commandContent = "[from server] HEART_BEAT_RESP";
			break;
		case MessageType.FULLY_CHARGED_RESP:
			commandContent = "[from server] FULLY_CHARGED_RESP";
			break;
		case MessageType.START_CHARGING_RESP:
			commandContent = "[from server] START_CHARGING_RESP";
			break;
		case MessageType.TERMINATE_CHARGING_RESP:
			commandContent = "[from server] TERMINATE_CHARGING_RESP";
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
		msg.setDeviceId(ClientHolder.getClient().getDeviceId());
		msg.setPortId(Integer.parseInt("00", 16));
		MessageHelper.sendCmd(socket, msg);
		System.out.println("handleStartCharging");
		
		clientFrame.appendCommandLine("开始充电");
	}
	
	public void doFullyCharged(){
		if(!checkSocket()){
			return;
		}
		FullyChargedMessage msg = new FullyChargedMessage();
		msg.setDeviceId(ClientHolder.getClient().getDeviceId());
		MessageHelper.sendCmd(socket, msg);
		System.out.println("handleFullyCharged");
		
		clientFrame.appendCommandLine("充满");
	}

	public void doTerminateCharging(){
		if(!checkSocket()){
			return;
		}
		TerminateChargingMessage msg = new TerminateChargingMessage();
		msg.setDeviceId(ClientHolder.getClient().getDeviceId());
		MessageHelper.sendCmd(socket, msg);
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
