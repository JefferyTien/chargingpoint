package com.reddoor.charging.common.message;

public class HeartBeatMessage extends BaseMessage {

	private final int type = MessageType.HEART_BEAT;
	
	private String ip = "127.0.0.1";

	public int getType() {
		return type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
