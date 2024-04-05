<template>
  <div class="main-container">
    <h1>설문 결과</h1>
    <div class="popular-regions-container" v-if="resultList.length > 0">
      <h2 class="popular-regions-title">당신을 위한 최적의 귀농지 TOP5!!</h2>
      <swiper
        class="swiper"
        :modules="modules"
        :effect="'coverflow'"
        :slides-per-view="3"
        :centered-slides="true"
        :coverflow-effect="coverflowEffect"
        :space-between="30"
        :slides-per-group="1"
        :loop="true"
        :loop-fill-group-with-blank="true"
        :navigation="true"
        :pagination="paginationConfig"
        :autoplay="autoplayOptions"
        :speed="1000"
        @swiper="onSwiper"
        @mouseover="showNavigation = true"
        @mouseout="showNavigation = false"
        :initial-slide="1"
      >
        <swiper-slide
          v-for="(region, index) in resultList"
          :key="region.region_id"
          @click="handleSlideClick(index, region.region_id)"
          class="swiper-slide"
        >
          <div class="region-card">
            <!-- 이미지를 배경으로 사용하는 컨테이너 -->
            <div
              class="region-image-background"
              :style="{ backgroundImage: 'url(' + region.image_url + ')' }"
            >
              <!-- 데이터 표시 영역 -->
              <h2 class="rank">{{ index + 1 }}위</h2>
              <div class="region-info-overlay">
                <h4>{{ region.province }}</h4>
                <h3>{{ region.region_name }}</h3>
                <br />
                <br />
              </div>
            </div>
          </div>
        </swiper-slide>
      </swiper>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { Pagination, Navigation, Autoplay } from "swiper/modules";
// 스와이퍼 import 지우면 실행 안된다
import { Swiper, SwiperSlide } from "swiper/vue";
import { useSurveyStore } from "@/stores/surveyStore";
import { useRouter } from "vue-router";

import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";
import "swiper/css/effect-coverflow";

const surveyStore = useSurveyStore();
const modules = ref([Pagination, Navigation, Autoplay]);
const router = useRouter();

const showNavigation = ref(false);
// Swiper 인스턴스를 저장할 ref 정의
const swiperInstance = ref();

// Swiper 인스턴스를 저장하는 함수
const onSwiper = function(swiper) {
  swiperInstance.value = swiper;
};

// 클릭 이벤트 핸들러
const handleSlideClick = function(index, regionId) {
  // 현재 활성 슬라이드의 실제 인덱스 얻기
  let realIndex = swiperInstance.value.realIndex;
  if (realIndex === index) {
    router.push({ name: "regionDetail", params: { id: regionId } });
  } else {
    // 비활성 슬라이드 클릭시 슬라이드 넘김
    swiperInstance.value.slideToLoop(index);
  }
};

const autoplayOptions = {
  delay: 2000, // 2초 간격으로 자동 전환
  disableOnInteraction: false, // 사용자 상호작용 후에도 자동 재생 계속
};

const resultList = ref([]);

onMounted(() => {
  window.scrollTo(0, 0);
  resultList.value = surveyStore.resultList;
});
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.popular-regions-container {
  width: 100%;
  padding: 25px;
  height: 550px;
  margin-bottom: 100px;
  background: #fff;
  border: solid 3px #ecf6ec;
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

.region-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.region-image-background {
  width: 100%;
  height: 350px;
  background-size: cover;
  background-position: center;
  opacity: 0.7;
  border-radius: 10px;
  position: relative;
  cursor: pointer;
}

.region-info-overlay {
  width: 100%;
  height: 100px;
  color: #fff;
  text-align: center;
  background-image: linear-gradient(
    to bottom,
    rgba(0, 0, 0, 0.1),
    rgba(0, 0, 0, 1)
  );
  border-radius: 10px;
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
  padding: 5px 10px;
  width: 80px;
  height: 55px;
  border-top-left-radius: 10px;
  border-bottom-right-radius: 10px;
  line-height: 50px;
  z-index: 10;
  margin: 0px;
  position: absolute;
  top: 0px;
  left: 0px;
}


::v-deep(.swiper-button-next),
::v-deep(.swiper-button-prev) {
  color: #000;
  width: 50px;
  height: 360px;
  background: #ffffff;
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
