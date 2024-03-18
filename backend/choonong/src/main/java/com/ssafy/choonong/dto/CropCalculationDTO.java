package com.ssafy.choonong.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class CropCalculationDTO {//계산기용으로 쓰일 예정입니다. 전체 작물 조회
    private int cropId; // 작물 ID
    private String cropName; // 작물 이름
    private int administrationFee; // 평당 경영비
    private BigInteger sales; // 평당 매출액
    private double profitRate; // 수익률
}
