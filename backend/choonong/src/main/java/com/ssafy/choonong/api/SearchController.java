package com.ssafy.choonong.api;


import com.ssafy.choonong.dto.SearchDTO;
import com.ssafy.choonong.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor //  클래스의 모든 final 필드나 @NonNull 어노테이션이 붙은 필드를 파라미터로 받는 생성자를 자동으로 생성
public class SearchController {
    private final SearchService searchService; // SearchService 객체를 의존성 주입합니다.


    /** regionId로 해당하는 귀농지 정보를 반환합니다. */
    @GetMapping("/{regionId}")
    public ResponseEntity<List<SearchDTO>> getSearchResultsByRegionId(@PathVariable int regionId) {
        List<SearchDTO> searchResults = searchService.getSearchResultsByRegionId(regionId);
        return ResponseEntity.ok(searchResults);
    }
}
