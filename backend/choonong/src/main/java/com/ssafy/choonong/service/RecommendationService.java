package com.ssafy.choonong.service;


import com.ssafy.choonong.entity.RecommendationEntity;
import com.ssafy.choonong.entity.RegionEntity;
import com.ssafy.choonong.repository.RecommendationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    // 특정 추천 지역의 정보를 조회하는 메서드
    public RecommendationEntity getRecommendationById(int regionId) {
        return recommendationRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 지역을 찾을 수 없습니다."));
    }
}
