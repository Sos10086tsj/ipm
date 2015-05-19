package com.chinesedreamer.ipm.common.utils.format;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String dateFormat(Calendar calendar) throws Exception {
		if (null == calendar)
			return null;
		String date = null;
		try {
			String pattern = DATE_FORMAT;
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.format(calendar.getTime());
		} catch (Exception e) {
			throw e;
		}
		return date == null ? "" : date;
	}
	
	public static String dateFormat(Calendar calendar, String dateFormat) throws Exception {
		if (null == calendar)
			return null;
		String date = null;
		try {
			String pattern = dateFormat;
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.format(calendar.getTime());
		} catch (Exception e) {
			throw e;
		}
		return date == null ? "" : date;
	} 
}
