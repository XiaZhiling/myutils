/*
 * @(#)EncryptUtil.java    1.0   2016/04/18
 *
 * Copyright (c) 2015-2016 Ling Software, Inc.
 * 1301 Mingkang Road, sz, GuangDong, China.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of 
 * Ling Software, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with xzl.
 */

package com.ling.utils;

import java.security.MessageDigest;

/**
 * 加密方法工具
 * 
 * @author Zhiling.Xia
 * @since version 1.0  2016-04-18
 */
public class EncryptUtil {
	
	/**
	 * SHA1 加密
	 * @param str 需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String getSha1(String str) {
		if(str == null || str.length() == 0){
			return null;
		}
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] md = messageDigest.digest();
			int j = md.length;
			char buf[] = new char[j*2];
			int k =0;
			for(int i = 0;i<j;i++){
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf]; 
			}
			return new String(buf);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
