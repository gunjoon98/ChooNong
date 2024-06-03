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
    @Column(name = "region_crop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionCropId;

    @Column(name = "area_rate")
    private double areaRate; //그래프상 면적비율(%)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private RegionEntity region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    private CropEntity crop;
}
