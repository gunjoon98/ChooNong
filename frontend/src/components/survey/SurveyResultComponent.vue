<template>
  <div>
    <h1>설문 결과</h1>
    <h3>설문 답변 리스트 : {{ surveyStore.selectedAnswers }}</h3>
    <h4>답변은 답변의 인덱스값</h4>

    <div class="popular-regions-container" v-if="resultList.length > 0">
      <h2 class="popular-regions-title">귀농인이 많은 지역!!</h2>
      <swiper class="swiper" :modules="modules" :effect="'coverflow'" :slides-per-view="3" :centered-slides="true"
        :coverflow-effect="coverflowEffect" :space-between="30" :slides-per-group="1" :loop="true"
        :loop-fill-group-with-blank="true" :navigation="true" :pagination="paginationConfig"
        :autoplay="autoplayOptions" :speed="1000" @swiper="onSwiper" @mouseover="showNavigation = true"
        @mouseout="showNavigation = false">

        <swiper-slide v-for="(region, index) in resultList" :key="region.region_id"
          @click="handleSlideClick(index, region.region_id)" class="swiper-slide">
          <div class="region-card">
            <!-- 이미지를 배경으로 사용하는 컨테이너 -->
            <div class="region-image-background" :style="{ backgroundImage: 'url(' + region.image_url + ')' }">
              <!-- 데이터 표시 영역 -->
              <h2 class="rank">{{ index + 1 }}위</h2>
              <div class="region-info-overlay">
                <h4>{{ region.province }}</h4>
                <h3>{{ region.region_name }}</h3>
                <p>귀농인 수: {{ region.returners }} 명</p>
                <br>
                <br>

              </div>
            </div>
          </div>
        </swiper-slide>
      </swiper>
    </div>

    <!-- <swiper class="swiper" :modules="modules" :effect="'coverflow'" :slides-per-view="3" :centered-slides="true"
      :coverflow-effect="{
      rotate: 0,
      stretch: 1,
      depth: 50,
      modifier: 1,
      slideShadows: false,
    }" :space-between="30" :slides-per-group="1" :loop="true" :loop-fill-group-with-blank="true" :navigation="true"
      :pagination="{ clickable: true }" @swiper="onSwiper" @mouseover="showNavigation = true"
      @mouseout="showNavigation = false">

      <swiper-slide v-for="(region, index) in resultList" :key="region.region_id"
        @click="handleSlideClick(index, region.region_name)">
        <div class="content-container">
          <div class="upper-container">
            <div class="text-container">
              <h2>{{ index + 1 }}순위</h2>
              <h3> {{ region.province }}</h3>
              <h3> {{ region.region_name }}</h3>
            </div>
            <img src="@/assets/cropimage.png" alt="지역 이미지" class="img-swiper">
          </div>
          </div>

      </swiper-slide>

    </swiper> -->
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { Pagination, Navigation } from 'swiper/modules';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { useRegionStore } from '@/stores/regionStore';
import { useSurveyStore } from '@/stores/surveyStore';
import { useRouter } from 'vue-router';

import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import 'swiper/css/effect-coverflow';

const regionStore = useRegionStore();
const surveyStore = useSurveyStore();
const modules = ref([Pagination, Navigation]);
const router = useRouter();

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
    router.push({ name: 'regionDetail'})
  } else {
    // 비활성 슬라이드 클릭시 슬라이드 넘김
    swiperInstance.value.slideToLoop(index);
  }
};

const resultList = ref([]);
onMounted(() => {
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
  margin-top: 30px;
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
  opacity: 0;
  /* 기본적으로 안 보이게 */
  transition: opacity 0.5s;
  /* 서서히 보이게 */
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
  margin-bottom: 20px;
  /* 이미지와 텍스트 사이의 여백 조정 */
  width: auto;
  /* 이미지 너비 자동 조정으로 비율 유지 */
  max-width: 100%;
  /* 컨테이너 너비를 넘지 않도록 최대 너비 설정 */
  height: 300px;
  /* 이미지 높이 자동 조정으로 비율 유지 */
}

.content-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100%;
  width: 99%;
  border: solid 2px #4BAF47;
  border-radius: 1rem;
}

.upper-container {
  flex-direction: column;
  /* 기존의 row에서 column으로 변경 */
  align-items: center;
  /* 중앙 정렬 */
  width: 100%;
}

.upper-container h3 {
  margin: 0px
}

.text-container {
  text-align: center;
  /* 텍스트를 중앙 정렬 */
  padding: 0px;
  /* 적절한 패딩으로 텍스트 영역 정의 */
}

.text-container h2 {
  margin: 0px;
}

.additional-text {
  text-align: center;
  width: 100%;
}

.additional-text p {
  margin: 0px
}

.popular-regions-container {
  width: 100%;
  padding: 25px;
  /* max-width: 1200px; */
  height: 550px;
  margin-bottom: 100px;
  background: #fff;
  border: solid 3px #ECF6EC;
  border-radius: 1rem;
}

.popular-regions-title {
  margin-bottom: 50px;
  text-align: center;
}

.swiper {
  width: 1300px;
  padding: 0 70px;
}

/* .swiper-slide {
  margin: 0 10px;
  width: 50px
} */

.region-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.region-image-container {
  width: 100%;
  display: flex;
  justify-content: center;
  margin: 0px;
  padding: 0px
}

.region-image {
  max-width: 100%;
  height: 200px;
  border-radius: 10px;
  padding-left: 20px;
  padding-right: 20px;
}

.region-info h3,
.region-info h4 {
  margin: 5px 0;
}

.region-additional-info {
  font-size: 0.9rem;
  margin-top: 10px;
}

.region-image-background {
  width: 100%;
  height: 350px;
  /* 이미지 높이 조절 */
  background-size: cover;
  background-position: center;
  opacity: 0.7;
  /* 이미지 투명도 설정 */
  /* display: flex;
  justify-content: center;
  align-items: center; */
  border-radius: 10px;
  /* 이미지 모서리 둥글게 */
  /* padding-left: 20px;
  padding-right: 20px; */
  position: relative;
  cursor: pointer;
}

.region-info-overlay {
  width: 100%;
  height: 100px;
  color: #fff;
  /* 데이터 텍스트 색상 */
  text-align: center;
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 1));
  /* background-color: rgba(0, 0, 0, 0.5); */
  /* 데이터 배경 투명도 */
  border-radius: 10px;
  /* 데이터 영역 모서리 둥글게 */
  padding: 10px;
  position: absolute;
  bottom: 0;
}

.region-info-overlay h3,
.region-info-overlay h4,
.region-info-overlay p {
  margin: 0;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.rank {
  background-color: rgba(0, 0, 0, 0.7);
  color: rgb(255, 255, 255);
  /* 텍스트 색상 */
  padding: 5px 10px;
  width: 80px;
  height: 55px;
  /* 패딩 */
  border-top-left-radius: 10px;
  border-bottom-right-radius: 10px;
  /* 원형 모양 */
  font-weight: bold;
  line-height: 50px;
  /* 굵은 글씨 */
  z-index: 10;
  /* 다른 요소들 위에 오도록 z-index 설정 */
  margin: 0px;
  position: absolute;
  top: 0px;
  left: 0px;
}

/* .increse-arrow {
  color: #ff3838;
} */

.arrow-up {
  width: 25px;
  height: auto;
  margin-bottom: 3px;
}

::v-deep(.swiper-button-next),
::v-deep(.swiper-button-prev) {
  color: #000;
  width: 50px;
  height: 360px;
  background: #ffffff;
  /* border-radius: 50%; */
  transform: translateY(-43%);
  z-index: 10;
  opacity: 1; /* 항상 보이도록 설정 */
  display: block;
  transition: opacity 0.5s; /* 호버 효과를 위한 트랜지션은 유지 */
  display: flex;
  justify-content: center;
  align-items: center;
}

/* 호버 효과에 대한 스타일 삭제 */
.swiper ::v-deep(.swiper-button-next),
.swiper ::v-deep(.swiper-button-prev) {
  opacity: 1;
}

::v-deep(.swiper-button-next) {
  right: 0px;
}

::v-deep(.swiper-button-prev) {
  left: 0px;
}
</style>