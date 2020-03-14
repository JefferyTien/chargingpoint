package com.reddoor.charging.client.handler.impl;

import java.net.Socket;

import com.reddoor.charging.client.handler.Handler;
import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.response.EndChargingResp;
import com.reddoor.charging.common.message.transform.Responsive;
import com.reddoor.charging.util.Logger;

public class EndChargingHandler implements Handler{
	
	@Override
	public Object handle(Socket socket, BaseMessage message) {
		Logger.log("Server End Charging");
		// 回复
		if(message instanceof Responsive){
			BaseMessage resp = ((Responsive) message).getRespMessage();
			MessageHelper.sendCmd(socket, resp);
			return resp;
		}
		
		return null;
	}
}
