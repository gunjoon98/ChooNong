<template>
  <div class="info-chart-container">
    <h2 class="info-title">지역의 기본 정보입니다.</h2>
    <div class="region-info">
      <div class="region-info-detail">
        <!-- 지역정보 -->
        <div class="info-group">
          <div class="group-title">지역정보</div>
          <div class="info-item">
            <div class="info-label">세대수</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.house_hold }}</div>
              <div class="info-unit">세대</div>
            </div>
          </div>
          <div class="info-item">
            <div class="info-label">행정면적</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.area }}</div>
              <div class="info-unit">㎢</div>
            </div>
          </div>
          <div class="info-item">
            <div class="info-label">귀농인구</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.returners }}</div>
              <div class="info-unit">명</div>
            </div>
          </div>
        </div>

        <!-- 지역환경 -->
        <div class="info-group">
          <div class="group-title">지역환경</div>
          <div class="info-item">
            <div class="info-label">인구밀도</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.population_density }}</div>
              <div class="info-unit">명/㎢</div>
            </div>
          </div>
          <div class="info-item">
            <div class="info-label">수질등급</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.water_quality }}</div>
              <div class="info-unit">급</div>
            </div>
          </div>
          <div class="info-item">
            <div class="info-label">미세먼지 농도</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.dust_concentration }}</div>
              <div class="info-unit">㎍/㎥</div>
            </div>
          </div>
        </div>

        <!-- 주거시설 -->
        <div class="info-group">
          <div class="group-title">주거시설</div>
          <div class="info-item">
            <div class="info-label">보육시설 개수</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.child_care_facilities }}</div>
              <div class="info-unit">개 (1000명 당)</div>
            </div>
          </div>
          <div class="info-item">
            <div class="info-label">평균 교육시설 도착 시간</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.average_accesstime_educational_facilities }}</div>
              <div class="info-unit">분 (도보기준)</div>
            </div>
          </div>
          <div class="info-item">
            <div class="info-label">평균 편의시설 도착 시간</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.average_accesstime_amenities }}</div>
              <div class="info-unit">분 (도보기준)</div>
            </div>
          </div>
        </div>

        <!-- 금융 -->
        <div class="info-group">
          <div class="group-title">금융</div>
          <div class="info-item">
            <div class="info-label">평균 주택 가격</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.average_housing_price * 1000 }}</div>
              <div class="info-unit">원/㎡</div>
            </div>
          </div>
          <div class="info-item">
            <div class="info-label">평균 농지 가격</div>
            <div class="info-content-unit">
              <div class="info-content">{{ regionDetail.average_price_farmland * 1000 }}</div>
              <div class="info-unit">원/㎡</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

const props = defineProps({
  regionDetail: Object
});
</script>

<style scoped>
.info-title {
  text-align: center;
}

.info-chart-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  max-width: 1200px; /* 최대 너비 설정 */
  margin: auto; /* 중앙 정렬 */
  gap: 15px; /* 요소 간 간격 줄임 */
}

.region-chart {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  gap: 15px; /* 요소 간 간격 줄임 */
}

.region-info-detail {
  width: 1000px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  /* gap: 15px; 요소 간 간격 줄임 */
}

.info-group {
  width: calc((100% / 2) - 5px);
  padding: 15px; /* 패딩 줄임 */
  margin: 5px 0;
  background-color: #f6f9f6; /* 배경색 */
  border-radius: 8px; /* 모서리 둥글게 */
  border: solid 1px #e9e9e9;
  /* box-shadow: 0 3px 6px rgba(0,0,0,0.1); 그림자 효과 */
  display: flex;
  flex-direction: column;
  /* flex-wrap: wrap; */
  /* justify-content: space-between; */
}

.group-title {
  width: 100%;
  text-align: left;
  font-size: 1.1em; /* 글자 크기 조정 */
  font-weight: bold;
  margin-bottom: 10px; /* 타이틀과 항목 간의 간격 줄임 */
  color: #333; /* 글자 색상 */
  /* text-decoration: underline;
	text-decoration-color: rgba(233, 255, 185, 0.5);
	text-decoration-thickness: 20px;
	text-underline-offset: -20px; */
}

.info-item {
  width: 100%; /* 항목 너비 설정 */
  padding: 10px; /* 패딩 줄임 */
  margin: 5px 0;
  background-color: #ffffff; /* 배경색 */
  border: solid 1px #e9e9e9;
  border-radius: 8px; /* 모서리 둥글게 */
  box-shadow: 0 2px 4px rgba(0,0,0,0.05);
  display: flex;
  flex-direction: column;
  gap: 8px; /* 내부 요소 간 간격 줄임 */
}

.info-content-unit {
  display: flex;
  justify-content: right; /* 내용과 단위를 양 끝으로 정렬 */
  align-items: center;
  width: 100%;
}

.info-label {
  font-weight: 500;
  color: #666;
  font-size: 0.8em;
}

.info-content {
  flex-grow: 1; /* 내용이 단위 옆에 붙도록 남은 공간을 채움 */
  text-align: right;
}

.info-unit {
  /* 내용과 단위 사이의 간격을 줄임 */
  margin-left: 15px; /* 왼쪽 마진을 추가하여 간격 조정 */
  color: #888;
  /* text-align: center; */
}

.info-content, .info-unit {
  font-size: 1em;
  font-weight: bold;
  color: #333; /* 강조를 위한 색상 변경 */
}

.region-chart {
  width: 100%;
  padding: 15px; /* 패딩 줄임 */
  background-color: #f7f7f7;
  border-radius: 8px; /* 모서리 둥글게 조정 */
  box-shadow: 0 3px 6px rgba(0,0,0,0.1); /* 그림자 효과 조정 */
}

/* 금융 정보 섹션을 위한 특별한 스타일링 */
.finance-info-group .info-item {
  width: calc(50% - 20px); /* 금융 정보 항목이 2개 뿐이므로 너비 조정 */
}

/* 마지막 항목 오른쪽 마진 제거 */
.info-group:last-child .info-item:last-child {
  margin-right: 0;
}
</style>