package com.reddoor.charging.server.action;

import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.EndChargingMessage;
import com.reddoor.charging.common.message.MessageType;
import com.reddoor.charging.common.message.PrepareChargingMessage;
import com.reddoor.charging.common.message.SetHeartbeatIntervalMessage;
import com.reddoor.charging.common.observe.Publisher;
import com.reddoor.charging.server.ServerFrame;
import com.reddoor.charging.server.SocketHolder;
import com.reddoor.charging.server.SocketWrapper;

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
			commandContent = message.getDeviceId() + " start charging";
			break;
		case MessageType.FULLY_CHARGED:
			commandContent = message.getDeviceId() + " fully charged";
			break;
		case MessageType.TERMINATE_CHARGING:
			commandContent = message.getDeviceId() + " terminate charging";
			break;
		case MessageType.HEART_BEAT:
			commandContent = "~~~~heartbeat~~~ from " + message.getDeviceId();
			
			serverFrame.updateClientList(String.valueOf(message.getDeviceId()));
			break;
		case MessageType.PREPARE_CHARGING_RESP:
			commandContent = message.getDeviceId() + " PREPARE_CHARGING_RESP";
			break;
		case MessageType.END_CHARGING_RESP:
			commandContent = message.getDeviceId() + " END_CHARGING_RESP";
			break;
		case MessageType.SET_HEARTBEAT_INTERVAL_RESP:
			commandContent = message.getDeviceId() + " SET_HEARTBEAT_INTERVAL_RESP";
			break;
		}
		
		// update command line
		serverFrame.appendCommandLine(commandContent);
	}
	
	public void doPrepareCharging(long deviceId, int portId, int chargingHour, int chargingMinute, int chargingSecond){
		SocketWrapper wrapper = SocketHolder.get("Device-" + deviceId);
		Socket socket = wrapper.getSocket();
		if(null == socket){
			return;
		}
		PrepareChargingMessage msg = new PrepareChargingMessage();
		msg.setDeviceId(deviceId);
		msg.setPortId(portId);
		msg.setChargingHour(chargingHour);
		msg.setChargingMinute(chargingMinute);
		msg.setChargingSecond(chargingSecond);
		MessageHelper.sendCmd(socket, msg);
	}
	
	public void doEndCharging(long deviceId, int portId){
		SocketWrapper wrapper = SocketHolder.get("Device-" + deviceId);
		Socket socket = wrapper.getSocket();
		if(null == socket){
			return;
		}
		EndChargingMessage msg = new EndChargingMessage();
		msg.setDeviceId(deviceId);
		msg.setPortId(portId);
		MessageHelper.sendCmd(socket, msg);
	}
	
	public void doSetHeartbeatInterval(long deviceId, int intervalMinute, int intervalSecond){
		SocketWrapper wrapper = SocketHolder.get("Device-" + deviceId);
		Socket socket = wrapper.getSocket();
		if(null == socket){
			return;
		}
		SetHeartbeatIntervalMessage msg = new SetHeartbeatIntervalMessage();
		msg.setDeviceId(deviceId);
		msg.setHeartbeatMinute(intervalMinute);
		msg.setHeartbeatSecond(intervalSecond);
		MessageHelper.sendCmd(socket, msg);
	}

	public ServerFrame getServerFrame() {
		return serverFrame;
	}

	public void setServerFrame(ServerFrame serverFrame) {
		this.serverFrame = serverFrame;
	}
}
