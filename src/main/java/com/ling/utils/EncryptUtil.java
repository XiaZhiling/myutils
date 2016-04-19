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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * 加密方法工具
 * 
 * @author Zhiling.Xia
 * @since version 1.0 2016-04-18
 */
public class EncryptUtil {

	/**
	 * 获得日志记录器
	 */
	private static Logger logger = Logger.getLogger(EncryptUtil.class);

	/**
	 * SHA1 加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] md = messageDigest.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
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

	/**
	 * MD5加密
	 * 
	 * @param str
	 *            需加密的字符串
	 * @param encoding
	 *            字符编码
	 * @return 返回32位加密后字符串
	 */
	public static String getMD5(String str, String encoding) {
		MessageDigest messageDigest = null;
		if (str == null) {
			return null;
		}
		try {
			byte[] unStr = str.getBytes(encoding);
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(unStr);
			byte[] byteArray = messageDigest.digest();

			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
			return md5StrBuff.toString();
		} catch (UnsupportedEncodingException e) {
			logger.error("MD5加密失败:");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.error("MD5加密失败:");
			e.printStackTrace();
		}
		return null;
	}
}
