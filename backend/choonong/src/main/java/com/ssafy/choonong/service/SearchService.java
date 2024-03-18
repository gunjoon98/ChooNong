package com.ssafy.choonong.service;


import com.ssafy.choonong.dto.SearchDTO;
import com.ssafy.choonong.entity.SearchEntity;
import com.ssafy.choonong.repository.SearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchService { // 귀농지 검색
    private SearchRepository searchRepository;

    /** 모든 검색 결과를 가져와서 DTO 리스트로 변환하여 반환합니다. */
    public List<SearchDTO> getSearchResultsByRegionId(int regionId) {
        List<SearchEntity> searchEntities = searchRepository.findByRegionId(regionId);
        return searchEntities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 검색 엔티티를 검색 DTO로 변환합니다.
    private SearchDTO mapToDTO(SearchEntity searchEntity) {
        return SearchDTO.builder()
                .regionId(searchEntity.getRegionId())
                .regionName(searchEntity.getRegionName())
                .province(searchEntity.getProvince())
                .imgUrl(searchEntity.getImgUrl())
                .homepageUrl(searchEntity.getHomepageUrl())
                .build();
    }
}
