<template>
  <div class="main-container">
    <!-- 검색 컨테이너 -->
    <div class="search-container">
      <h2 class="search-title">전국의 귀농지 정보를 검색해보세요...</h2>
      <region-search-component class="search-box" />
    </div>

    <!-- 인기 귀농지 스와이퍼 -->
    <div class="popular-regions-container" v-if="famousRegionsInfo.length > 0">
      <h2 class="popular-regions-title">귀농인이 많은 지역!!</h2>
      <swiper class="swiper" :modules="modules" :effect="'coverflow'" :slides-per-view="3" :centered-slides="true"
        :coverflow-effect="coverflowEffect" :space-between="30" :slides-per-group="1" :loop="true"
        :loop-fill-group-with-blank="true" :navigation="navigationEnabled" :pagination="paginationConfig"
        :autoplay="autoplayOptions" :speed="1000" :observer="true" :observe-parents="true" @swiper="onSwiper" @mouseover="showNavigation = true"
        @mouseout="showNavigation = false">

        <swiper-slide v-for="(region, index) in famousRegionsInfo" :key="region.region_id" @click="handleSlideClick(index, region.region_id)">
  <div class="region-card">
    <!-- 이미지를 배경으로 사용하는 컨테이너 -->
    <div class="region-image-background" :style="{ backgroundImage: 'url(' + region.image_url + ')' }">
      <!-- 데이터 표시 영역 -->
      <div class="region-info-overlay">
        <h2>{{ index + 1 }}위</h2>
        <h4>{{ region.province }}</h4>
        <h3>{{ region.region_name }}</h3>
        <p>귀농인 수: {{ region.returners }} 명</p>
        <p>평균 농지가격: {{ region.average_price_farmland * 1000 }} 원 (m^2)</p>
        <p>평균 주택가격: {{ region.average_housing_price * 1000 }} 원 (m^2)</p>
      </div>
    </div>
  </div>
</swiper-slide>
      </swiper>
    </div>

    <!-- 귀농인 증가 많은 지역 스와이퍼 -->
    <div class="popular-regions-container" v-if="increaseRegionInfo.length > 0">
      <h2 class="popular-regions-title">전년 대비 귀농인 많아진 지역!!</h2>
      <swiper class="swiper" :modules="modules" :effect="'coverflow'" :slides-per-view="3" :centered-slides="true"
        :coverflow-effect="coverflowEffect" :space-between="30" :slides-per-group="1" :loop="true"
        :loop-fill-group-with-blank="true" :navigation="navigationEnabled" :pagination="paginationConfig"
        :autoplay="autoplayOptions" :speed="1000" @swiper="onSwiper" @mouseover="showNavigation = true"
        @mouseout="showNavigation = false">

        <swiper-slide v-for="(region, index) in increaseRegionInfo" :key="region.region_id"
          @click="handleSlideClick(index, region.region_id)">
          <div class="region-card">

            <div class="region-info">
              <h2>{{ index + 1 }}위</h2>
              <h4>{{ region.province }}</h4>
              <h3>{{ region.region_name }}</h3>
            </div>
            <div class="region-image-container">
              <img :src="region.image_url" alt="지역 이미지" class="region-image">
            </div>
            <div class="region-additional-info">
              <p>귀농인 수: {{ region.returners }} 명</p>
              <p>증가한 귀농인 수: {{ increaseRegionPeople[index] }} 명</p>
              <p>평균 농지가격: {{ region.average_price_farmland * 1000 }} 원 (m^2)</p>
              <p>평균 주택가격: {{ region.average_housing_price * 1000 }} 원 (m^2)</p>
            </div>

          </div>
        </swiper-slide>
      </swiper>
    </div>
  </div>
</template>

<script setup>
import RegionSearchComponent from '../region/RegionSearchComponent.vue';
import { ref, onMounted } from 'vue';
import { Pagination, Navigation, Autoplay } from 'swiper/modules';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { useRegionStore } from '@/stores/regionStore';
import { useRouter } from 'vue-router';

import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import 'swiper/css/effect-coverflow';


const regionStore = useRegionStore();
const famousRegionIds = [92, 51, 26, 13, 78, 132, 41, 67]// 의성 상주 김천 고흥 영천 해남 밀양 양평 영동 // 귀농인 가장 많은 지역

const increaseRegionPeople = [25,20,28,13,13,13,11,11,11]
const increaseRegionIds = [1,26,123,64,122,65,66,70,22]
//가평 25, 김천20, 평창18, 양산13, 파주13, 양양13, 양주11, 연천11, 군위 11 // 21년 대비 귀농인 증가 많은 순


//남양주158, 제주39, 가평 35, 양산 26, 영동24, 평창19, 파주16, 양주16 // 21년 대비 귀농 가구원 증가많은 순
//남양주270, 제주206, 가평 81, 양산 60, 영동200, 평창131, 파주97, 양주 48, 아산210, 해남 200 // 22년 귀농가구원수 많은 지역
const famousRegionsInfo = ref([]); 
const increaseRegionInfo = ref([])

onMounted(async () => {
  const famousDetails = await regionStore.getRegionsDetailList(famousRegionIds);
  famousRegionsInfo.value = famousDetails.value;

  const increaseDetails = await regionStore.getRegionsDetailList(increaseRegionIds);
  increaseRegionInfo.value = increaseDetails.value;
});

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
const handleSlideClick = (index, regionId) => {
  // 현재 활성 슬라이드의 실제 인덱스 얻기
  let realIndex = swiperInstance.value.realIndex;
  if (realIndex === index) {
    router.push({ name: 'regionDetail', params: { id: regionId } });
  } else {
    // 비활성 슬라이드 클릭시 슬라이드 넘김
    swiperInstance.value.slideToLoop(index);
  }
};

const autoplayOptions = {
  delay: 2000, // 2초 간격으로 자동 전환
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

.region-info h2,
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
  height: 4%; /* 이미지 높이 조절 */
  background-size: cover;
  background-position: center;
  opacity: 0.7; /* 이미지 투명도 설정 */
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10px; /* 이미지 모서리 둥글게 */
  padding-left: 20px;
  padding-right: 20px;
}

.region-info-overlay {
  color: #fff; /* 데이터 텍스트 색상 */
  text-align: center;
  background-color: rgba(0, 0, 0, 0.5); /* 데이터 배경 투명도 */
  border-radius: 10px; /* 데이터 영역 모서리 둥글게 */
  padding: 10px;
}
</style>
