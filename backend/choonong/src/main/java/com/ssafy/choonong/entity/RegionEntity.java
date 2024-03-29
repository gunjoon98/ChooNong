package com.ssafy.choonong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "region")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer regionId; //지역 아이디

    @Column(name = "region_name")
    private String regionName; // 지역명

    @Column
    private int household; //세대수

    @Column(length = 32)
    private String province; //도 이름 (예: 충청남도)

    @Column
    private int returners; //귀농인 수

    @Column
    private int area; // 행정면적

    @Column(name = "image_url")
    private String imageUrl; //이미지URL

    @Column(name = "child_care_facilities")
    private Double childCareFacilities;

    @Column(name = "average_accesstime_traffic_facilities")
    private Double averageAccesstimeTrafficFacilities; // 교통 시설 접근성

    @Column(name = "average_accesstime_medical_facilities")
    private Double averageAccesstimeMedicalFacilities; // 의료 시설 접근성

    @Column(name = "average_accesstime_sales_facilities")
    private Double averageAccesstimeSalesFacilities; // 판매 시설 접근성

    @Column(name = "average_accesstime_educational_facilities")
    private Double averageAccesstimeEducationalFacilities;

    @Column(name = "average_price_farmland")
    private Double averagePriceFarmland;

    @Column(name = "average_housing_price")
    private Double averageHousingPrice;

    @Column(name = "average_accesstime_amenities")
    private Double averageAccesstimeAmenities;

    @Column(name = "population_density")
    private Double populationDensity;

    @Column(name = "dust_concentration")
    private Double dustConcentration;

    @Column(name = "water_quality")
    private Double waterQuality;

    @Column(name = "pediatrics")
    private Integer pediatrics; // 소아과 수

    @Column(name = "kindergarden")
    private Integer kindergarden; // 유치원 수

    @Column(name = "elementary_school")
    private Integer elementarySchool; // 초등학교 수

    @Column(name = "middle_school")
    private Integer middleSchool; // 중학교 수

    @Column(name = "education_cluster")
    private Integer educationCluster;

    @Column(name = "ground_cluster")
    private Integer groundCluster;

    @Column(name = "resident_cluster")
    private Integer residentCluster;

    @Column(name = "env_cluster")
    private Integer envCluster;

    public void updateNewValues(int pediatrics, int kindergarden, int elementarySchool, int middleSchool) {
        this.pediatrics = pediatrics;
        this.kindergarden = kindergarden;
        this.elementarySchool = elementarySchool;
        this.middleSchool = middleSchool;
//        return this;
    }
}
