package com.chinesedreamer.ipm.domain.sys.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chinesedreamer.ipm.domain.sys.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	public Customer findByCustCode(String custCode);
}
