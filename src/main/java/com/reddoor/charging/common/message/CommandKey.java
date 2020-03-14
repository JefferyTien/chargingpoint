package com.reddoor.charging.common.message;

public class CommandKey {
	// message类型(指令类型)
	private int type;
	// 充电桩ID
	private int deviceId;
	// 充电桩上的插口ID
	private int portId;
	
	public int getType() {
		return type;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public int getPortId() {
		return portId;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public void setPortId(int portId) {
		this.portId = portId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deviceId;
		result = prime * result + portId;
		result = prime * result + type;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandKey other = (CommandKey) obj;
		if (deviceId != other.deviceId)
			return false;
		if (portId != other.portId)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
