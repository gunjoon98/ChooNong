package com.ssafy.choonong.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CropResponse {
    @JsonProperty("crop_id")
    private int cropId; //작물 ID

    @JsonProperty("crop_name")
    private String cropName; //작물명

    @JsonProperty("area_rate")
    private double areaRate; //그래프상 면적비율(%)
}
