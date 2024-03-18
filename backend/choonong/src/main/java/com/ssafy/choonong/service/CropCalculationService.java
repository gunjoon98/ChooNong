package com.ssafy.choonong.service;


import com.ssafy.choonong.dto.CropCalculationDTO;
import com.ssafy.choonong.entity.CropCalculationEntity;
import com.ssafy.choonong.repository.CropCalculationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CropCalculationService {
    //계산기용으로 쓰일 예정입니다. 전체 작물 조회
    private final CropCalculationRepository cropcalculationrepository;

    /**
     * 모든 작물 계산 정보를 조회하고 cropName, administrationFee, sales, profitRate (4가지) 추출하여 반환합니다.
     */
    public List<CropCalculationDTO> getAllCropCalculations() {
        List<CropCalculationEntity> cropCalculations = cropcalculationrepository.findAll();
        return cropCalculations.stream()
                .map(entity -> CropCalculationDTO.builder()
                        .cropName(entity.getCropName()) //이름
                        .administrationFee(entity.getAdministrationFee()) //경영비
                        .sales(entity.getSales()) //매출액
                        .profitRate(entity.getProfitRate()) //수익률
                        .build())
                .collect(Collectors.toList());
    }



}
