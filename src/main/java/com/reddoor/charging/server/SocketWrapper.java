package com.reddoor.charging.server;

import java.net.Socket;
import java.util.Date;

public class SocketWrapper {
	private Socket socket;
	private String terminalId; // 设备插口的ID
	private Date lastAliveTime;
	
	public SocketWrapper(Socket socket, Date lastAliveTime) {
		this.socket = socket;
		this.lastAliveTime = lastAliveTime;
	}

	public Socket getSocket() {
		return socket;
	}

	public Date getLastAliveTime() {
		return lastAliveTime;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setLastAliveTime(Date lastAliveTime) {
		this.lastAliveTime = lastAliveTime;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
}
