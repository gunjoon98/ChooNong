package com.ssafy.choonong.api;


import com.ssafy.choonong.entity.RegionEntity;
import com.ssafy.choonong.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// @RestController 어노테이션은 이 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냅니다.
@RequestMapping("/api/v1/region")// @RequestMapping 어노테이션은 이 컨트롤러의 모든 메서드에 적용되는 요청 경로를 지정합니다.
//@RequiredArgsConstructor //클래스의 모든 final 필드 또는 @NonNull으로 지정된 필드에 대한 생성자를 생성
//@RequiredArgsConstructor 어노테이션을 사용하면 final로 선언된 모든 필드에 대해 생성자를 자동으로 생성
//별도로 @Autowired를 사용하지 않아도 스프링이 의존성을 주입이 가능.
public class RegionController {
    private final RegionService regionService;


    @Autowired //생성자를 주입함(오류가 발생해서 RequiredArgsConstructor 사용하지 않고 직접 작성했습니다.)
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    // @GetMapping 어노테이션은 이 메서드가 GET 요청을 처리함을 나타냅니다.
    // @PathVariable 어노테이션은 요청 경로의 일부를 메서드 매개변수로 바인딩합니다.
    @GetMapping("/{regionId}")
    public ResponseEntity<RegionEntity> getRegionById(@PathVariable int regionId) {
        // regionService를 사용하여 regionId에 해당하는 RegionEntity를 가져옵니다.
        RegionEntity region = regionService.getRegionById(regionId);
        // ResponseEntity.ok 메서드는 HTTP 상태 코드 200(OK)와 함께 응답 본문을 반환합니다.
        return ResponseEntity.ok(region);
    }

    // 이 메서드는 지역 정보와 함께 작물 및 정책 정보도 반환합니다.
    @GetMapping("/{regionId}/details")
    public ResponseEntity<RegionEntity> getRegionWithCropsAndPoliciesById(@PathVariable int regionId) {
        RegionEntity region = regionService.getRegionWithCropsAndPoliciesById(regionId);
        return ResponseEntity.ok(region);
    }
}


