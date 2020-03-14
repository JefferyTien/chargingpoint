package com.reddoor.charging.common.message;

import com.reddoor.charging.common.message.response.TerminateChargingResp;
import com.reddoor.charging.common.message.transform.Responsive;
import com.reddoor.charging.util.ConvertUtil;

public class TerminateChargingMessage extends BaseMessage implements Responsive {
	
	private final int type = MessageType.TERMINATE_CHARGING;

	private int chargingHour;
	private int chargingMinute;
	private int chargingSecond;
	
	public int getType() {
		return type;
	}

	@Override
	public BaseMessage transformFromHex(String hexStr) {
		hexStr = hexStr.replaceAll(" ", "");
		String deviceIdStr = hexStr.substring(2, 10);
		this.deviceId = Long.parseLong(deviceIdStr, 16);
		
		String portStr = hexStr.substring(10, 12);
		this.portId = Integer.parseInt(portStr, 16);
		
		String hourStr = hexStr.substring(12, 14);
		String minuteStr  = hexStr.substring(14, 16);
		String secondStr;
		if(hexStr.length()>18){
			secondStr = hexStr.substring(16, 18);
		}
		else {
			secondStr = hexStr.substring(16);
		}
		
		this.chargingHour = Integer.parseInt(hourStr, 16);
		this.chargingMinute = Integer.parseInt(minuteStr, 16);
		this.chargingSecond = Integer.parseInt(secondStr, 16);
		
		return this;
	}

	@Override
	public String transformToHex() {
		StringBuffer sb = new StringBuffer();
		sb.append(ConvertUtil.intToHexString(this.type)).append(" ")
			.append(ConvertUtil.deviceIdToHexString(this.deviceId)).append(" ")
			.append(ConvertUtil.intToHexString(this.portId)).append(" ")
			.append(ConvertUtil.intToHexString(this.chargingHour)).append(" ")
			.append(ConvertUtil.intToHexString(this.chargingMinute)).append(" ")
			.append(ConvertUtil.intToHexString(this.chargingSecond));
		
		return sb.toString();
	}

	@Override
	public BaseMessage getRespMessage() {
		TerminateChargingResp respMsg = new TerminateChargingResp();
		respMsg.setDeviceId(deviceId);
		respMsg.setPortId(portId);
		return respMsg;
	}

	public int getChargingHour() {
		return chargingHour;
	}

	public int getChargingMinute() {
		return chargingMinute;
	}

	public int getChargingSecond() {
		return chargingSecond;
	}

	public void setChargingHour(int chargingHour) {
		this.chargingHour = chargingHour;
	}

	public void setChargingMinute(int chargingMinute) {
		this.chargingMinute = chargingMinute;
	}

	public void setChargingSecond(int chargingSecond) {
		this.chargingSecond = chargingSecond;
	}
}
