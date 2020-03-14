package com.reddoor.charging.util;

public class ConvertUtil {

	private ConvertUtil() {
	}

	/**
	 * 将十六进制的字符串转换成字节数组
	 *
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStrToByteArrs(String hexString) {
		if (null == hexString || "".equals(hexString)) {
			return null;
		}

		hexString = hexString.replaceAll(" ", "");
		int len = hexString.length();
		int index = 0;

		byte[] bytes = new byte[len / 2];

		while (index < len) {
			String sub = hexString.substring(index, index + 2);
			bytes[index / 2] = (byte) Integer.parseInt(sub, 16);
			index += 2;
		}

		return bytes;
	}

	/**
	 * 数组转换成十六进制字符串
	 * 
	 * @param byte[]
	 * @return HexString
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
			sb.append(" ");
		}
		return sb.toString();
	}
	
	
	public static final String intToHexString(int intValue) {
		String hexStr = Integer.toHexString(intValue);
		if (hexStr.length() < 2) {
			hexStr = "0" + hexStr;
		}
		return hexStr;
	}
	
//	public static final String longToHexString(long intValue) {
//		String hexStr = Long.toHexString(intValue);
//		if (hexStr.length() < 2) {
//			hexStr = "0" + hexStr;
//		}
//		return hexStr;
//	}
	
	public static final String deviceIdToHexString(long intValue) {
		String deviceIdHexStr = Long.toHexString(intValue);
		// 补足8个  (按照与硬件的协议要求,deviceId占4byte,十六进制字符串长度为8)
		while (deviceIdHexStr.length() < 8) {
			deviceIdHexStr = "0" + deviceIdHexStr;
		}
		return deviceIdHexStr;
	}
	
	// int => hexStr
//	Integer.toHexString(v)
	
	// hexStr => int
//	Integer.parseInt(commandHex, 16);
	
	
	public static void main(String[] args) {
		// int
//		System.out.println(Integer.toHexString(10));
//		String hexStr = ConvertUtil.intToHexString(10);
//		System.out.println(hexStr);
//		System.out.println(Integer.parseInt(hexStr, 16));
		
		// long
//		System.out.println(Long.toHexString(10));
//		String hexStr2 = ConvertUtil.longToHexString(10L);
//		System.out.println(hexStr2);
//		System.out.println(Long.parseLong(hexStr2, 16));
		
		System.out.println(Long.parseLong("00002c04", 16));
		
	}

}
