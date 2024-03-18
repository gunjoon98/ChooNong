package com.ssafy.choonong.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
<<<<<<< HEAD
// 엔티티는 데이터베이스와 직접적으로 상호작용하는 모델 클래스.a
=======
// 엔티티는 데이터베이스와 직접적으로 상호작용하는 모델 클래스
>>>>>>> 63d51c452ac016c01a6b92aa147fb7995bb98881
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
