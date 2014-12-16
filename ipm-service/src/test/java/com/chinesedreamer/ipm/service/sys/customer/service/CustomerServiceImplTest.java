package com.chinesedreamer.ipm.service.sys.customer.service;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.ipm.common.base.SpringTest;
import com.chinesedreamer.ipm.domain.sys.customer.constant.CustomerStatus;
import com.chinesedreamer.ipm.domain.sys.customer.entity.Customer;

public class CustomerServiceImplTest extends SpringTest{
	@Resource
	private CustomerService service;
	
	@Test
	public void testGetCustomerLong() {
		Customer customer = this.service.getCustomer(1l);
		assertNotNull(customer);
		System.out.println("发现客户：" + customer.getName());
	}

	@Test
	public void testGetCustomerString() {
		Customer customer = this.service.getCustomer("SYS_IPM");
		assertNotNull(customer);
		System.out.println("发现客户：" + customer.getName());
	}

	@Test
	public void testUpdateCustomerInfo() {
		Customer customer = this.service.getCustomer("SYS_IPM");
		assertNotNull(customer);
		customer.setLastUpdateDate(new Date());
		Customer updatedCustomer = this.service.updateCustomerInfo(customer);
		System.out.println("发现客户：" + updatedCustomer.getName() + " 最后修改时间：" + updatedCustomer.getLastUpdateDate());
	}

	@Test
	public void testUpdateCustomerStatus() {
		Customer customer = this.service.getCustomer("SYS_IPM");
		assertNotNull(customer);
		Customer updatedCustomer = this.service.updateCustomerStatus(customer, CustomerStatus.INACTIVE);
		System.out.println("发现客户：" + updatedCustomer.getName() + " 状态：" + updatedCustomer.getStatus());
	}

	@Test
	public void testSaveCustomer() {
		Customer customer = new Customer();
		customer.setCustCode("TEST_CRR");
		customer.setName("Test");
		Customer savedCustomer = this.service.saveCustomer(customer);
		assertNotNull(savedCustomer);
		System.out.println("保存客户：" + savedCustomer.getName() + " ID：" + savedCustomer.getId());
	}

	@Test
	public void testDeleteCustomer() {
		Customer customer = new Customer();
		customer.setCustCode("TEST_CRR");
		customer.setName("Test");
		Customer savedCustomer = this.service.saveCustomer(customer);
		assertNotNull(savedCustomer);
		System.out.println("保存客户：" + savedCustomer.getName() + " ID：" + savedCustomer.getId());
		
		this.service.deleteCustomer(savedCustomer);
		
		System.out.println("删除成功");
	}

}
