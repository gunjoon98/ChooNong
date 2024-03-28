package com.ssafy.choonong.repository;

import com.ssafy.choonong.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
    // 클러스터 조건에 맞는 목록 찾아오는 메서드
    List<RegionEntity> findAllByEducationClusterAndGroundClusterAndResidentClusterAndEnvCluster(int edu, int ground, int resident, int env);
}
