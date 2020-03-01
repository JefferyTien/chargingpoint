package com.reddoor.charging.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.alibaba.fastjson.JSON;
import com.reddoor.charging.common.message.BaseMessage;

public class MessageHelper {
	
	public static BaseMessage parseObject(String content){
		BaseMessage message = JSON.parseObject(content, BaseMessage.class);
		return message;
	}
	
	public static <T> T parseObject(String content, Class<T> clazz){
		T message = JSON.parseObject(content, clazz);
		return message;
	}
	
	public static Object transform(BaseMessage message){
		return JSON.toJSON(message);
	}
	
	public synchronized static void sendMsg(Socket socket, BaseMessage msg){
		if(null != socket && !socket.isClosed()){
			try {
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				String jsonStr = MessageHelper.transform(msg).toString();
				pw.println((jsonStr + "\n"));
				pw.flush();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
