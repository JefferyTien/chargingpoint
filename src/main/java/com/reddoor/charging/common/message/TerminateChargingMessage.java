package com.reddoor.charging.common.message;

public class TerminateChargingMessage extends BaseMessage {
	
	private final int type = MessageType.TERMINATE_CHARGING;

	public int getType() {
		return type;
	}
}
