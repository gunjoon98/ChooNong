package com.ssafy.choonong.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PolicyDTO {
    private int policyId; // 정책ID
    private String businessName; // 세부사업명
    private String targetEligibility; // 지원대상 및 자격요건
    private String businessContent; // 사업 내용
    private String conditions; // 지원조건
    private String evidence; // 증빙자료
    private String reception; // 접수 및 문의처
    private Integer regionId; //지역ID
}
