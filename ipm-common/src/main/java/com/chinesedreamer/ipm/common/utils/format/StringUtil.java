package com.chinesedreamer.ipm.common.utils.format;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	public static String subString(String str, String indexStr, boolean trim){
		if (StringUtils.isEmpty(str) || StringUtils.isEmpty(indexStr)) {
			return str;
		}
		String subStr = str.substring(indexStr.length(), str.length());
		return trim ? subStr.trim() : subStr;
	}
}
