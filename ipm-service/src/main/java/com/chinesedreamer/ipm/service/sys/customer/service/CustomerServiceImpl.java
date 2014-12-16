package com.chinesedreamer.ipm.service.sys.customer.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.sys.customer.constant.CustomerStatus;
import com.chinesedreamer.ipm.domain.sys.customer.entity.Customer;
import com.chinesedreamer.ipm.domain.sys.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Resource
	private CustomerRepository repository;
	
	@Override
	public Customer getCustomer(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Customer getCustomer(String custCode) {
		return this.repository.findByCustCode(custCode);
	}

	@Override
	public Customer updateCustomerInfo(Customer customer) {
		return this.repository.saveAndFlush(customer);
	}

	@Override
	public Customer updateCustomerStatus(Customer customer,
			CustomerStatus status) {
		customer.setStatus(status);
		return this.repository.saveAndFlush(customer);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return this.repository.save(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		this.repository.delete(customer);
	}

}
