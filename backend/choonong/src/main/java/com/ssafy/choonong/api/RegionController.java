package com.ssafy.choonong.api;

import com.ssafy.choonong.dto.Response.RegionItemResponse;
import com.ssafy.choonong.dto.Response.RegionResponse;
import com.ssafy.choonong.entity.RegionEntity;
import com.ssafy.choonong.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionItemResponse>> getRegionList() {
        return null;
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<RegionResponse> getRegionWithCropsAndPoliciesById(@PathVariable int regionId) {
        RegionResponse regionResponse = regionService.getRegionWithCropsAndPoliciesById(regionId);
        return ResponseEntity.ok(regionResponse);
    }
}


