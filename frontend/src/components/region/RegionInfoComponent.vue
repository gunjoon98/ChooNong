<template>
  <div class="info-chart-container">
    <div class="region-info">
      <h3>info</h3>
      <div class="region-info-detail">
  <!-- 세대수 -->
  <div class="info-item">
    <div class="info-label">세대수: </div>
    <div class="info-content">{{ regionDetail.house_hold }}</div>
    <div class="info-unit">세대</div>
  </div>

  <!-- 행정면적 -->
  <div class="info-item">
    <div class="info-label">행정면적: </div>
    <div class="info-content">{{ regionDetail.area }}</div>
    <div class="info-unit">㎢</div>
  </div>

  <!-- 귀농인 -->
  <div class="info-item">
    <div class="info-label">귀농인: </div>
    <div class="info-content">{{ regionDetail.returners }}</div>
    <div class="info-unit">명</div>
  </div>

  <!-- 수질등급 -->
  <div class="info-item">
    <div class="info-label">수질등급: </div>
    <div class="info-content">{{ regionDetail.water_quality }}</div>
    <div class="info-unit"></div> <!-- 단위 없음 -->
  </div>

  <!-- 미세먼지 농도 -->
  <div class="info-item">
    <div class="info-label">미세먼지 농도: </div>
    <div class="info-content">{{ regionDetail.dust_concentration }}</div>
    <div class="info-unit"></div> <!-- 단위 없음 -->
  </div>

  <!-- 1000명당 보육시설 개수 -->
  <div class="info-item">
    <div class="info-label">보육시설 개수: </div>
    <div class="info-content">{{ regionDetail.child_care_facilities }}</div>
    <div class="info-unit">(개/1000명)</div>
  </div>

  <!-- 평균 교육시설 도착 시간 -->
  <div class="info-item">
    <div class="info-label">평균 교육시설 도착 시간: </div>
    <div class="info-content">{{ regionDetail.average_accesstime_educational_facilities }}</div>
    <div class="info-unit">(도보기준, 분)</div>
  </div>

  <!-- 평균 편의시설 도착 시간 -->
  <div class="info-item">
    <div class="info-label">평균 편의시설 도착 시간: </div>
    <div class="info-content">{{ regionDetail.average_accesstime_amenities }}</div>
    <div class="info-unit">(도보기준, 분)</div>
  </div>

  <!-- 평균 주택 가격 -->
  <div class="info-item">
    <div class="info-label">평균 주택 가격: </div>
    <div class="info-content">{{ regionDetail.average_housing_price }}</div>
    <div class="info-unit">(1000원/m^2)</div>
  </div>

  <!-- 평균 농지 가격 -->
  <div class="info-item">
    <div class="info-label">평균 농지 가격: </div>
    <div class="info-content">{{ regionDetail.average_price_farmland }}</div>
    <div class="info-unit">(1000원/m^2)</div>
  </div>
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

.region-info p {
  margin: 0px 10px;
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

.info-label, .info-content, .info-unit {
  margin-right: 5px; /* 요소 간 간격 조정 */

}

.info-label {
  font-weight: bold;
}

.info-unit {
  font-size: 0.8em;
  color: #888;
}
</style>
