package com.reddoor.charging.server.handler.impl;

import java.net.Socket;
import java.util.Date;

import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.response.HeartBeatResp;
import com.reddoor.charging.common.message.transform.Responsive;
import com.reddoor.charging.server.SocketHolder;
import com.reddoor.charging.server.SocketWrapper;
import com.reddoor.charging.server.handler.SocketHandler;
import com.reddoor.charging.util.Logger;
import com.reddoor.charging.util.StringUtil;

public class HeartBeatHandler implements SocketHandler{
	@Override
	public Object handle(Socket socket, BaseMessage message) {
		Logger.log("Client Heart Beat");
		
		// TODO 处理来自客户端的心跳指令, 入库等
		
//		HeartBeatMessage message = MessageHelper.parseObject(data.toString(), HeartBeatMessage.class);
		SocketWrapper wrapper = SocketHolder.get("Device-" + message.getDeviceId());
		if (wrapper != null) {
			wrapper.setLastAliveTime(new Date()); 
			//TODO check if IP changes, then replace the socket
//				if(wrapper.getSocket().getLocalAddress() ){
//					
//				}
			wrapper.setSocket(socket);
			
			SocketHolder.put("Device-" + message.getDeviceId(), wrapper);
		}
		else {
			// not found, the put socket into SocketHolder
			wrapper = new SocketWrapper(socket, new Date());
			SocketHolder.put("Device-" + message.getDeviceId(), wrapper);
		}
		
		// 回复心跳消息
		if(message instanceof Responsive){
			HeartBeatResp resp = (HeartBeatResp)((Responsive) message).getRespMessage();
			MessageHelper.sendCmd(socket, resp);
			return resp;
		}
		return null;
	}

}
