package com.reddoor.charging.common.message.transform;

import com.reddoor.charging.common.message.EndChargingMessage;
import com.reddoor.charging.common.message.FullyChargedMessage;
import com.reddoor.charging.common.message.HeartBeatMessage;
import com.reddoor.charging.common.message.MessageType;
import com.reddoor.charging.common.message.PrepareChargingMessage;
import com.reddoor.charging.common.message.SetHeartbeatIntervalMessage;
import com.reddoor.charging.common.message.StartChargingMessage;
import com.reddoor.charging.common.message.TerminateChargingMessage;
import com.reddoor.charging.common.message.response.EndChargingResp;
import com.reddoor.charging.common.message.response.FullyChargedResp;
import com.reddoor.charging.common.message.response.HeartBeatResp;
import com.reddoor.charging.common.message.response.PrepareChargingResp;
import com.reddoor.charging.common.message.response.SetHeartbeatIntervalResp;
import com.reddoor.charging.common.message.response.StartChargingResp;
import com.reddoor.charging.common.message.response.TerminateChargingResp;

public class TransformerFactory {
	public static Transformer getTransformer(String hexStr){
		String commandHex = hexStr.substring(0, 2); // 前两位为指令类型,根据指令类型选取对应的转换器
		int command = Integer.parseInt(commandHex, 16);
		switch(command){
		case MessageType.START_CHARGING:
			return new StartChargingMessage();
		case MessageType.FULLY_CHARGED:
			return new FullyChargedMessage();
		case MessageType.TERMINATE_CHARGING:
			return new TerminateChargingMessage();
		case MessageType.HEART_BEAT:
			return new HeartBeatMessage();
		case MessageType.PREPARE_CHARGING:
			return new PrepareChargingMessage();
		case MessageType.END_CHARGING:
			return new EndChargingMessage();
		case MessageType.SET_HEARTBEAT_INTERVAL:
			return new SetHeartbeatIntervalMessage();
	    // response message
		case MessageType.START_CHARGING_RESP:
			return new StartChargingResp();
		case MessageType.FULLY_CHARGED_RESP:
			return new FullyChargedResp();
		case MessageType.TERMINATE_CHARGING_RESP:
			return new TerminateChargingResp();
		case MessageType.HEART_BEAT_RESP:
			return new HeartBeatResp();
		case MessageType.PREPARE_CHARGING_RESP:
			return new PrepareChargingResp();
		case MessageType.END_CHARGING_RESP:
			return new EndChargingResp();	
		case MessageType.SET_HEARTBEAT_INTERVAL_RESP:
			return new SetHeartbeatIntervalResp();
		}
		return null;
	}
}
