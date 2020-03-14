package com.reddoor.charging.common;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.alibaba.fastjson.JSON;
import com.reddoor.charging.common.message.BaseMessage;
import com.reddoor.charging.common.message.transform.Transformer;
import com.reddoor.charging.common.message.transform.TransformerFactory;
import com.reddoor.charging.util.ConvertUtil;

public class MessageHelper {
	
	// 以下几个方法用于与硬件设备的字节流交互
	public static BaseMessage transformFromByte(byte[] data){
		String hexStr = ConvertUtil.bytesToHexString(data);
		Transformer transformer = TransformerFactory.getTransformer(hexStr);
		return transformer.transformFromHex(hexStr);
	}
	
	public static byte[] transformToByte(BaseMessage message){
		String hexStr = message.transformToHex();
		return ConvertUtil.hexStrToByteArrs(hexStr);
	}
	
	public synchronized static void sendCmd(Socket socket, BaseMessage msg){
		if(null != socket && !socket.isClosed()){
			try {
				OutputStream out = socket.getOutputStream();
				byte[] byteArr = transformToByte(msg);
				out.write(byteArr);
				out.flush();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 以下几个方法可用于直接发送字符流 (JSON结构) 的场景,比如聊天等
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
