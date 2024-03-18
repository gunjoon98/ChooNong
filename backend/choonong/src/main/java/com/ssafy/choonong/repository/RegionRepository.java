package com.ssafy.choonong.repository;

import com.ssafy.choonong.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
    Optional<RegionEntity> findRegionEntityWithCropsAndPoliciesByRegionId(int regionId);
}
