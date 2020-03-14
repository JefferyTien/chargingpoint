package com.reddoor.charging.client;

import java.net.Socket;

import com.reddoor.charging.common.Constants;
import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.HeartBeatMessage;
import com.reddoor.charging.util.ConvertUtil;
import com.reddoor.charging.util.Logger;

public class HeartBeat extends Thread{
	
	private Socket socket;
	
	private long deviceId;
	
	public HeartBeat(Socket socket, long deviceId){
		this.socket = socket;
		this.deviceId = deviceId;
	}
	
	@Override
    public void run() {
		while(null != socket && !socket.isClosed()){
			try {
				HeartBeatMessage message = new HeartBeatMessage();
				message.setDeviceId(deviceId);
				String deviceIdHexSt = ConvertUtil.deviceIdToHexString(deviceId);
				message.transformFromHex("81 "+deviceIdHexSt+" 01 01 00 00 00 00 00 00 01 01");
				
				// debug
				Logger.log("81 "+deviceIdHexSt+" 01 01 00 00 00 00 00 00 01 01");
				System.out.println(message.getDeviceId());
				System.out.println(message.transformToHex());
				
				
				MessageHelper.sendCmd(socket, message);
				
				
				Thread.sleep(Constants.HEART_BEAT_PERIOD * 1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
}
