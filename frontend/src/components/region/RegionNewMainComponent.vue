<template>
  <div class="main-container">
    <!-- 검색 컨테이너 -->
    <div class="search-container">
      <h2 class="search-title">전국의 귀농지 정보를 검색해보세요...</h2>
      <region-search-component class="search-box" />
    </div>

    <!-- 인기 귀농지 스와이퍼 -->
    <div class="popular-regions-container">
      <h2 class="popular-regions-title">인기 귀농지!!</h2>
      <swiper class="swiper" :modules="modules" :effect="'coverflow'" :slides-per-view="3" :centered-slides="true"
        :coverflow-effect="coverflowEffect" :space-between="30" :slides-per-group="1" :loop="true"
        :loop-fill-group-with-blank="true" :navigation="navigationEnabled" :pagination="paginationConfig" :autoplay="autoplayOptions" :speed="1000"
        @swiper="onSwiper" @mouseover="showNavigation = true" @mouseout="showNavigation = false" >

        <swiper-slide v-for="(region, index) in regionStore.dummyRegionList" :key="region.region_id"
          @click="handleSlideClick(index, region.region_name)">
          <div class="region-card">
            
            <div class="region-info">
              <h2>{{ index + 1 }}위</h2>
              <h3>{{ region.province }}</h3>
              <h4>{{ region.region_name }}</h4>
              <p>적합도: {{ (region.suitability * 100).toFixed(0) }}%</p>
            </div>
            
            <div class="region-additional-info">
              <p>aaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
              <p>컴포넌트 클릭시 지역 상세로 이동</p>
              <p>지금은 콘솔출력만</p>
            </div>
            <div class="region-image-container">
              <img src="@/assets/cropimage.png" alt="지역 이미지" class="region-image">
            </div>
          </div>
        </swiper-slide>
      </swiper>
    </div>
  </div>
</template>

<script setup>
import RegionSearchComponent from '../region/RegionSearchComponent.vue';
import { ref } from 'vue';
import { Pagination, Navigation, Autoplay } from 'swiper/modules';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { useRegionStore } from '@/stores/regionStore';
import { useRouter } from 'vue-router';

import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import 'swiper/css/effect-coverflow';


const regionStore = useRegionStore();
const modules = ref([Pagination, Navigation, Autoplay]);
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
    router.push({ name: 'regionMain'})
  } else {
    // 비활성 슬라이드 클릭시 슬라이드 넘김
    swiperInstance.value.slideToLoop(index);
  }
};

const autoplayOptions = {
  delay: 2000, // 5초 간격으로 자동 전환
  disableOnInteraction: false, // 사용자 상호작용 후에도 자동 재생 계속
};
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.search-container {
  width: 100%;
  max-width: 800px;
  margin-bottom: 40px;

}

.search-title {
  margin-bottom: 20px;
  text-align: center;
}

.popular-regions-container {
  width: 100%;
  max-width: 1200px;
  margin-bottom: 50px;
  background: #fff;
  border: solid 3px #ECF6EC;
  border-radius: 1rem;
}

.popular-regions-title {
  margin-bottom: 20px;
  text-align: center;
}

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
}

.region-image {
  max-width: 100%;
  height: auto;
  border-radius: 10px;
}

.region-info h2,
.region-info h3,
.region-info h4 {
  margin: 5px 0;
}

.region-additional-info {
  font-size: 0.9rem;
  margin-top: 10px;
}
</style>
