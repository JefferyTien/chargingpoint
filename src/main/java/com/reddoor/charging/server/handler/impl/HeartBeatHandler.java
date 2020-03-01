package com.reddoor.charging.server.handler.impl;

import java.net.Socket;
import java.util.Date;

import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.HeartBeatMessage;
import com.reddoor.charging.common.observe.PublisherHolder;
import com.reddoor.charging.server.SocketHolder;
import com.reddoor.charging.server.SocketWrapper;
import com.reddoor.charging.server.handler.SocketHandler;
import com.reddoor.charging.util.Logger;
import com.reddoor.charging.util.StringUtil;

public class HeartBeatHandler implements SocketHandler{
	@Override
	public Object handle(Socket socket, Object data) {
		Logger.log("Client Heart Beat");
		HeartBeatMessage message = MessageHelper.parseObject(data.toString(), HeartBeatMessage.class);
		if (StringUtil.isNotEmpty(message.getFrom())) {
			SocketWrapper wrapper = SocketHolder.get(message.getFrom());
			if (wrapper != null) {
				wrapper.setLastAliveTime(new Date()); 
				//TODO check if IP changes, then replace the socket
//				if(wrapper.getSocket().getLocalAddress() ){
//					
//				}
				wrapper.setSocket(socket);
				
				SocketHolder.put(message.getFrom(), wrapper);
			}
			else {
				// not found, the put socket into SocketHolder
				wrapper = new SocketWrapper(socket, new Date());
				SocketHolder.put(message.getFrom(), wrapper);
			}
		}
		return null;
	}

}
