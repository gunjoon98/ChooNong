package com.ssafy.choonong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "region_crop")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegionCropEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionCropId;

    @Column
    private double areaRate; //그래프상 면적비율(%)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regionId")
    private RegionEntity region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cropId")
    private CropEntity crop;
}
