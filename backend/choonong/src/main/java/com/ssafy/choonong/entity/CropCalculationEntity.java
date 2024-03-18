package com.ssafy.choonong.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
public class CropCalculationEntity { //계산기용으로 쓰일 예정입니다. 전체 작물 조회

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cropId; //작물 ID

    @Column(length = 16)
    private String cropName; //작물 이름

    @Column
    private int administrationFee; //평당경영비

    @Column
    private BigInteger sales; //평당매출액

    @Column
    private double profitRate; //수익률
}
