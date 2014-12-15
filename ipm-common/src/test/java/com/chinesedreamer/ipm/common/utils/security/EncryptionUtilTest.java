package com.chinesedreamer.ipm.common.utils.security;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncryptionUtilTest {

	@Test
	public void testMd5L32() {
		String testStr = "1234";
		String rstStr = EncryptionUtil.md5L32(testStr);
		assertNotNull(rstStr);
		System.out.println("rstStr:" + rstStr);
	}

}
