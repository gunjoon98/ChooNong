package com.ssafy.choonong.dto.Response;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@Builder
@AllArgsConstructor
public class CropCalculationResponse {
    @JsonProperty("crop_id")
    private Integer cropId; // 작물 ID

    @JsonProperty("crop_name")
    private String cropName; // 작물 이름

    @JsonProperty("administration_fee")
    private Integer administrationFee; // 평당 경영비

    @JsonProperty("image_url")
    private String imageUrl; // 이미지 URL

    private BigInteger sales; // 평당 매출액

    @JsonProperty("profit_rate")
    private Double profitRate; // 수익률
}
