package com.ssafy.choonong.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data // Getter,Setter,toString,equals,hashCode 메서드를 자동으로 생성.
public class SearchDTO {
    private Integer regionId; // 지역 아이디
    private String regionName; // 지역명
    private String province; // 도의 이름
    private String imgUrl; // 이미지 파일 URL
    private String homepageUrl; // 지자체 홈페이지 URL
}
