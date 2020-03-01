package com.reddoor.charging.server;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SocketHolder {
	private static ConcurrentMap<String, SocketWrapper> listSocketWrap = new ConcurrentHashMap<String, SocketWrapper>();
	
	public static Set<String> keySet() {
		return listSocketWrap.keySet();
	}
	
	public static SocketWrapper get(String key) {
		return listSocketWrap.get(key);
	}
	
	public static void put(String key, SocketWrapper value) {
		listSocketWrap.put(key, value);
	}
	
	public static SocketWrapper remove(String key) {
		return listSocketWrap.remove(key);
	}
	
	public static void clear() {
		listSocketWrap.clear();
	}
}
