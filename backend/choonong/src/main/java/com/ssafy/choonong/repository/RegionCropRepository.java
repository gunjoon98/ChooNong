package com.ssafy.choonong.repository;

import com.ssafy.choonong.entity.RegionCropEntity;
import com.ssafy.choonong.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionCropRepository extends JpaRepository<RegionCropEntity, Integer> {
    @Query("select rc from RegionCropEntity rc inner join fetch rc.region inner join fetch rc.crop where rc.region.regionId = :regionId")
    List<RegionCropEntity> findByRegion(int regionId);
}