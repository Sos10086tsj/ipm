package com.chinesedreamer.ipm.domain.base.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinesedreamer.ipm.domain.base.model.AbstractEntity;
import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.base.service.IpmService;


/**
 * Description: 
 * @author Paris
 * @date Mar 26, 20155:20:22 PM
 * @version beta
 */
public abstract class IpmServiceImpl<M extends AbstractEntity<?>, ID extends Serializable> implements IpmService<M, ID>{
	protected IpmRepository<M, ID> repository;

	@Autowired
	public void setBaseLogic(IpmRepository<M, ID> repository) {
		this.repository = repository;
	}

	@Override
	public M save(M m) {
		return repository.save(m);
	}

	@Override
	public M update(M m) {
		return repository.save(m);
	}

	@Override
	public void delete(ID id) {
		repository.delete(id);
	}

	@Override
	public void delete(M m) {
		repository.delete(m);
	}

	@Override
	public M findOne(ID id) {
		return repository.findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return repository.exists(id);
	}

	@Override
	public long count() {
		return repository.count();
	}
}
