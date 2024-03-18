package com.ssafy.choonong.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "region")
@Data
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer regionId; //지역 아이디

    @Column(length = 32)
    private String regionName; // 지역명

    @Column
    private int household; //세대수

    @Column(length = 32)
    private String province; //도 이름 (예: 충청남도)

    @Column
    private int returners; //귀농인 수

    @Column
    private int area; // 행정면적

    @Column(length = 512)
    private String imgUrl; //이미지URL

    @Column(length = 512)
    private String homepageUrl; //홈페이지URL

    @OneToMany
    private List<CropEntity> cropList;

    @OneToMany
    private List<PolicyEntity> policyList;

}
