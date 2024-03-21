<template>
  <div>
  <h1>설문 결과</h1>
  <h3>설문 답변 리스트 : {{ surveyStore.selectedAnswers }}</h3>
  <h4>답변은 답변의 인덱스값</h4>
  
  <swiper class="swiper" :modules="modules" :effect="'coverflow'" :slides-per-view="3" :centered-slides="true"
    :coverflow-effect="{
    rotate: 0, // 슬라이드 회전 없음
    stretch: 1, // 슬라이드 사이 간격 조정
    depth: 50, // 깊이 효과 강조, 슬라이드 사이의 '깊이' 효과
    modifier: 1, // 효과 영향 범위
    slideShadows: false, // 슬라이드 그림자 제거
  }" :space-between="30" :slides-per-group="1" :loop="true" :loop-fill-group-with-blank="true" :navigation="true"
    :pagination="{ clickable: true }">
    
    <swiper-slide v-for="(region, index) in regionStore.dummyRegionList" :key="region.region_id">
  <div class="content-container">
    <div class="upper-container">
      <div class="text-container">
        <h2>{{ index + 1 }}순위</h2>
        
        <h3> {{ region.province }}</h3>
        <h3> {{ region.region_name }}</h3>
        <h3>적합도: {{ (region.suitability * 100).toFixed(0) }}%</h3>
      </div>
      <img src="@/assets/cropimage.png" alt="지역 이미지" class="img-swiper">
    </div>
    <br>
    <div class="additional-text">
      <h4>aaaaaaaaaaaa</h4>
      <p>aaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
      <p>컴포넌트 클릭시 지역 상세로 이동</p>
    </div>
  </div>
  
</swiper-slide>

  </swiper>
</div>
</template>

<script setup>
import { ref } from 'vue'
import { Pagination, Navigation } from 'swiper/modules'
import { Swiper, SwiperSlide } from 'swiper/vue'
import { useRegionStore } from '@/stores/regionStore';
import { useSurveyStore } from '@/stores/surveyStore';

import 'swiper/css'
import 'swiper/css/pagination'
import 'swiper/css/navigation'
import 'swiper/css/effect-coverflow'

const modules = ref([Pagination, Navigation])
const regionStore = useRegionStore()
const surveyStore = useSurveyStore()
</script>

<style scoped>
.main-container {
  padding: auto;
}

/* Swiper 컨테이너 스타일 */
.swiper {
  width: 100%;
  height: 100%;
  margin-bottom: 40px;
}

.swiper-horizontal {
  padding-bottom: 40px;
}
/* Swiper 슬라이드 기본 스타일 */
::v-deep(.swiper-slide) {
  text-align: center;
  font-size: 1em;
  background: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
  height: 500px;
  width: 80%;
  /* border: 1px solid black; */
}

/* 슬라이드 양 사이드 */
::v-deep(.swiper-slide-prev),
::v-deep(.swiper-slide-next) {
  opacity: 0.3;
  margin-right: 0px;
}


/* 네비게이션 버튼 스타일 */
::v-deep(.swiper-button-next),
::v-deep(.swiper-button-prev) {
  color: #000;
  width: 50px;
  height: 50px;
  background: rgba(0, 0, 0, 0);
  border-radius: 50%;
  transform: translateY(0%);
  z-index: 10;
}

::v-deep(.swiper-button-next) {
  right: -10px;
}

::v-deep(.swiper-button-prev) {
  left: -10px;
}

/* 페이지네이션 스타일 */
::v-deep(.swiper-pagination-bullet) {
  background: #000;
  opacity: 1;
}

::v-deep(.swiper-pagination-bullet-active) {
  background: #007bff;
}

::v-deep(.swiper-pagination) {
  bottom: 0px;
  width: 100%;
  text-align: center;
  margin-top: 20px;
  padding-top: 40px;
  z-index: 9;
}

.img-swiper {
  height: 200px;
  width: 200px;
  margin: 20px;
  border: 1px solid rgba(0, 0, 0, 0.3);
  object-fit: cover;
}

.content-container {
  display: flex;
  flex-direction: column;
  align-items: center; 
  height: 100%;
  width: 95%;
  border: solid 2px #4BAF47;
  border-radius: 1rem;
}

.upper-container {
  display: flex;
  width: 100%;
}

.text-container {
  text-align: left;
  flex: 1; 
  padding: 20px;
}

.additional-text {
  text-align: center;
  width: 100%;
}
</style>