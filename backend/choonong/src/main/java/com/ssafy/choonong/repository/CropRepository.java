package com.ssafy.choonong.repository;

import com.ssafy.choonong.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<CropEntity, Integer> {
}
