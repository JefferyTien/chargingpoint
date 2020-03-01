package com.reddoor.charging.client.handler;

import com.reddoor.charging.client.handler.impl.EndChargingHandler;
import com.reddoor.charging.client.handler.impl.PrepareChargingHandler;

public class HandlerFactory {
	public static Handler getHandler(String key){
		switch(key){
		case "PrepareCharging":
			return new PrepareChargingHandler();
		case "EndCharging":
			return new EndChargingHandler();
		}
		return null;
	}
}
