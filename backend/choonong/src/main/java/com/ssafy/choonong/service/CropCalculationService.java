package com.ssafy.choonong.service;

import com.ssafy.choonong.dto.Response.CropCalculationResponse;
import com.ssafy.choonong.entity.CropCalculationEntity;
import com.ssafy.choonong.repository.CropCalculationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropCalculationService {
    private final CropCalculationRepository cropcalculationrepository;

    public List<CropCalculationResponse> getAllCropCalculations() {
        List<CropCalculationEntity> cropCalculations = cropcalculationrepository.findAll();
        return cropCalculations.stream()
                .map(entity -> CropCalculationResponse.builder()
                        .cropName(entity.getCropName())
                        .administrationFee(entity.getAdministrationFee())
                        .sales(entity.getSales())
                        .profitRate(entity.getProfitRate())
                        .build()).toList();
    }
}
