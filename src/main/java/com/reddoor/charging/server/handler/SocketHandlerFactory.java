package com.reddoor.charging.server.handler;

import com.reddoor.charging.common.message.MessageType;
import com.reddoor.charging.server.handler.impl.TerminateChargingHandler;
import com.reddoor.charging.server.handler.impl.FullyChargedHandler;
import com.reddoor.charging.server.handler.impl.HeartBeatHandler;
import com.reddoor.charging.server.handler.impl.StartChargingHandler;

public class SocketHandlerFactory {
	public static SocketHandler getHandler(int type){
		switch(type){
		case MessageType.START_CHARGING:
			return new StartChargingHandler();
		case MessageType.FULLY_CHARGED:
			return new FullyChargedHandler();
		case MessageType.TERMINATE_CHARGING:
			return new TerminateChargingHandler();
		case MessageType.HEART_BEAT:
			return new HeartBeatHandler();
		}
		return null;
	}
}
