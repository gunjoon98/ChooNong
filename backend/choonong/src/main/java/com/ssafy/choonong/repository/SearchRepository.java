package com.ssafy.choonong.repository;

import com.ssafy.choonong.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchRepository extends JpaRepository<SearchEntity, Integer> {
    List<SearchEntity> findByRegionId(int regionId);
}
