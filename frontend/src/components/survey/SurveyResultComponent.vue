<template>
  <div>
  <h1>설문 결과</h1>
  <h3>설문 답변 리스트 : {{ surveyStore.selectedAnswers }}</h3>
  <h4>답변은 답변의 인덱스값</h4>
  
  <swiper class="swiper" :modules="modules" :effect="'coverflow'" :slides-per-view="3" :centered-slides="true"
        :coverflow-effect="{
        rotate: 0,
        stretch: 1,
        depth: 50,
        modifier: 1,
        slideShadows: false,
      }" :space-between="30" :slides-per-group="1" :loop="true" :loop-fill-group-with-blank="true" :navigation="true"
        :pagination="{ clickable: true }"
        @swiper="onSwiper"
        @mouseover="showNavigation = true"
        @mouseout="showNavigation = false">
    
        <swiper-slide v-for="(region, index) in regionStore.dummyRegionList" :key="region.region_id"
                    @click="handleSlideClick(index, region.region_name)">
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
    <div class="additional-text">
      <p>aaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
      <p>컴포넌트 클릭시 지역 상세로 이동</p>
      <p>지금은 콘솔출력만</p>
    </div>
  </div>
  
</swiper-slide>

  </swiper>
</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { Pagination, Navigation } from 'swiper/modules';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { useRegionStore } from '@/stores/regionStore';
import { useSurveyStore } from '@/stores/surveyStore';

import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import 'swiper/css/effect-coverflow';

const regionStore = useRegionStore();
const surveyStore = useSurveyStore();
const modules = ref([Pagination, Navigation]);

const showNavigation = ref(false);
// Swiper 인스턴스를 저장할 ref 정의
const swiperInstance = ref();

// Swiper 인스턴스를 저장하는 함수
const onSwiper = (swiper) => {
  swiperInstance.value = swiper;
};

// 클릭 이벤트 핸들러
const handleSlideClick = (index, regionName) => {
  // 현재 활성 슬라이드의 실제 인덱스 얻기
  let realIndex = swiperInstance.value.realIndex;
  if (realIndex === index) {
    console.log(regionName);
  } else {
    // 비활성 슬라이드 클릭시 슬라이드 넘김
    swiperInstance.value.slideToLoop(index);
  }
};

const resultList = ref([]);
onMounted(async () => {
  await surveyStore.getSurveyResult();
  resultList.value = surveyStore.resultList;
  console.log(resultList.value);
})
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
  opacity: 0; /* 기본적으로 안 보이게 */
  transition: opacity 0.5s; /* 서서히 보이게 */
  display: block;
}

.swiper:hover ::v-deep(.swiper-button-next),
.swiper:hover ::v-deep(.swiper-button-prev),
.swiper:hover ::v-deep(.swiper-pagination) {
  opacity: 1;
}

::v-deep(.swiper-button-next) {
  right: -10px;
}

::v-deep(.swiper-button-prev) {
  left: 15px;
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
  opacity: 0; 
  transition: opacity 0.5s;
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

.upper-container h3 {
  margin: 0px
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

.additional-text p {
  margin: 0px
}

</style>