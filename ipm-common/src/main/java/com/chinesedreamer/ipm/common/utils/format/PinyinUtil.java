package com.chinesedreamer.ipm.common.utils.format;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PinyinUtil {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(PinyinUtil.class);
	
	public static String getChinesePinyin(String chinese) {
		char[] t = chinese.toCharArray();
		String[] t2 = new String[t.length];
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		StringBuffer buffer = new StringBuffer();
		try {
			for (int i = 0; i < t.length; i++) {
				if (Character.toString(t[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t[i], format);
					buffer.append(t2[0]);
				}else {
					buffer.append(Character.toString(t[i]));
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			LOGGER.error("bad chinese.", e);
		}
		return buffer.toString();
	}
	
	public static String getChineseFirstLetter(String chinese){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < chinese.length(); i++) {
			char word = chinese.charAt(i);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (null != pinyinArray) {
				buffer.append(pinyinArray[0].charAt(0));
			}else {
				buffer.append(word);
			}
		}
		return buffer.toString();
	}
}
