package com.ssafy.choonong.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CropEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cropId; //작물 ID

    @Column(length = 16)
    private String cropName; //작물명

    @Column
    private double areaRate; //그래프상 면적비율(%)
}
