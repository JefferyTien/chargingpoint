package com.reddoor.charging.common.message;

import com.reddoor.charging.common.message.transform.Transformer;

public abstract class BaseMessage implements Transformer{
	// message类型(指令类型)
	protected int type;
	// 充电桩ID
	protected long deviceId;
	// 充电桩上的插口ID
	protected int portId;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getDeviceId() {
		return deviceId;
	}
	public int getPortId() {
		return portId;
	}
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
	public void setPortId(int portId) {
		this.portId = portId;
	}
}
