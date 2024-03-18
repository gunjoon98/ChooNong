package com.ssafy.choonong.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SerachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer regionId; //지역 아이디

    @Column(length = 16)
    String regionName; //지역명

    @Column(length = 16)
    String province ; //도의 이름

    @Column(length = 512)
    String imgUrl; //이미지 파일 URL

    @Column(length = 512)
    String homepageUrl; //지자체 홈페이지 URL


}
