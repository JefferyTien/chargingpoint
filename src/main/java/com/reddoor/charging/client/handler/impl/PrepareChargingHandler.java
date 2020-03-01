package com.reddoor.charging.client.handler.impl;

import com.reddoor.charging.client.handler.Handler;
import com.reddoor.charging.util.Logger;

public class PrepareChargingHandler implements Handler{

	@Override
	public Object handle(Object data) {
		Logger.log("Server Prepare Charging");
		return null;
	}

}
