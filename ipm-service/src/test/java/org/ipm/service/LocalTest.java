package org.ipm.service;

import org.junit.Assert;
import org.junit.Test;

import com.chinesedreamer.ipm.domain.system.company.model.CompanyStatus;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20158:25:46 AM
 * @version beta
 */
public class LocalTest {
	@Test
	public void testEnum(){
		CompanyStatus status = CompanyStatus.valueOf("无效客户");
		Assert.assertNotNull(status);
		System.out.println(status.getInfo());
	}
}
