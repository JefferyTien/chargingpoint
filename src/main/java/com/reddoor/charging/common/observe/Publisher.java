package com.reddoor.charging.common.observe;

import java.net.Socket;
import java.util.Observable;

import com.reddoor.charging.common.MessageHelper;
import com.reddoor.charging.common.message.BaseMessage;

public class Publisher extends Observable{
	
	private BaseMessage data = null;

    public BaseMessage getData() {
        return data;
    }

//    public void publish(BaseMessage data) {
//        this.data = data;
//        setChanged();    
//        notifyObservers();    
//    }
    
    
    public void publishCmd(Socket socket, BaseMessage baseMessage){
		this.data = baseMessage;
        setChanged();    //改变通知者的状态
        notifyObservers();    //调用父类Observable方法，通知所有观察者
	}
    
	public void publishMsg(Socket socket, Object data){
		BaseMessage baseMessage = MessageHelper.parseObject(data.toString());
		this.data = baseMessage;
        setChanged();    //改变通知者的状态
        notifyObservers();    //调用父类Observable方法，通知所有观察者
	}
	
	
}
