package com.reddoor.charging.common.message;

public class FullyChargedMessage extends BaseMessage {
	
	private final int type = MessageType.FULLY_CHARGED;

	public int getType() {
		return type;
	}
}
