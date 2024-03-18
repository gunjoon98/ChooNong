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

@RestController
@RequestMapping("/api/v1/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping("/{regionId}")
    public ResponseEntity<RegionEntity> getRegionById(@PathVariable int regionId) {
        RegionEntity region = regionService.getRegionById(regionId);
        return ResponseEntity.ok(region);
    }

    @GetMapping("/{regionId}/details")
    public ResponseEntity<RegionEntity> getRegionWithCropsAndPoliciesById(@PathVariable int regionId) {
        RegionEntity region = regionService.getRegionWithCropsAndPoliciesById(regionId);
        return ResponseEntity.ok(region);
    }
}


