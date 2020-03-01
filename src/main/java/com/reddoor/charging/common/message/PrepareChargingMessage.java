package com.reddoor.charging.common.message;

public class PrepareChargingMessage extends BaseMessage{
	
	private final int type = MessageType.PREPARE_CHARGING;

	public int getType() {
		return type;
	}
}
