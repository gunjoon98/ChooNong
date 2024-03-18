package com.ssafy.choonong.service;


import com.ssafy.choonong.entity.RegionEntity;
import com.ssafy.choonong.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegionService { //DTO를 사용하지 않고 엔티티를 그대로 반환

    private final RegionRepository regionRepository;

    /** regionID에 따라 지역정보만을 가져옵니다.*/
    public RegionEntity getRegionById(int regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 지역을 찾을 수 없습니다."));
    }

    /** 주어진 regionId에 해당하는 지역 정보와 그 지역의 작물 및 정책 정보를 한 번에 가져옵니다. */
    public RegionEntity getRegionWithCropsAndPoliciesById(int regionId) {
        return regionRepository.findRegionEntityWithCropsAndPoliciesByRegionId(regionId);
    }

    //만약 지역 정보만 필요한 경우에는 getRegionById 메서드를 사용하고,
    // 지역 정보와 함께 작물 및 정책 정보도 필요한 경우에는 getRegionWithCropsAndPoliciesById 메서드를 사용하면 됩니다.

}







