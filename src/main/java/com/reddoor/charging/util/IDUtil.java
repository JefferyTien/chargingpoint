package com.reddoor.charging.util;

import java.lang.management.ManagementFactory;

public class IDUtil {
	public static String jvmPid() {
		String pid = ManagementFactory.getRuntimeMXBean().getName();
	    int indexOf = pid.indexOf('@');
	    if(indexOf > 0){
	        pid = pid.substring(0,indexOf);
	    }
	    return pid;
    }
	
	public static void main(String[] args) {
		System.out.println(IDUtil.jvmPid());
	}
}
