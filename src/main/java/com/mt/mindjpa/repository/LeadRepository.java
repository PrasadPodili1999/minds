package com.mt.mindjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mt.mindjpa.model.Lead;

@Repository
public interface LeadRepository extends CrudRepository<Lead, Integer>{

}
