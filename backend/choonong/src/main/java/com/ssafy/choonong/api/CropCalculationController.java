package com.ssafy.choonong.api;

import com.ssafy.choonong.dto.Response.CropCalculationResponse;
import com.ssafy.choonong.service.CropCalculationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cropcalculation")
@RequiredArgsConstructor
public class CropCalculationController {
    private final CropCalculationService cropcalculationservice;

    //모든 작물 정보를 조회한 후 cropName, administrationFee, sales, profitRate (4가지)를 반환
    @GetMapping
    public List<CropCalculationResponse> getAllCropCalculations() {
        return cropcalculationservice.getAllCropCalculations();
    }
}
