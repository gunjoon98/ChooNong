package com.ssafy.choonong.service;

import com.ssafy.choonong.dto.Response.CropResponse;
import com.ssafy.choonong.dto.Response.PolicyResponse;
import com.ssafy.choonong.dto.Response.RegionItemResponse;
import com.ssafy.choonong.dto.Response.RegionResponse;
import com.ssafy.choonong.entity.RegionEntity;
import com.ssafy.choonong.repository.PolicyRepository;
import com.ssafy.choonong.repository.RegionCropRepository;
import com.ssafy.choonong.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final PolicyRepository policyRepository;
    private final RegionCropRepository regionCropRepository;

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
                regionRepository.findById(regionId)
                        .orElseThrow();

        List<CropResponse> cropResponseList = regionCropRepository.findByRegion(regionEntity.getRegionId()).stream()
                .map(regionCropEntity -> CropResponse.builder()
                        .cropId(regionCropEntity.getCrop().getCropId())
                        .cropName(regionCropEntity.getCrop().getCropName())
                        .areaRate(regionCropEntity.getAreaRate())
                        .build()).toList();

        List<PolicyResponse> policyResponseList = policyRepository.findByRegion(regionEntity).stream()
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
                .imageUrl(regionEntity.getImageUrl())
                .childCareFacilities(regionEntity.getChildCareFacilities())
                .averageAccesstimeEducationalFacilities(regionEntity.getAverageAccesstimeEducationalFacilities())
                .averagePriceFarmland(regionEntity.getAveragePriceFarmland())
                .averageHousingPrice(regionEntity.getAverageHousingPrice())
                .averageAccesstimeAmenities(regionEntity.getAverageAccesstimeAmenities())
                .populationDensity(regionEntity.getPopulationDensity())
                .dustConcentration(regionEntity.getDustConcentration())
                .waterQuality(regionEntity.getWaterQuality())
                .cropList(cropResponseList)
                .policyList(policyResponseList)
                .build();
    }
}







