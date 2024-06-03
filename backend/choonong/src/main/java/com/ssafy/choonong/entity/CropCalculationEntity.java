package com.ssafy.choonong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    @Column(name = "crop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cropId; //작물 ID

    @Column(name = "crop_name")
    private String cropName; //작물 이름

    @Column(name = "administration_fee")
    private Integer administrationFee; //평당경영비

    @Column
    private BigInteger sales; //평당매출액

    @Column(name = "profit_rate")
    private Double profitRate; //수익률

    @Column(name = "image_url")
    private String imageUrl;
}
