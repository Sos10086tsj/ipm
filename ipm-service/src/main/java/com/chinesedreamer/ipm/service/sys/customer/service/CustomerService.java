//package com.chinesedreamer.ipm.service.sys.customer.service;
//
//import com.chinesedreamer.ipm.domain.sys.customer.constant.CustomerStatus;
//import com.chinesedreamer.ipm.domain.sys.customer.entity.Customer;
//
//public interface CustomerService {
//	/**
//	 * 根据id查找customer
//	 * @param id
//	 * @return customer entity
//	 */
//	public Customer getCustomer(Long id);
//	/**
//	 * 根据 customer code 查找customer
//	 * @param custCode
//	 * @return customer entity
//	 */
//	public Customer getCustomer(String custCode);
//	/**
//	 * 修改customer基本信息
//	 * @param customer
//	 * @return
//	 */
//	public Customer updateCustomerInfo(Customer customer);
//	/**
//	 * 更新客户状态
//	 * @param customer
//	 * @param status
//	 * @return
//	 */
//	public Customer updateCustomerStatus(Customer customer, CustomerStatus status);
//	
//	/**
//	 * 保存客户
//	 * @param customer
//	 * @return
//	 */
//	public Customer saveCustomer(Customer customer);
//	/**
//	 * 删除客户
//	 * @param customer
//	 * @return
//	 */
//	public void deleteCustomer(Customer customer);
//}
