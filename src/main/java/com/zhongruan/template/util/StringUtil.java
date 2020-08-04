package com.zhongruan.template.util;

import java.util.Random;

/**
 * @Author: zhou
 * Created by zhou chong on 11:32 2020/8/4.
 * @Description:
 */
public class StringUtil {
	private static final String ALLCHAR = "0123456789";
	private static final Random random = new Random();
	public static String randomStr(){
		final StringBuilder salt = new StringBuilder();
		for (int i=0; i < 4;i++){
			salt.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return salt.toString();
	}
}
