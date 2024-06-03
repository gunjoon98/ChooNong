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
          v-for="(region, index) in famousRegionsInfo"
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
                <p>귀농인 수: {{ region.returners }} 명</p>
                <br />
                <br />
              </div>
            </div>
          </div>
        </swiper-slide>
      </swiper>
    </div>

    <!-- 귀농인 증가 많은 지역 스와이퍼 -->
    <div class="popular-regions-container" v-if="increaseRegionInfo.length > 0">
      <h2 class="popular-regions-title">전년 대비 귀농인 많아진 지역!!</h2>
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
          v-for="(region, index) in increaseRegionInfo"
          :key="region.region_id"
          @click="handleSlideClick(index, region.region_id)"
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
                <!-- <h2>{{ index + 1 }}위</h2> -->
                <h4>{{ region.province }}</h4>
                <h3>{{ region.region_name }}</h3>
                <p>
                  귀농인 수: {{ region.returners }} 명({{
                    increaseRegionPeople[index]
                  }}<img src="@/assets/up-arrow.png" class="arrow-up" />)
                </p>

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
import RegionSearchComponent from "../region/RegionSearchComponent.vue";
import { ref, onMounted } from "vue";
import { Pagination, Navigation, Autoplay } from "swiper/modules";
import { Swiper, SwiperSlide } from "swiper/vue";
import { useRegionStore } from "@/stores/regionStore";
import { useRouter } from "vue-router";

import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";
import "swiper/css/effect-coverflow";

const regionStore = useRegionStore();
// const famousRegionIds = [92, 51, 26, 13, 78, 132, 41, 67]// 의성 상주 김천 고흥 영천 해남 밀양 양평 영동 // 귀농인 가장 많은 지역
const famousRegionIds = [92, 51, 26, 13, 78];
const increaseRegionPeople = [25, 20, 18, 13, 13];
const increaseRegionIds = [1, 26, 123, 64, 122];
//가평 25, 김천20, 평창18, 양산13, 파주13, 양양13, 양주11, 연천11, 군위 11 // 21년 대비 귀농인 증가 많은 순

//남양주158, 제주39, 가평 35, 양산 26, 영동24, 평창19, 파주16, 양주16 // 21년 대비 귀농 가구원 증가많은 순
//남양주270, 제주206, 가평 81, 양산 60, 영동200, 평창131, 파주97, 양주 48, 아산210, 해남 200 // 22년 귀농가구원수 많은 지역
const famousRegionsInfo = ref([]);
const increaseRegionInfo = ref([]);

onMounted(async () => {
  window.scrollTo(0, 0);

  const famousDetails = await regionStore.getRegionsDetailList(famousRegionIds);
  famousRegionsInfo.value = famousDetails.value;

  const increaseDetails = await regionStore.getRegionsDetailList(
    increaseRegionIds
  );
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
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  /* padding: 20px; */
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
  padding: 25px;
  /* max-width: 1200px; */
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
  padding: 0px;
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
  background-image: linear-gradient(
    to bottom,
    rgba(0, 0, 0, 0.1),
    rgba(0, 0, 0, 1)
  );
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
