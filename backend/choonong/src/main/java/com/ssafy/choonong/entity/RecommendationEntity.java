package com.ssafy.choonong.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "recommendation")
@Data
public class RecommendationEntity { // 귀농지 추천 엔티티


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer regionId; //지역 아이디

    @Column(length = 16)
    String regionName; //지역명

    @Column(length = 16)
    String province; //도 이름 (예: 충청남도)

    @Column(length = 512)
    String imgUrl; //imgUrl

    @Column(length = 512)
    String homepageUrl; //homepageURL




}
