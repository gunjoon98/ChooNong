package com.ssafy.choonong.api;

import com.ssafy.choonong.dto.Response.RegionItemResponse;
import com.ssafy.choonong.dto.Response.RegionResponse;
import com.ssafy.choonong.dto.request.SurveyRequest;
import com.ssafy.choonong.entity.RegionEntity;
import com.ssafy.choonong.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionItemResponse>> getRegionList() {
        return ResponseEntity.ok(regionService.getResionList());
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<RegionResponse> getRegionWithCropsAndPoliciesById(@PathVariable int regionId) {
        RegionResponse regionResponse = regionService.getRegionWithCropsAndPoliciesById(regionId);
        return ResponseEntity.ok(regionResponse);
    }


    /**
     * @author 김용준
     * @param edu 교육/보육시설 클러스터
     * @param ground 토지가격 클러스터
     * @param resident 주거환경 클러스터
     * @param env 자연환경 클러스터
     * @return 해당 조합 클러스터를 가진 지역 리스트
     */
    @GetMapping("/survey")
    public ResponseEntity<List<RegionItemResponse>> getRegionListBySurvey(@RequestParam("edu") int edu,
                                                                          @RequestParam("ground") int ground,
                                                                          @RequestParam("resident") int resident,
                                                                          @RequestParam("env") int env) {

        return ResponseEntity.ok(regionService.getRegionListBySurvey(new SurveyRequest(edu, ground, resident, env)));
    }
}


