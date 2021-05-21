package com.mt.mindjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mt.mindjpa.model.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{

}
