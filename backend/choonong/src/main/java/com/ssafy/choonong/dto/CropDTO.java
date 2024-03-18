package com.ssafy.choonong.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CropDTO {
    private int cropId; // 작물 ID
    private String cropName; // 작물명
    private double areaRate; // 그래프상 면적 비율(%)
    private Integer regionId; // 작물이 속한 지역의 ID
}
