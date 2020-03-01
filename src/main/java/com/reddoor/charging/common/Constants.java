package com.reddoor.charging.common;

public interface Constants {
	public static final String SERVER_IP = "127.0.0.1";
	
	public static final int SERVER_PORT = 9999;
	
	/**
	 * 心跳间隔   (单位: 秒)
	 */
	public static final int HEART_BEAT_PERIOD = 10;
	
	/**
	 * server线程池大小
	 */
	public static final int SERVER_POOL_SIZE = 40;
}
