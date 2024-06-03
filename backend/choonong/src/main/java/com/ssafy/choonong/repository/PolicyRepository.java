package com.ssafy.choonong.repository;

import com.ssafy.choonong.entity.PolicyEntity;
import com.ssafy.choonong.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<PolicyEntity, Integer> {
    List<PolicyEntity> findByRegion(RegionEntity regionEntity);
}
