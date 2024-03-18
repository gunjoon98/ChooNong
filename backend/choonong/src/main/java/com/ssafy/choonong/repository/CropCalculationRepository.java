package com.ssafy.choonong.repository;

import com.ssafy.choonong.entity.CropCalculationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CropCalculationRepository extends JpaRepository<CropCalculationEntity, Integer> {
//계산기용으로 쓰일 예정입니다. 전체 작물 조회




}
