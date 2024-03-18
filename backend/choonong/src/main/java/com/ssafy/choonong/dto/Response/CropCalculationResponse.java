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
    private int cropId; // 작물 ID

    @JsonProperty("crop_name")
    private String cropName; // 작물 이름

    @JsonProperty("admin")
    private int administrationFee; // 평당 경영비
    private BigInteger sales; // 평당 매출액
    private double profitRate; // 수익률
}
