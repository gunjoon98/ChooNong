package com.ssafy.choonong.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PolicyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int policyId; // 정책ID

    @Column(length = 512)
    private String businessName; // 세부사업명

    @Column(length = 512)
    private String targetEligibility; // 지원대상 및 자격요건

    @Column(length = 512)
    private String businessContent; // 사업 내용

    @Column(length = 512)
    private String conditions; // 지원조건

    @Column(length = 512)
    private String evidence; // 증빙자료

    @Column(length = 512)
    private String reception; // 접수 및 문의처

//    @ManyToOne
//    @JoinColumn(name = "region_id") // region_id를 외래키로 사용하여 관계 매핑
//    private RegionEntity region; // 지역과의 관계 매핑



}
