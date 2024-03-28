<template>
  <div class="info-chart-container">
    <div class="region-info">
      <h3>info</h3>
      <div class="region-info-detail">
        <div class="info-item">
          <img src="@/assets/extent.png" alt="세대수" class="info-icon">
          <p>세대수: {{ regionDetail.house_hold }} 세대</p>
        </div>
        <p>행정면적 : {{ regionDetail.area }} ㎢</p>
        <p>귀농인 : {{ regionDetail.returners }}명</p>

        <p>수질등급 : {{ regionDetail.water_quality }}</p>
        <p>미세먼지 농도 : {{ regionDetail.dust_concentration }}</p>

        <p>1000명당 보육시설 개수 : {{ regionDetail.child_care_facilities }}(개/1000명) </p>
        <p>평균 교육시설 도착 시간 : {{ regionDetail.average_accesstime_educational_facilities }} (도보기준, 분)</p>
        <p>평균 편의시설 (의료, 판매, 교통) 도착 시간 : {{ regionDetail.average_accesstime_amenities }} (도보기준, 분)</p>

        <p>평균 주택 가격 : {{ regionDetail.average_housing_price }} (1000원/m^2)</p>
        <p>평균 농지 가격 : {{ regionDetail.average_price_farmland }} (1000원/m^2)</p>
      </div>
    </div>

    <div class="region-chart">
      <h3>지역 작물</h3>
      <region-chart-component />
    </div>
  </div>
</template>

<script setup>
import RegionChartComponent from '@/components/region/RegionChartComponent.vue';

const props = defineProps({
  regionDetail: Object
});
</script>

<style scoped>
.info-chart-container {
  display: flex;
  /* border: 1px solid #4BAF47;
  border-radius: 1rem; */
  margin-right: 10px;
  margin-bottom: 10px;
}

.region-info, .region-chart {
  flex: 1; 
  margin-bottom: 20px;
}

.region-info h3, .region-chart h3 {
  margin: 10px;
}

.region-info {
  margin-right: 20px; 
  margin: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 3px solid #ECF6EC;
  border-radius: 1rem;
  background: white;
}

.region-chart {
  margin-right: 20px; 
  margin: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 3px solid #ECF6EC;
  border-radius: 1rem;
  background: white;
}

.region-info-detail {
  margin-left: 10px;
}

.info-icon {
  width: 30px;
  height: 30px;
  margin-right: 10px;
}
.info-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}
</style>
