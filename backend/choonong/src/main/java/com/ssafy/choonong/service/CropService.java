//package com.ssafy.choonong.service;
//
//
//import com.ssafy.choonong.dto.CropDTO;
//import com.ssafy.choonong.entity.CropEntity;
//import com.ssafy.choonong.repository.cropRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class cropService {
//    private final cropRepository croprepository;
//
//    public List<CropDTO> getCropsByRegionId(Integer regionId) {
//        // 특정 지역에서 잘 자라는 작물을 조회합니다.
//        List<CropEntity> crops = croprepository.findByRegionId(regionId);
//
//        // 작물 엔티티를 DTO 형태로 변환하여 반환합니다.
//        return crops.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    private CropDTO convertToDto(CropEntity cropEntity) {
//        return CropDTO.builder()
//                .cropId(cropEntity.getCropId())
//                .cropName(cropEntity.getCropName())
//                .areaRate(cropEntity.getAreaRate())
//                .regionId(cropEntity.getRegionId())
//                .build();
//    }
//
//
//
//
//}
