package com.yu.cvs.network;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CvsUtils {
	
	public static String GetMD5(String data) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		md5.update(data.getBytes());
		byte[] m = md5.digest();// 加密

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < m.length; i++) {
			int b = (0xFF & m[i]);
			// if it is a single digit, make sure it have 0 in front (proper
			// padding)
			if (b <= 0xF)
				sb.append("0");
			// add number to string
			sb.append(Integer.toHexString(b));
		}

		return sb.toString();
	}

}
