package com.reddoor.charging.common.message;

import com.reddoor.charging.common.message.response.StartChargingResp;
import com.reddoor.charging.common.message.transform.Responsive;
import com.reddoor.charging.util.ConvertUtil;

public class StartChargingMessage extends BaseMessage implements Responsive {
	
	private final int type = MessageType.START_CHARGING;

	public int getType() {
		return type;
	}

	@Override
	public BaseMessage transformFromHex(String hexStr) {
		hexStr = hexStr.replaceAll(" ", "");
		String deviceIdStr = hexStr.substring(2, 10);
		this.deviceId = Long.parseLong(deviceIdStr, 16);
		String portStr;
		if(hexStr.length()>12){
			portStr = hexStr.substring(10, 12);
		}
		else{
			portStr = hexStr.substring(10);
		}
		this.portId = Integer.parseInt(portStr, 16);
		return this;
	}

	@Override
	public String transformToHex() {
		StringBuffer sb = new StringBuffer();
		sb.append(ConvertUtil.intToHexString(this.type)).append(" ")
			.append(ConvertUtil.deviceIdToHexString(this.deviceId)).append(" ")
			.append(ConvertUtil.intToHexString(this.portId));
		
		return sb.toString();
	}

	@Override
	public BaseMessage getRespMessage() {
		StartChargingResp respMsg = new StartChargingResp();
		respMsg.setDeviceId(deviceId);
		respMsg.setPortId(portId);
		return respMsg;
	}
}
