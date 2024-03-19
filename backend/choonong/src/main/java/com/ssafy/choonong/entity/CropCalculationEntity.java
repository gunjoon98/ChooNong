package com.ssafy.choonong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "crop_calculation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CropCalculationEntity {
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
