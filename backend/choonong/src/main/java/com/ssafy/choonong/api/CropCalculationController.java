package com.ssafy.choonong.api;

import com.ssafy.choonong.dto.CropCalculationDTO;
import com.ssafy.choonong.service.CropCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api1/cropcalculation")
@AllArgsConstructor
public class CropCalculationController {//계산기용으로 쓰일 예정입니다. 전체 작물 조회.
    private final CropCalculationService cropcalculationservice;



    //모든 작물 정보를 조회한 후 cropName, administrationFee, sales, profitRate (4가지)를 반환
    @GetMapping
    public List<CropCalculationDTO> getAllCropCalculations() {
        return cropcalculationservice.getAllCropCalculations();
    }
}
