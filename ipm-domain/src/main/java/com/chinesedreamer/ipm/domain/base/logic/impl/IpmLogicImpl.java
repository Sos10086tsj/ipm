package com.chinesedreamer.ipm.domain.base.logic.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.chinesedreamer.ipm.domain.base.logic.IpmLogic;
import com.chinesedreamer.ipm.domain.base.model.AbstractEntity;
import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20158:13:24 AM
 * @version beta
 */

public class IpmLogicImpl<M extends AbstractEntity<?>, ID extends Serializable> implements IpmLogic<M, ID> {
	protected IpmRepository<M, ID> ipmRepository;

	@Autowired
    public void setBaseRepository(IpmRepository<M, ID> baseRepository) {
        this.ipmRepository = baseRepository;
    }

    @Override
    public M save(M m) {
        return ipmRepository.save(m);
    }

    @Override
    public M update(M m) {
        return ipmRepository.save(m);
    }

    @Override
    public void delete(ID id) {
        ipmRepository.delete(id);
    }

    @Override
    public void delete(M m) {
        ipmRepository.delete(m);
    }

    @Override
    public M findOne(ID id) {
        return ipmRepository.findOne(id);
    }

    @Override
    public boolean exists(ID id) {
        return ipmRepository.exists(id);
    }

    @Override
    public long count() {
        return ipmRepository.count();
    }

	@Override
	public Specification<M> getQuerySpecification(M entity, Root<M> root,CriteriaBuilder cb) {
		Specification<M> condition = new Specification<M>() {
			@Override
			public Predicate toPredicate(Root<M> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> pds = new ArrayList<Predicate>();
				return cb.and(pds.toArray(new Predicate[] {}));
			}
		};
		return condition;
	}
}
