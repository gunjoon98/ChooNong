package com.ssafy.choonong.service;


import com.ssafy.choonong.entity.PolicyEntity;

import com.ssafy.choonong.repository.PolicyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;

//    /** 모든 귀농정책 정보를 조회하여 DTO 형태로 반환 (필요없을 것 같은데 넣었습니다.) */
//    public List<policyDto> getAllPolicies() {
//        // 데이터베이스에서 모든 귀농정책 정보를 가져옵니다.
//        List<policyEntity> policies = policyrepository.findAll();
//
//        // 귀농정책 정보를 DTO 형태로 변환하여 반환합니다.
//        return policies.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }

    /** 특정 지역의 모든 귀농정책 정보를 조회하여 DTO 형태로 반환 */
    public PolicyEntity getPolicyById(int policyId) {
        return policyRepository.findByPolicyId(policyId);
    }



}
