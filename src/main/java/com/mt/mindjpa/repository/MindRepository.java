package com.mt.mindjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mt.mindjpa.model.MTMind;

@Repository
public interface MindRepository extends CrudRepository<MTMind, Integer>{

}
