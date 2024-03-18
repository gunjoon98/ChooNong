package com.ssafy.choonong.service;

import com.ssafy.choonong.dto.Response.CropResponse;
import com.ssafy.choonong.dto.Response.PolicyResponse;
import com.ssafy.choonong.dto.Response.RegionItemResponse;
import com.ssafy.choonong.dto.Response.RegionResponse;
import com.ssafy.choonong.entity.RegionEntity;
import com.ssafy.choonong.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public List<RegionItemResponse> getResionList() {
        return regionRepository.findAll().stream()
                .map(regionEntity -> RegionItemResponse.builder()
                        .regionId(regionEntity.getRegionId())
                        .regionName(regionEntity.getRegionName())
                        .build()).toList();
    }

    /* regionId 지역 정보, 작물, 정책 정보 가져옴 */
    public RegionResponse getRegionWithCropsAndPoliciesById(int regionId) {
        RegionEntity regionEntity =
                regionRepository.findRegionEntityWithCropsAndPoliciesByRegionId(regionId)
                        .orElseThrow();

        List<CropResponse> cropResponseList = regionEntity.getCropList().stream()
                .map(cropEntity -> CropResponse.builder()
                        .cropId(cropEntity.getCropId())
                        .cropName(cropEntity.getCropName())
                        .areaRate(cropEntity.getAreaRate())
                        .build()).toList();

        List<PolicyResponse> policyResponseList = regionEntity.getPolicyList().stream()
                .map(policyEntity -> PolicyResponse.builder()
                        .policyId(policyEntity.getPolicyId())
                        .businessName(policyEntity.getBusinessName())
                        .targetEligibility(policyEntity.getTargetEligibility())
                        .businessContent(policyEntity.getBusinessContent())
                        .conditions(policyEntity.getConditions())
                        .evidence(policyEntity.getEvidence())
                        .reception(policyEntity.getReception())
                        .build()).toList();

        return RegionResponse.builder()
                .regionId(regionEntity.getRegionId())
                .regionName(regionEntity.getRegionName())
                .household(regionEntity.getHousehold())
                .province(regionEntity.getProvince())
                .returners(regionEntity.getReturners())
                .area(regionEntity.getArea())
                .homepageUrl(regionEntity.getHomepageUrl())
                .imgUrl(regionEntity.getImgUrl())
                .cropList(cropResponseList)
                .policyList(policyResponseList)
                .build();
    }
}







