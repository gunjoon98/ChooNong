package com.ssafy.choonong.dto;


import com.ssafy.choonong.entity.CropEntity;
import com.ssafy.choonong.entity.PolicyEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

//DTO는 데이터 전송을 위한 모델 클래스
@Builder
@Data // Getter,Setter,toString,equals,hashCode 메서드를 자동으로 생성.
public class RegionDTO {
    private Integer regionId; // 지역 아이디
    private String regionName; // 지역명
    private int household; // 세대수
    private String province; // 도 이름
    private int returners; // 귀농인 수
    private int area; // 행정면적
    private String imgUrl; // 이미지 URL
    private String homepageUrl; // 홈페이지 URL
    private List<CropEntity> cropList; //해당하는 지역에 속한 작물 목록
    private List<PolicyEntity> policyList; //해당하는 지역에 속한 정책 목록





}
