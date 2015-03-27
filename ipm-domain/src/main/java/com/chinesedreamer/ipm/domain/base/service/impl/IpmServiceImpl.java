package com.chinesedreamer.ipm.domain.base.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinesedreamer.ipm.domain.base.logic.IpmLogic;
import com.chinesedreamer.ipm.domain.base.model.AbstractEntity;
import com.chinesedreamer.ipm.domain.base.service.IpmService;


/**
 * Description: 
 * @author Paris
 * @date Mar 26, 20155:20:22 PM
 * @version beta
 */
public abstract class IpmServiceImpl<M extends AbstractEntity<?>, ID extends Serializable> implements IpmService<M, ID>{
	protected IpmLogic<M, ID> ipmLogic;

	@Autowired
	public void setBaseLogic(IpmLogic<M, ID> ipmLogic) {
		this.ipmLogic = ipmLogic;
	}

	@Override
	public M save(M m) {
		return ipmLogic.save(m);
	}

	@Override
	public M update(M m) {
		return ipmLogic.save(m);
	}

	@Override
	public void delete(ID id) {
		ipmLogic.delete(id);
	}

	@Override
	public void delete(M m) {
		ipmLogic.delete(m);
	}

	@Override
	public M findOne(ID id) {
		return ipmLogic.findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return ipmLogic.exists(id);
	}

	@Override
	public long count() {
		return ipmLogic.count();
	}
}
