package com.reddoor.charging.common.message;

public class StartChargingMessage extends BaseMessage {
	
	private final int type = MessageType.START_CHARGING;

	public int getType() {
		return type;
	}
}
