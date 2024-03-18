package com.ssafy.choonong.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PolicyResponse {
    @JsonProperty("policy_id")
    private int policyId; // 정책ID

    @JsonProperty("business_name")
    private String businessName; // 세부사업명

    @JsonProperty("target_eligibility")
    private String targetEligibility; // 지원대상 및 자격요건

    @JsonProperty("business_content")
    private String businessContent; // 사업 내용

    private String conditions; // 지원조건
    private String evidence; // 증빙자료
    private String reception; // 접수 및 문의처
}
