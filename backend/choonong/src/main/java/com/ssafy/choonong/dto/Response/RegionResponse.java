package com.ssafy.choonong.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.choonong.entity.CropEntity;
import com.ssafy.choonong.entity.PolicyEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RegionResponse {
    @JsonProperty("region_id")
    private Integer regionId; // 지역 아이디

    @JsonProperty("region_name")
    private String regionName; // 지역명

    @JsonProperty("house_hold")
    private int household; // 세대수

    private String province; // 도 이름
    private int returners; // 귀농인 수
    private int area; // 행정면적

    @JsonProperty("image_url")
    private String imageUrl; // 이미지 URL

    @JsonProperty("child_care_facilities")
    private Double childCareFacilities;

    @JsonProperty("average_accesstime_educational_facilities")
    private Double averageAccesstimeEducationalFacilities;

    @JsonProperty("average_price_farmland")
    private Double averagePriceFarmland;

    @JsonProperty("average_housing_price")
    private Double averageHousingPrice;

    @JsonProperty("average_accesstime_amenities")
    private Double averageAccesstimeAmenities;

    @JsonProperty("population_density")
    private Double populationDensity;

    @JsonProperty("dust_concentration")
    private Double dustConcentration;

    @JsonProperty("water_quality")
    private Double waterQuality;

    @JsonProperty("crop_list")
    private List<CropResponse> cropList; //해당하는 지역에 속한 작물 목록

    @JsonProperty("policy_list")
    private List<PolicyResponse> policyList; //해당하는 지역에 속한 정책 목록
}
