package com.reddoor.charging.common.message.transform;

import com.reddoor.charging.common.message.BaseMessage;

/**
 * 与硬件交互用的是字节流, 此接口用于将对象和十六进制字符串之间进行转换
 * (实际发送时会再把十六进制字符串转换为字节数组)
 * @author tjf
 *
 */
public interface Transformer {
	
	public BaseMessage transformFromHex(String hexStr);
	
	public String transformToHex();
	
}
