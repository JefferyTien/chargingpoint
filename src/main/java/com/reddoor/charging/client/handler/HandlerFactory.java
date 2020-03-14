package com.reddoor.charging.client.handler;

import com.reddoor.charging.client.handler.impl.EndChargingHandler;
import com.reddoor.charging.client.handler.impl.PrepareChargingHandler;
import com.reddoor.charging.common.message.MessageType;

public class HandlerFactory {
	public static Handler getHandler(int type){
		switch(type){
		case MessageType.PREPARE_CHARGING:
			return new PrepareChargingHandler();
		case MessageType.END_CHARGING:
			return new EndChargingHandler();
		}
		return null;
	}
}
