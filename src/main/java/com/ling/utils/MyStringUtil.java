/*
 * @(#)MyStringUtil.java    1.0   2016/04/18
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 字符串处理工具
 * 
 * @author Zhiling.Xia
 * @since version 1.0  2016-04-18
 */
public class MyStringUtil {
	
	/**
	 * 时间格式：20140805
	 */
	private static String Date_TIME_FMT_8 = "yyyyMMdd";
	
	/**
	 * 获取当前系统日期
	 * @return  日期格式字符串20140805
	 */
	public static String getCurrentDate() {
		return getSimpleDateFormat(Date_TIME_FMT_8).format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 根据给出的日期格式生成一个SimpleDateFormat对象
	 * @param pattern 日期格式
	 * @return SimpleDateFormat
	 */
	private static SimpleDateFormat getSimpleDateFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}
}
