package com.chinesedreamer.ipm.common.utils.security;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class EncryptionUtil {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(EncryptionUtil.class);
	
	public static String md5L32(String unencryptedText){
		if (StringUtils.isEmpty(unencryptedText)) {
			LOGGER.info("unencryptedText is null.");
			return unencryptedText;
		}
		
		String ciphertext = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(unencryptedText.getBytes("UTF-8"));
			StringBuffer buffer = new StringBuffer();
			for (byte b : bytes) {
				int bt = b & 0xff;
				if (bt < 16) {
					buffer.append(0);
				}
				buffer.append(Integer.toHexString(bt));
				ciphertext = buffer.toString();
			}
			LOGGER.info("encrypt string {} to {};",unencryptedText,ciphertext);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return ciphertext;
	}
}
