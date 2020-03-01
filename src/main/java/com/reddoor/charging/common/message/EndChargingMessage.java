package com.reddoor.charging.common.message;

public class EndChargingMessage extends BaseMessage{
	
	private final int type = MessageType.END_CHARGING;

	public int getType() {
		return type;
	}
}
