package com.reddoor.charging.common.message;

import java.util.ArrayList;
import java.util.List;

import com.reddoor.charging.common.message.response.HeartBeatResp;
import com.reddoor.charging.common.message.transform.Responsive;
import com.reddoor.charging.util.ConvertUtil;

public class HeartBeatMessage extends BaseMessage implements Responsive{

	private final int type = MessageType.HEART_BEAT;
	
	private List<PortStatus> portList = new ArrayList<PortStatus>();
	
	public class PortStatus {
		private int portId;
		private int status;
		
		public int getPortId() {
			return portId;
		}
		public int getStatus() {
			return status;
		}
		public void setPortId(int portId) {
			this.portId = portId;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		
	}
	
	public int getType() {
		return type;
	}

	@Override
	public BaseMessage transformFromHex(String hexStr) {
		hexStr = hexStr.replaceAll(" ", "");
		String deviceIdStr = hexStr.substring(2, 10);
		this.deviceId = Long.parseLong(deviceIdStr, 16);
		
		int startIndex = 10;
		int len = hexStr.length();
		String portStr;
		int status, portNum;
		while(startIndex < len){
			if((startIndex+2)<len){
				portStr = hexStr.substring(startIndex, startIndex+2);
			}
			else{
				portStr = hexStr.substring(startIndex);
			}
			status = Integer.parseInt(portStr, 16);
			portNum = startIndex/2 - 5;  // 从字符串第10位开始是第0号端口
			
			PortStatus portStatus = new PortStatus();
			portStatus.setPortId(portNum);
			portStatus.setStatus(status);
			portList.add(portStatus);
			
			// + + 
			startIndex = startIndex + 2;
		}
		
		return this;
	}
	
	@Override
	public String transformToHex() {
		StringBuffer sb = new StringBuffer();
		sb.append(ConvertUtil.intToHexString(this.type)).append(" ")
			.append(ConvertUtil.deviceIdToHexString(this.deviceId)).append(" ");
		for(PortStatus eachPort: portList){
			sb.append(ConvertUtil.intToHexString(eachPort.getStatus())).append(" ");
		}
		return sb.toString();
	}

	public List<PortStatus> getPortList() {
		return portList;
	}

	public void setPortList(List<PortStatus> portList) {
		this.portList = portList;
	}

	@Override
	public BaseMessage getRespMessage() {
		HeartBeatResp respMsg = new HeartBeatResp();
		respMsg.setDeviceId(this.deviceId);
		return respMsg;
	}
}
