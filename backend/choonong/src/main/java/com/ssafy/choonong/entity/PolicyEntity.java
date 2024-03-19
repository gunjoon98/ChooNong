package com.ssafy.choonong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "policy")
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private RegionEntity region;
}
