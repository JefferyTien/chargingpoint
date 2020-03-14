package com.reddoor.charging.client.handler.impl;

import java.net.Socket;

import com.reddoor.charging.client.handler.Handler;
import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.response.PrepareChargingResp;
import com.reddoor.charging.common.message.transform.Responsive;
import com.reddoor.charging.util.Logger;

public class PrepareChargingHandler implements Handler{

	@Override
	public Object handle(Socket socket, BaseMessage message) {
		Logger.log("Server Prepare Charging");
		
		if(message instanceof Responsive){
			BaseMessage resp = ((Responsive) message).getRespMessage();
			MessageHelper.sendCmd(socket, resp);
			return resp;
		}
		return null;
				
	}

}
