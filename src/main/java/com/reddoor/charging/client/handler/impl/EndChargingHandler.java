package com.reddoor.charging.client.handler.impl;

import com.reddoor.charging.client.handler.Handler;
import com.reddoor.charging.util.Logger;

public class EndChargingHandler implements Handler{
	
	@Override
	public Object handle(Object data) {
		Logger.log("Server End Charging");
		
		
		return null;
	}
}
