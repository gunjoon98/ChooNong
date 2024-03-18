////package com.ssafy.choonong.api;
////
////
////import com.ssafy.choonong.entity.RecommendationEntity;
////import com.ssafy.choonong.service.RecommendationService;
////import lombok.RequiredArgsConstructor;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.PathVariable;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RestController;
////
////@RestController
////@RequestMapping("/api/v1/recommend")
////@RequiredArgsConstructor //클래스의 모든 final 필드 또는 @NonNull으로 지정된 필드에 대한 생성자를 생성
////public class RecommendationController {
////    private final RecommendationService recommendationService;
////
////    @GetMapping("/{regionId}")
////    public RecommendationEntity getRecommendationById(@PathVariable int regionId) {
////        return recommendationService.getRecommendationById(regionId);
////    }
////}
//=======
//package com.ssafy.choonong.api;
//
//
//import com.ssafy.choonong.entity.RecommendationEntity;
//import com.ssafy.choonong.service.RecommendationService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/recommend")
//@RequiredArgsConstructor //클래스의 모든 final 필드 또는 @NonNull으로 지정된 필드에 대한 생성자를 생성
//public class RecommendationController {
//    private final RecommendationService recommendationService;
//
//    @GetMapping("/{regionId}")
//    public RecommendationEntity getRecommendationById(@PathVariable int regionId) {
//        return recommendationService.getRecommendationById(regionId);
//    }
//}

