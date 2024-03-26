package com.ssafy.choonong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "region")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer regionId; //지역 아이디

    @Column(name = "region_name")
    private String regionName; // 지역명

    @Column
    private int household; //세대수

    @Column(length = 32)
    private String province; //도 이름 (예: 충청남도)

    @Column
    private int returners; //귀농인 수

    @Column
    private int area; // 행정면적

    @Column(name="image_url")
    private String imageUrl; //이미지URL
}
