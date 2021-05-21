package com.mt.mindjpa.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mt.mindjpa.model.Track;

@Repository
public interface TrackRepository extends CrudRepository<Track, Integer>{

}
