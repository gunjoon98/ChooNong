package com.ssafy.choonong.api;


import com.ssafy.choonong.entity.PolicyEntity;
import com.ssafy.choonong.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {

    private final PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    // 특정 지역의 귀농 정책을 조회하는 API 엔드포인트
    @GetMapping("/{policyId}")
    public PolicyEntity getPolicyById(@PathVariable int policyId) {
        return policyService.getPolicyById(policyId);
    }

//    // 모든 귀농 정책을 조회하는 API, 혹시 몰라서 넣었습니다.
//    @GetMapping
//    public ResponseEntity<List<policyDto>> getAllPolicies() {
//        List<policyDto> policies = service.getAllPolicies();
//        if (!policies.isEmpty()) {
//            return ResponseEntity.ok(policies); // 귀농 정책이 존재할 경우 200 OK 응답 반환
//        } else {
//            return ResponseEntity.notFound().build(); // 귀농 정책이 존재하지 않을 경우 404 Not Found 응답 반환
//        }
//    }


}
