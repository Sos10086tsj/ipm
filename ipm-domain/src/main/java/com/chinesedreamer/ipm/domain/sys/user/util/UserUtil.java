package com.chinesedreamer.ipm.domain.sys.user.util;

import java.util.Random;

import org.apache.commons.codec.binary.Base64;

public class UserUtil {
	public static String generateSalt() {
		Random random = new Random();
		byte[] bytes = new byte[1024];
		random.nextBytes(bytes);
		return new String(Base64.encodeBase64(bytes));
	}
}
