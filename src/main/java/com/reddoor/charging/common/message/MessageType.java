package com.reddoor.charging.common.message;

public interface MessageType {
	
	/**
	 * server => client 
	 */
	public static final int PREPARE_CHARGING = 0;
	public static final int END_CHARGING = 1;
	
	/**
	 * 
	 */
	public static final int HEART_BEAT = 2;
	
	/**
	 * client => server 
	 */
	public static final int  START_CHARGING = 10;
	public static final int  FULLY_CHARGED = 11;
	public static final int  TERMINATE_CHARGING = 12;
	
	
}
