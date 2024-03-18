package com.ssafy.choonong.repository;

import com.ssafy.choonong.dto.RegionDTO;
import com.ssafy.choonong.entity.CropEntity;
import com.ssafy.choonong.entity.PolicyEntity;
import com.ssafy.choonong.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
    //JpaRepository 인터페이스를 상속받아 JPA의 기본 CRUD 기능을 구현합니다.
//    첫 번째 매개변수는 엔티티 클래스 이름이며, 두 번째 매개변수는 엔티티의 기본 키(primary key)의 타입


    // RegionId에 해당하는 지역 정보와 속한 정책 및 작물 정보를 조회하는 메서드 (3가지 한꺼번에 조회)

//    @Query("SELECT r FROM RegionEntity r JOIN FETCH r.cropList c JOIN FETCH r.policyList p WHERE r.regionId = :regionId")
    RegionEntity findRegionEntityWithCropsAndPoliciesByRegionId(int regionId);
}
