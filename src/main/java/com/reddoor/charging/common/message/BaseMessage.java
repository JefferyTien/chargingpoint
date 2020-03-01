package com.reddoor.charging.common.message;

public class BaseMessage {
	protected String from;
	protected String to;
	protected String owner;
	protected int type;
	
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getOwner() {
		return owner;
	}
	public int getType() {
		return type;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public void setType(int type) {
		this.type = type;
	}
}
