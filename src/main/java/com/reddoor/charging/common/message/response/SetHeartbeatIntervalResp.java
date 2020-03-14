package com.reddoor.charging.common.message.response;

import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.MessageType;
import com.reddoor.charging.util.ConvertUtil;

public class SetHeartbeatIntervalResp extends BaseMessage {
	private final int type = MessageType.SET_HEARTBEAT_INTERVAL_RESP;

	private int heartbeatMinute;
	private int heartbeatSecond;
	
	public int getType() {
		return type;
	}

	@Override
	public BaseMessage transformFromHex(String hexStr) {
		hexStr = hexStr.replaceAll(" ", "");
		String deviceIdStr = hexStr.substring(2, 10);
		this.deviceId = Long.parseLong(deviceIdStr, 16);
		
		String minuteStr = hexStr.substring(10, 12);
		String secondStr;
		if(hexStr.length()>14){
			secondStr = hexStr.substring(12, 14);
		}
		else {
			secondStr = hexStr.substring(12);
		}
		this.heartbeatMinute = Integer.parseInt(minuteStr, 16);
		this.heartbeatSecond = Integer.parseInt(secondStr, 16);
		
		return this;
	}

	@Override
	public String transformToHex() {
		StringBuffer sb = new StringBuffer();
		sb.append(ConvertUtil.intToHexString(this.type)).append(" ")
			.append(ConvertUtil.deviceIdToHexString(this.deviceId)).append(" ")
			.append(ConvertUtil.intToHexString(this.heartbeatMinute)).append(" ")
			.append(ConvertUtil.intToHexString(this.heartbeatSecond));
		
		return sb.toString();
	}

	public int getHeartbeatMinute() {
		return heartbeatMinute;
	}

	public int getHeartbeatSecond() {
		return heartbeatSecond;
	}

	public void setHeartbeatMinute(int heartbeatMinute) {
		this.heartbeatMinute = heartbeatMinute;
	}

	public void setHeartbeatSecond(int heartbeatSecond) {
		this.heartbeatSecond = heartbeatSecond;
	}
}
