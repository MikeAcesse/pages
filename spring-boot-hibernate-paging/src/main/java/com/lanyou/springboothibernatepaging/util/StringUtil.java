package com.lanyou.springboothibernatepaging.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fanzk
 * @version 1.8
 * @date 2020/12/14 17:01
 */
public class StringUtil {
	/**
	 * 校验字符串是否是大于0的数字
	 * @param string
	 * @return
	 */
	public static boolean isNum(String string){
		Pattern pattern = Pattern.compile("[1-9]{1}\\d*");
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}
}
