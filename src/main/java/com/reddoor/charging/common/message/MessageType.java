package com.reddoor.charging.common.message;

public interface MessageType {
	
	/**
	 * server => client 
	 */
	public static final int SET_HEARTBEAT_INTERVAL = 0x02;
	public static final int PREPARE_CHARGING = 0x03;
	public static final int END_CHARGING = 0x04;
	
	
	/**
	 * client => server 
	 */
	public static final int  START_CHARGING = 0x85;
	public static final int  TERMINATE_CHARGING = 0x86;
	public static final int  FULLY_CHARGED = 0x87;
	
	public static final int HEART_BEAT = 0x81;
	
	
	/*----------------reponse message------------ */
	/**
	 * response 4 server => client 
	 */
	public static final int SET_HEARTBEAT_INTERVAL_RESP = 0x82;
	public static final int PREPARE_CHARGING_RESP = 0x83;
	public static final int END_CHARGING_RESP = 0x84;
	
	
	/**
	 * response 4 client => server 
	 */
	public static final int  START_CHARGING_RESP = 0x05;
	public static final int  TERMINATE_CHARGING_RESP = 0x06;
	public static final int  FULLY_CHARGED_RESP = 0x07;
	
	public static final int HEART_BEAT_RESP = 0x01;
	
	
}
