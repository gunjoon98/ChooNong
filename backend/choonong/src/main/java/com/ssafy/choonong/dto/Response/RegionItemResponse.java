package com.ssafy.choonong.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RegionItemResponse {
    @JsonProperty("region_id")
    private Integer regionId; // 지역 아이디

    @JsonProperty("region_name")
    private String regionName; // 지역명
}